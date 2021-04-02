package ru.telenok.newspaper.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.telenok.newspaper.auth.config.AuthConstants;
import ru.telenok.newspaper.auth.exception.RegisterException;
import ru.telenok.newspaper.auth.http.RegistrationRequest;

public interface RegistrationUpdateController {

    @RequestMapping(value = AuthConstants.AuthController.V1.AUTHENTICATE_REGISTER, method = RequestMethod.POST)
    ResponseEntity<?> registerCredentials(@RequestBody RegistrationRequest registerRequest) throws RegisterException;
}
