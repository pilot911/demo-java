package ru.telenok.newspaper.auth.exception;

import java.util.Set;

public class RegisterException extends WithErrorsAuthenticationException {
    public RegisterException(Set<String> errors, Throwable t) {
        super(errors, t);
    }
}
