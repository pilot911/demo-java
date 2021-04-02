package ru.telenok.newspaper.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.telenok.newspaper.auth.exception.LoginException;
import ru.telenok.newspaper.auth.http.JwtTokenRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

import static ru.telenok.newspaper.auth.config.AuthConstants.AuthController.V1.AUTHENTICATE_JWT_TOKENT_CREATE;
import static ru.telenok.newspaper.auth.config.AuthConstants.AuthController.V1.AUTHENTICATE_JWT_TOKENT_REFRESH;

public interface LoginUpdateController {

    @PostMapping(value = AUTHENTICATE_JWT_TOKENT_CREATE)
    ResponseEntity<?> createAuthenticationtoken(@RequestBody JwtTokenRequest authenticationRequest) throws UsernameNotFoundException;

    @PostMapping(value = AUTHENTICATE_JWT_TOKENT_REFRESH)
    ResponseEntity<?> refreshTokens(HttpServletRequest request);

    @ExceptionHandler({LoginException.class})
    ResponseEntity<Set<String>> handlerAuthenticationException(LoginException e);
}
