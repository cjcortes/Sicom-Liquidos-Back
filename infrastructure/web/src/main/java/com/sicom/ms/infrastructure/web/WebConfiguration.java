package com.sicom.ms.infrastructure.web;

import org.springframework.context.annotation.Configuration;
<<<<<<< HEAD
import org.springframework.web.reactive.config.EnableWebFlux;
=======
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
>>>>>>> release


@Configuration
@EnableWebFlux
<<<<<<< HEAD
public class WebConfiguration {
=======
public class WebConfiguration implements WebFluxConfigurer {
    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().maxInMemorySize(25 * 1024 * 1024);
    }
>>>>>>> release
}
