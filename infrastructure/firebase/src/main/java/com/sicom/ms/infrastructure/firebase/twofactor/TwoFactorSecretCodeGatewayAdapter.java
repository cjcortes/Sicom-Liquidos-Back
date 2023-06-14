package com.sicom.ms.infrastructure.firebase.twofactor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.Timestamp;
import com.google.firebase.cloud.FirestoreClient;
import com.sicom.ms.domain.model.error.BadRequestException;
import com.sicom.ms.domain.model.twofactor.SecretCodeStatusEnum;
import com.sicom.ms.domain.model.twofactor.TwoFactorSecretCode;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorSecretCodeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class TwoFactorSecretCodeGatewayAdapter implements TwoFactorSecretCodeGateway {

    private final FireBaseInstanceAdapter adapter;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String COLLECTION_NAME = "two-factor-secret-code";
    private static final String USER = "user";
    private static final String SECRET = "secret";
    private static final String STATUS = "status";

    @Value("${app.two-factor.secret-code.timeout}")
    private Integer secretCodeTimeOut;

    @Override
    @SuppressWarnings({"unchecked", "Duplicates", "BlockingMethodInNonBlockingContext"})
    public Mono<TwoFactorSecretCode> saveOrUpdate(TwoFactorSecretCode secretCode) {
        try {
            final Map<String, Object> collection = objectMapper.convertValue(secretCode, Map.class);
            collection.put("date", Timestamp.of(secretCode.getDate()));

            adapter.fireBaseInstance();
            final var db = FirestoreClient.getFirestore();
            final var query = db.collection(COLLECTION_NAME).whereEqualTo(USER, secretCode.getUser());
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
            return Mono.just(secretCode);
        } catch (Exception cause) {
            cause.printStackTrace();
            throw new BadRequestException("400", "Error saving or updating the secret-code", null);
        }
    }

    @Override
    @SuppressWarnings("BlockingMethodInNonBlockingContext")
    public Mono<TwoFactorSecretCode> findBySecret(String secret) {
        try {
            adapter.fireBaseInstance();
            final var db = FirestoreClient.getFirestore();
            final var query = db.collection(COLLECTION_NAME).whereEqualTo(SECRET, secret)
                    .whereEqualTo(STATUS, SecretCodeStatusEnum.SENDING.name());
            final var future = query.get();
            final var docList = future.get().getDocuments();

            if (docList.size() > 0) {
                final var result = docList.get(0).toObject(TwoFactorSecretCode.class);
                if (result.getDate().after(Date.from(Instant.now().minusSeconds(secretCodeTimeOut)))) {
                    return Mono.just(result);
                }
            }
        } catch (Exception cause) {
            cause.printStackTrace();
            throw new BadRequestException("400", "Error searching the secret-code", null);
        }
        return Mono.empty();
    }
}