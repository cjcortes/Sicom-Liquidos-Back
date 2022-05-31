package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.di.Injectable;
import com.sicom.ms.domain.model.error.ApplicationException;
import com.sicom.ms.domain.model.twofactor.SecretCodeStatusEnum;
import com.sicom.ms.domain.model.twofactor.TwoFactorSecretCode;
import com.sicom.ms.domain.model.twofactor.TwoFactorUser;
import com.sicom.ms.domain.model.twofactor.UserStatusEnum;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Injectable
public class TwoFactorCommon {

    public Mono<String> generateCode() {
        return Mono.just(new Random())
                .map(random -> Flux.range(1, 6)
                        .map(i -> String.valueOf(random.nextInt(10)))
                        .toStream()
                        .collect(Collectors.joining()));
    }

    public Mono<TwoFactorUser> buildTwoFactorUser(String user, UserStatusEnum status) {
        return Mono.just(TwoFactorUser.builder()
                .user(user)
                .uuid(UUID.randomUUID().toString())
                .status(status.toString())
                .date(Date.from(Instant.now()))
                .build());
    }

    public Mono<TwoFactorSecretCode> buildTwoFactorSecretCode(String uuid, String user, String code, SecretCodeStatusEnum status) {
        return generateSecret(uuid, user, code)
                .map(secret -> TwoFactorSecretCode.builder()
                        .user(user)
                        .secret(secret)
                        .status(status.toString())
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
