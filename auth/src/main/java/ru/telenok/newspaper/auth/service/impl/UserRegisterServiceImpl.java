package ru.telenok.newspaper.auth.service.impl;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.telenok.newspaper.auth.service.UserRegisterService;
import ru.telenok.newspaper.common.model.User;
import ru.telenok.newspaper.auth.config.AuthConstants;
import ru.telenok.newspaper.core.service.admin.UserServiceAdmin;
import ru.telenok.newspaper.auth.exception.RegisterException;
import ru.telenok.newspaper.auth.http.RegistrationRequest;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class UserRegisterServiceImpl implements UserRegisterService {

    private final static int usernameLengthMin = 6;
    private final static int usernameLengthMax = 12;

    private final static int passwordLengthMin = 6;
    private final static HashSet errors = new HashSet<String>();

    private UserServiceAdmin userService;
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    public void validate(RegistrationRequest registerRequest) {
        validate(registerRequest.getUsername(), registerRequest.getPassword());
    }

    @Override
    public void validate(String username, String password) {
        try {
            validateUsername(username);
        } catch (RegisterException e) {
            errors.addAll(e.getErrors());
        }

        try {
            validatePassword(password);
        } catch (RegisterException e) {
            errors.addAll(e.getErrors());
        }

        if (CollectionUtils.isEmpty(errors)) {
            throw new RegisterException(errors, new BadCredentialsException("Username is wrong"));
        }
    }

    @Override
    public void register(RegistrationRequest registerRequest) {
        register(registerRequest.getUsername(), registerRequest.getPassword());
    }

    @Override
    public void register(String username, String password) {

        final User user = new User();
        user.setUsername(username);
        user.setUsername(passwordEncoder.encode(password));
        user.setActive(true);

        userService.save(user);
    }

    private void validatePassword(String password) {
        if (StringUtils.isEmpty(password)) {
            errors.add(AuthConstants.Error.Password.IS_EMPTY);
        }

        if (password.length() < passwordLengthMin) {
            errors.add(AuthConstants.Error.Password.TOO_SHORT);
        }

        if (!errors.isEmpty()) {
            throw new RegisterException(errors, new BadCredentialsException("Password is wrong"));
        }
    }

    private void validateUsername(String username) throws BadCredentialsException {

        final HashSet<String> errors = new HashSet<>();

        if (StringUtils.isEmpty(username)) {
            errors.add(AuthConstants.Error.Username.IS_EMPTY);
            throw new BadCredentialsException("Username is wrong");
        }

        if (username.length() < usernameLengthMin) {
            errors.add(AuthConstants.Error.Username.TOO_SHORT);
        }

        if (username.length() > usernameLengthMax) {
            errors.add(AuthConstants.Error.Username.TOO_LONG);
        }

        try {
            userDetailsService.loadUserByUsername(username);
            errors.add(AuthConstants.Error.Username.EXISTS);
        } catch (UsernameNotFoundException e) {}

        if (!errors.isEmpty()) {
            throw new RegisterException(errors, new BadCredentialsException("Username is wrong"));
        }
    }
}
