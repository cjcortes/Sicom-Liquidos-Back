package com.sicom.ms.infrastructure.web;

import com.sicom.ms.domain.model.error.*;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;


@Component
public class ApplicationExceptionHandler extends AbstractErrorWebExceptionHandler {

    private static final Map<Class<? extends ApplicationException>, HttpStatus> EXCEPTION_STATUS_MAPPING = Map.of(
            NotFoundException.class, HttpStatus.NOT_FOUND,
            BadRequestException.class, HttpStatus.BAD_REQUEST,
            UnauthorizedException.class, HttpStatus.UNAUTHORIZED
    );


    public ApplicationExceptionHandler(ErrorAttributes errorAttributes,
                                       ResourceProperties resourceProperties,
                                       ApplicationContext applicationContext,
                                       ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, resourceProperties, applicationContext);
        setMessageWriters(serverCodecConfigurer.getWriters());
        setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(
            ErrorAttributes errorAttributes) {
        return RouterFunctions.route(
                RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(
            ServerRequest request) {
        var error = getError(request);

        var body = error instanceof ApplicationException ?
                ApplicationError.fromApplicationException((ApplicationException) error) :
                ApplicationError.INTERNAL_ERROR;

        var statusCode = EXCEPTION_STATUS_MAPPING.getOrDefault(error.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);

        return ServerResponse.status(statusCode)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body);
    }

}
