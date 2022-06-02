package com.sicom.ms.domain.usecase.twofactor;

import com.sicom.ms.domain.model.twofactor.SecretCodeStatusEnum;
import com.sicom.ms.domain.model.twofactor.TwoFactorSecretCode;
import com.sicom.ms.domain.model.twofactor.TwoFactorUser;
import com.sicom.ms.domain.model.users.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TwoFactorCommonTest {

    @InjectMocks
    private TwoFactorCommon twoFactorCommon;

    @Test
    void generateCode() {
        StepVerifier.create(twoFactorCommon.generateCode())
                .consumeNextWith(code -> assertThat(code.length()).isEqualTo(6))
                .verifyComplete();
    }

    @Test
    void buildTwoFactorUser() {
        StepVerifier.create(twoFactorCommon.buildTwoFactorUser("user"))
                .consumeNextWith(code -> assertThat(code.getUser()).isNotNull())
                .verifyComplete();
    }

    @Test
    void buildTwoFactorSecretCode() {
        var twoFactorSecretCode = TwoFactorSecretCode.builder().build();
        StepVerifier.create(twoFactorCommon.buildTwoFactorSecretCode("65c1234081be42f8b96988e1a553be2b","610006000","1234356", SecretCodeStatusEnum.SENDING))
                .consumeNextWith(code -> assertThat(code).isNotNull())
                .verifyComplete();
    }

}