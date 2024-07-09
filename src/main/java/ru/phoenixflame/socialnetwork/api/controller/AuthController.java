package ru.phoenixflame.socialnetwork.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.phoenixflame.socialnetwork.api.dto.request.AuthRequest;
import ru.phoenixflame.socialnetwork.api.dto.response.AuthResponse;
import ru.phoenixflame.socialnetwork.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return authService.loginUser(authRequest.getId(), authRequest.getPassword());
    }
}
