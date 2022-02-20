package com.sicom.ms.infrastructure.firebase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.SendResponse;
import com.sicom.ms.domain.model.fcm.Notification;
import com.sicom.ms.domain.model.fcm.NotificationGateway;
import com.sicom.ms.domain.model.fcm.UserDeviceNotification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Repository
public class NotificationGatewayAdapter implements NotificationGateway {

    private static final String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
    private static final String[] SCOPES = {MESSAGING_SCOPE};

    @Value("${app.fcm.service.key}")
    private String serviceAccountKey;
    @Value("${app.fcm.url}")
    private String baseUrl;

    @Override
    public Mono<String> sendMultiplePushNotifications(List<Notification> notifications) {
        List <Message> messages = new ArrayList<>();
        notifications.forEach(notification -> {
            var body = notification.getBody();

            if (body.length() > 200) {
                body = body.substring(199);
            }

            UserDeviceNotification userDeviceNotification = null;

            if(notification.getUser()!= null) {
                userDeviceNotification = getUserDevice(notification.getUser());

                if(userDeviceNotification != null && userDeviceNotification.getDeviceToken() != null) {
                    Message message = Message.builder()
                            .setNotification(com.google.firebase.messaging.Notification.builder()
                                    .setTitle(notification.getTitle())
                                    .setBody(body)
                                    .build())
                            .setToken(userDeviceNotification.getDeviceToken())
                            .build();
                    messages.add(message);
                }
            }
        });

        try {
            if(messages.size() > 0) {
                FirebaseMessaging.getInstance().sendAll(messages).getResponses().forEach(sendResponse -> {
                    SendResponse s = sendResponse;
                });
            }
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }

        return  Mono.just("OK");
    }

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
        notificationData.put("dueDate", Timestamp.of(request.getDueDate()));

        DocumentReference docRef = db.collection("notifications").document(UUID.randomUUID().toString());
        ApiFuture<WriteResult> result = docRef.set(notificationData);

        return Mono.just("OK");
    }

    @Override
    public Mono<String> readNotification(String userId, String notificationId) {
        getFirebaseInstance();

        Firestore db = FirestoreClient.getFirestore();

        DocumentReference docRef = db.collection("notifications").document(notificationId);
        docRef.update("users", FieldValue.arrayUnion(userId));

        return Mono.just("OK");
    }

    @Override
    public Mono<String> saveUserDevice(UserDeviceNotification userDeviceNotification, String userId) {
        getFirebaseInstance();

        Firestore db = FirestoreClient.getFirestore();

        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> userDeviceNotificationData = oMapper.convertValue(userDeviceNotification, Map.class);

        DocumentReference docRef = db.collection("usersDevice").document(userId);
        docRef.set(userDeviceNotificationData);

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

    private UserDeviceNotification getUserDevice(String userId)  {
        UserDeviceNotification userDeviceNotification = null;

        try {
            getFirebaseInstance();
            Firestore db = FirestoreClient.getFirestore();
            DocumentReference docRef = db.collection("usersDevice").document(userId);
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = null;
            document = future.get();
            if (document.exists()) {
                userDeviceNotification = document.toObject(UserDeviceNotification.class);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return userDeviceNotification;
    }


    private Mono<String> sendPushNotification(String token, Notification request) {
        var body = request.getBody();

        if (body.length() > 200) {
            body = body.substring(199);
        }

        var notification = NotificationDTO.builder()
                .title(request.getTitle())
                .body(body)
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
                .exchange().map(clientResponse -> {
                    ClientResponse clientResponse1 = clientResponse;
                    return "OK";
                });
    }
}
