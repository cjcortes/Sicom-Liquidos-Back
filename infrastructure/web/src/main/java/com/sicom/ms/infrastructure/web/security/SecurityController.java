package com.sicom.ms.infrastructure.web.security;

import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.model.users.User;
import com.sicom.ms.domain.usecase.users.LoginUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class SecurityController {

    private final LoginUserUseCase loginUserUseCase;

    @PostMapping("/login")
    public Mono<User> login(@RequestBody LoginRequest loginRequest) {
        return loginUserUseCase.login(loginRequest);
    }

    @GetMapping("/oe")
    public Mono<String> oe(Principal principal) {
        if (principal != null) {
            System.out.println(principal.getName());
        }
        return Mono.just("Oe");
    }
}