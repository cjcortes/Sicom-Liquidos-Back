package com.sicom.ms.domain.usecase.fcm;

import com.sicom.ms.domain.model.error.ApplicationErrorDetail;
import com.sicom.ms.domain.model.error.BadRequestException;
import com.sicom.ms.domain.model.fcm.Notification;
import com.sicom.ms.domain.model.fcm.NotificationGateway;
import com.sicom.ms.domain.usecase.validations.ObjectValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;

import static com.sicom.ms.domain.usecase.fcm.SendNotificationPushRules.NOTIFICATION_REQUEST_RULES;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SendPushNotificationUseCaseTest {

    @Spy
    private final ObjectValidator objectValidator = new ObjectValidator();

    @Mock
    private NotificationGateway notificationGateway;

    @InjectMocks
    private SendPushNotificationUseCase sendPushNotificationUseCase;

    @Test
    void sendShouldThrowBadExceptionIfRequestIsInvalid() {
        var request = Notification.builder().build();

        assertThatThrownBy(() -> sendPushNotificationUseCase.send(request))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("send bad request")
                .hasFieldOrPropertyWithValue("code", "send.error.badRequest")
                .satisfies(this::checkBadRequestDetails);

        verify(objectValidator).validate(request, NOTIFICATION_REQUEST_RULES);
    }

    @Test
    void loginShouldSendItFromRepository() {

        var request = Notification.builder()
                .title("title")
                .body("body")
                .dueDate(new Date())
                .build();
        String expected = "OK";

        when(notificationGateway.sendPushNotification(request))
                .thenReturn(Mono.just(expected));

        StepVerifier.create(sendPushNotificationUseCase.send(request))
                .expectNext(expected)
                .verifyComplete();

        verify(notificationGateway).sendPushNotification(request);
    }

    private void checkBadRequestDetails(Throwable error) {
        var badRequestException = (BadRequestException) error;

        assertThat(badRequestException.getDetails())
                .extracting(ApplicationErrorDetail::getCode, ApplicationErrorDetail::getMessage)
                .contains(
                        tuple("notification.titleCannotBeNull",
                                "title cannot be null"),
                        tuple("notification.bodyCannotBeNull",
                                "body cannot be null"));
    }
}
