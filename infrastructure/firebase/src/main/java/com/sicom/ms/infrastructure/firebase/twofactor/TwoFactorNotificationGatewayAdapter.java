package com.sicom.ms.infrastructure.firebase.twofactor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.Timestamp;
import com.google.firebase.cloud.FirestoreClient;
import com.sicom.ms.domain.model.error.BadRequestException;
import com.sicom.ms.domain.model.twofactor.TwoFactorNotification;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorNotificationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class TwoFactorNotificationGatewayAdapter implements TwoFactorNotificationGateway {

    private final FireBaseInstanceAdapter adapter;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String COLLECTION_NAME = "two-factor-notification";
    private static final String DATE = "date";
    private static final String SICOM_CODE = "sicomCode";

    @Value("${app.two-factor.notification.timeout}")
    private Integer timeOut;

    @Value("${app.two-factor.notification.title}")
    private String title;

    @Value("${app.two-factor.notification.body}")
    private String body;

    @Override
    @SuppressWarnings({"unchecked", "Duplicates", "BlockingMethodInNonBlockingContext"})
    public Mono<TwoFactorNotification> saveOrUpdate(String user, String sicomCode) {
        try {
            final var notification = TwoFactorNotification.builder()
                    .title(title).body(body)
                    .date(Date.from(Instant.now())).user(user).sicomCode(sicomCode).build();

            final Map<String, Object> collection = objectMapper.convertValue(notification, Map.class);
            collection.put(DATE, Timestamp.of(notification.getDate()));

            adapter.fireBaseInstance();
            final var db = FirestoreClient.getFirestore();
            final var query = db.collection(COLLECTION_NAME).whereEqualTo(SICOM_CODE, notification.getSicomCode());
            final var future = query.get();
            final var docList = future.get().getDocuments();
            //update
            if (docList.size() > 0) {
                final var docRef = docList.get(0).getReference();
                docRef.update(collection);
            } // save
            else {
                final var docRef = db.collection(COLLECTION_NAME).document();
                docRef.set(collection);
            }
            return Mono.just(notification);
        } catch (Exception cause) {
            cause.printStackTrace();
            throw new BadRequestException("400", "Error saving or updating the register notification", null);
        }
    }

    @Override
    @SuppressWarnings("BlockingMethodInNonBlockingContext")
    public Mono<TwoFactorNotification> findBySicomCode(String sicomCode) {
        try {
            adapter.fireBaseInstance();
            final var db = FirestoreClient.getFirestore();
            final var query = db.collection(COLLECTION_NAME).whereEqualTo(SICOM_CODE, sicomCode);
            final var future = query.get();
            final var docList = future.get().getDocuments();

            if (docList.size() > 0) {
                final var result = docList.get(0).toObject(TwoFactorNotification.class);
                if (result.getDate().after(Date.from(Instant.now().minusSeconds(timeOut)))) {
                    return Mono.just(result);
                }
            }
        } catch (Exception cause) {
            cause.printStackTrace();
            throw new BadRequestException("400", "Error searching the register notification", null);
        }
        return Mono.empty();
    }
}
