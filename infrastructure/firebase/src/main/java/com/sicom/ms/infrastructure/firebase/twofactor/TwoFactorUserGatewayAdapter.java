package com.sicom.ms.infrastructure.firebase.twofactor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.cloud.FirestoreClient;
import com.sicom.ms.domain.model.twofactor.TwoFactorUser;
import com.sicom.ms.domain.model.twofactor.UserStatusEnum;
import com.sicom.ms.domain.model.twofactor.gateway.TwoFactorUserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class TwoFactorUserGatewayAdapter implements TwoFactorUserGateway {

    private final FireBaseInstanceAdapter adapter;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String COLLECTION_NAME = "two-factor-user";
    private static final String USER = "user";
    private static final String STATUS = "status";

    @Override
    @SuppressWarnings({"unchecked", "Duplicates", "BlockingMethodInNonBlockingContext"})
    public Mono<TwoFactorUser> saveOrUpdate(TwoFactorUser user) {
        try {
            final Map<String, Object> collection = objectMapper.convertValue(user, Map.class);

            adapter.fireBaseInstance();
            final var db = FirestoreClient.getFirestore();
            final var query = db.collection(COLLECTION_NAME).whereEqualTo(USER, user.getUser());
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
    public Mono<TwoFactorUser> findByUser(String user) {
        try {
            adapter.fireBaseInstance();
            final var db = FirestoreClient.getFirestore();
            final var query = db.collection(COLLECTION_NAME).whereEqualTo(USER, user);
            final var future = query.get();
            final var docList = future.get().getDocuments();
            if (docList.size() > 0) {
                final var result = docList.get(0).toObject(TwoFactorUser.class);
                return Mono.just(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.empty();
    }

    @Override
    @SuppressWarnings("BlockingMethodInNonBlockingContext")
    public Mono<TwoFactorUser> findBy(String user, UserStatusEnum status) {
        try {
            adapter.fireBaseInstance();
            final var db = FirestoreClient.getFirestore();
            final var query = db.collection(COLLECTION_NAME).whereEqualTo(USER, user).whereEqualTo(STATUS, status);
            final var future = query.get();
            final var docList = future.get().getDocuments();
            if (docList.size() > 0) {
                final var result = docList.get(0).toObject(TwoFactorUser.class);
                return Mono.just(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.empty();
    }
}
