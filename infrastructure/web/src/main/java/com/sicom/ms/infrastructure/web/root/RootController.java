package com.sicom.ms.infrastructure.web.root;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RootController {

    @GetMapping
    public Mono<String> root() {
        return Mono.just("OK");
    }

}

