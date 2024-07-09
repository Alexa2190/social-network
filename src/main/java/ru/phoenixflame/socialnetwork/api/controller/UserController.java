package ru.phoenixflame.socialnetwork.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException.*;
import ru.phoenixflame.socialnetwork.api.dto.request.UserRequest;
import ru.phoenixflame.socialnetwork.api.dto.response.ErrorResponse;
import ru.phoenixflame.socialnetwork.api.dto.response.FullUserResponse;
import ru.phoenixflame.socialnetwork.api.dto.response.UserResponse;
import ru.phoenixflame.socialnetwork.service.UserService;

import javax.servlet.UnavailableException;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/user/get/{id}")
    public FullUserResponse getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/user/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse registerUser(@RequestBody UserRequest userRequest) {
        return userService.addUser(userRequest);
    }

    @ExceptionHandler({InternalServerError.class})
    public ResponseEntity<ErrorResponse> handleInternalException(InternalServerError ex) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({UnavailableException.class})
    public ResponseEntity<ErrorResponse> handleUnAvailableException(UnavailableException ex) {
        ErrorResponse message = new ErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(message, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
