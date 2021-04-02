package ru.telenok.newspaper.auth.exception;

import java.util.Set;

public class LoginException extends WithErrorsAuthenticationException {
    public LoginException(Set<String> errors, Throwable t) {
        super(errors, t);
    }
}
