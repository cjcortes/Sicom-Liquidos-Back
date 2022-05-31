package com.sicom.ms.infrastructure.firebase.twofactor;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.sicom.ms.domain.model.di.Injectable;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Injectable
public class FireBaseInstanceAdapter {

    @Value("${app.fcm.service.key}")
    private String serviceAccountKey;

    public void fireBaseInstance() {
        final var inputStream = ClassLoader.getSystemResourceAsStream(serviceAccountKey);
        try {
            assert inputStream != null;
            final var credentials = GoogleCredentials.fromStream(inputStream);
            final var options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build();
            if (FirebaseApp.getApps().size() == 0) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
