package com.sicom.ms.domain.usecase.twofactor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TwoFactorCommonTest {

    @InjectMocks
    private TwoFactorCommon twoFactorCommon;

    @Test
    void generateCode() {

    }

    @Test
    void buildTwoFactorUser() {
    }

    @Test
    void buildTwoFactorSecretCode() {
    }

    @Test
    void generateSecret() {
    }
}