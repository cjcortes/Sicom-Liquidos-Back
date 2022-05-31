package com.sicom.ms.infrastructure.firebase.twofactor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.cloud.FirestoreClient;
import com.sicom.ms.domain.model.twofactor.TwoFactorSecretCode;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorSecretCodeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class TwoFactorSecretCodeGatewayAdapter implements TwoFactorSecretCodeGateway {

    private final FireBaseInstanceAdapter adapter;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String COLLECTION_NAME = "two-factor-secret-code";
    private static final String SECRET = "secret";

    @Override
    @SuppressWarnings({"unchecked", "Duplicates", "BlockingMethodInNonBlockingContext"})
    public Mono<TwoFactorSecretCode> saveOrUpdate(TwoFactorSecretCode secretCode) {
        try {
            final Map<String, Object> collection = objectMapper.convertValue(secretCode, Map.class);

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.empty();
    }

    @Override
    @SuppressWarnings("BlockingMethodInNonBlockingContext")
    public Mono<TwoFactorSecretCode> findBySecret(String secret) {
        try {
            adapter.fireBaseInstance();
            final var db = FirestoreClient.getFirestore();
            final var query = db.collection(COLLECTION_NAME).whereEqualTo(SECRET, secret);
            final var future = query.get();
            final var docList = future.get().getDocuments();
            if (docList.size() > 0) {
                final var result = docList.get(0).toObject(TwoFactorSecretCode.class);
                return Mono.just(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.empty();
    }
}
