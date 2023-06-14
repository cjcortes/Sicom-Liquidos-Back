package com.sicom.ms.infrastructure.web;

import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

public class WebTestClientFactory {

    private WebTestClientFactory() {
    }

    public static WebTestClient create(ApplicationContext context,
                                       RestDocumentationContextProvider restDocumentation) {
        return WebTestClient.bindToApplicationContext(context)
                .configureClient()
                .filter(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint()))
                .build();
    }

}
