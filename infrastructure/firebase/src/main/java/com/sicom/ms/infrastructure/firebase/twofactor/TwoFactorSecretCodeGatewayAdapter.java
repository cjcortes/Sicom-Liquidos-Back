package com.sicom.ms.infrastructure.firebase.twofactor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.Timestamp;
import com.google.firebase.cloud.FirestoreClient;
import com.sicom.ms.domain.model.error.ApplicationException;
import com.sicom.ms.domain.model.twofactor.SecretCodeStatusEnum;
import com.sicom.ms.domain.model.twofactor.TwoFactorSecretCode;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorSecretCodeGateway;
import lombok.RequiredArgsConstructor;
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
    private static final String SECRET = "secret";
    private static final String STATUS = "status";

    @Override
    @SuppressWarnings({"unchecked", "Duplicates", "BlockingMethodInNonBlockingContext"})
    public Mono<TwoFactorSecretCode> saveOrUpdate(TwoFactorSecretCode secretCode) {
        try {
            final Map<String, Object> collection = objectMapper.convertValue(secretCode, Map.class);
            collection.put("date", Timestamp.of(secretCode.getDate()));

            adapter.fireBaseInstance();
            final var db = FirestoreClient.getFirestore();
            final var query = db.collection(COLLECTION_NAME).whereEqualTo(SECRET, secretCode.getSecret());
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
            throw new ApplicationException("two.factor.error.invalid", "Error saving or updating secretCode", cause);
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
                if (result.getDate().after(Date.from(Instant.now().minusSeconds(60)))) {
                    return Mono.just(result);
                }
            }
        } catch (Exception cause) {
            throw new ApplicationException("two.factor.error.invalid", "Error looking for the secret", cause);
        }
        return Mono.empty();
    }
}
