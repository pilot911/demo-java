package ru.telenok.newspaper.auth.controller.impl.update;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.telenok.newspaper.auth.config.AuthConstants;
import ru.telenok.newspaper.auth.controller.RegistrationUpdateController;
import ru.telenok.newspaper.auth.exception.RegisterException;
import ru.telenok.newspaper.auth.http.RegistrationRequest;
import ru.telenok.newspaper.auth.service.impl.UserRegisterServiceImpl;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RegistrationUpdateControllerImpl implements RegistrationUpdateController {

    private UserRegisterServiceImpl userRegisterService;

    public ResponseEntity<?> registerCredentials(@RequestBody RegistrationRequest registerRequest) throws RegisterException {
        userRegisterService.validate(registerRequest);
        userRegisterService.register(registerRequest);
        return ResponseEntity.ok(AuthConstants.Register.SUCCESS);
    }
}
