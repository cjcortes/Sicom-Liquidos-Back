package com.sicom.ms.infrastructure.firebase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.sicom.ms.domain.model.fcm.Notification;
import com.sicom.ms.domain.model.fcm.NotificationGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class NotificationGatewayAdapter implements NotificationGateway {

    private static final String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
    private static final String[] SCOPES = {MESSAGING_SCOPE};

    @Value("${app.fcm.service.key}")
    private String serviceAccountKey;
    @Value("${app.fcm.url}")
    private String baseUrl;

    @Override
    public Mono<String> sendPushNotification(Notification request) {
        var inputStream = ClassLoader.getSystemResourceAsStream(serviceAccountKey);
        GoogleCredentials credentials;
        try {
            credentials = GoogleCredentials
                    .fromStream(inputStream)
                    .createScoped(SCOPES);
            credentials.refreshIfExpired();
            AccessToken accessToken = credentials.getAccessToken();
            return sendPushNotification(accessToken.getTokenValue(), request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Mono.just("OK");
    }

    @Override
    public Mono<String> saveNotification(Notification request) {
        getFirebaseInstance();

        Firestore db = FirestoreClient.getFirestore();

        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> notificationData = oMapper.convertValue(request, Map.class);

        DocumentReference docRef = db.collection("notifications").document(UUID.randomUUID().toString());
        ApiFuture<WriteResult> result = docRef.set(notificationData);

        return Mono.just("OK");
    }

    private void getFirebaseInstance() {
        var inputStream = ClassLoader.getSystemResourceAsStream(serviceAccountKey);
        GoogleCredentials credentials;
        try {
            credentials = GoogleCredentials
                    .fromStream(inputStream);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            if(FirebaseApp.getApps().size() == 0){
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Mono<String> sendPushNotification(String token, Notification request) {
        var notification = NotificationDTO.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .build();
        var androidNotification = AndroidNotificationDTO.builder()
                .click_action("FLUTTER_NOTIFICATION_CLICK")
                .build();
        var androidConfig = AndroidConfigDTO.builder()
                .notification(androidNotification)
                .build();
        var message = MessageDTO.builder()
                .topic("news")
                .notification(notification)
                .android(androidConfig)
                .build();
        var pushNotification = PushNotificacionData.builder()
                .message(message)
                .build();

        var client = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return client.post()
                .uri("/messages:send")
                .bodyValue(pushNotification)
                .exchange().map(clientResponse -> "OK");
    }
}
