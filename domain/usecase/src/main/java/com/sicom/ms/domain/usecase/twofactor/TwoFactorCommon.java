package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.common.UUIDOperations;
import com.sicom.ms.domain.model.di.Injectable;
import com.sicom.ms.domain.model.error.ApplicationException;
import com.sicom.ms.domain.model.twofactor.SecretCodeStatusEnum;
import com.sicom.ms.domain.model.twofactor.TwoFactorSecretCode;
import com.sicom.ms.domain.model.twofactor.TwoFactorUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Random;

@Injectable
public class TwoFactorCommon implements UUIDOperations {

    public Mono<String> generateCode() {
        return Mono.just(new Random())
                .flatMap(random -> Flux.range(1, 6)
                        .map(i -> String.valueOf(random.nextInt(10)))
                        .reduce((a, b) -> a + b));
    }

    public Mono<TwoFactorUser> buildTwoFactorUser(String user) {
        return Mono.just(TwoFactorUser.builder()
                .user(user)
                .uuid(randomUUID())
                .date(Date.from(Instant.now()))
                .build());
    }

    public Mono<TwoFactorSecretCode> buildTwoFactorSecretCode(String uuid, String user, String code, SecretCodeStatusEnum status) {
        return generateSecret(uuid, user, code)
                .map(secret -> TwoFactorSecretCode.builder()
                        .user(user)
                        .code(code)
                        .secret(secret)
                        .status(status.name())
                        .date(Date.from(Instant.now()))
                        .build());
    }

    public Mono<String> generateSecret(String uuid, String user, String code) {
        return Mono.just(uuid)
                .flatMap(key -> Mono.just(String.join(":", user, code))
                        .map(data -> {
                            try {
                                // Create key and cipher
                                Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
                                Cipher cipher = Cipher.getInstance("AES");
                                // encrypt the data
                                cipher.init(Cipher.ENCRYPT_MODE, aesKey);
                                byte[] encrypted = cipher.doFinal(data.getBytes());
                                return new String(encrypted);
                            } catch (Exception cause) {
                                throw new ApplicationException("two.factor.error.invalid", "Request payload is invalid", cause);
                            }
                        }));
    }
}
