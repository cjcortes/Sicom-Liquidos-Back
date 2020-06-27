package com.sicom.ms.infrastructure.web.root;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RootController {

    @GetMapping
    public Mono<String> rootGet() {
        return Mono.just("Root!");
    }

    @GetMapping("/parameters/")
    public Mono<String> parametersGet() {
        return Mono.just("Root!");
    }

}

