package com.sicom.ms.infrastructure.web.security;

import com.sicom.ms.domain.model.users.LoginRequest;
import com.sicom.ms.domain.model.users.User;
import com.sicom.ms.domain.usecase.users.LoginUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
    public Flux<String> oe(Principal principal) {
        if (principal != null) {
            System.out.println(principal.getName());
        }
        List<String> data = new ArrayList<>();
        data.add("Value");
        data.add("Value");
        data.add("Value");
        return Flux.fromIterable(data);
    }
}