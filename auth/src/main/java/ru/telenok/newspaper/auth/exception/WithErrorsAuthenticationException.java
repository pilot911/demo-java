package ru.telenok.newspaper.auth.exception;

import org.springframework.security.core.AuthenticationException;

import java.util.HashSet;
import java.util.Set;

abstract class WithErrorsAuthenticationException extends AuthenticationException {

    private Set<String> errors = new HashSet<>();

    public WithErrorsAuthenticationException(Set<String> errors, Throwable t) {
        super("AuthenticationException", t);

        this.errors = errors;
    }

    public Set<String> getErrors() {
        return errors;
    }
}
