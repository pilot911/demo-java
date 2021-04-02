package ru.telenok.newspaper.auth.service;

import ru.telenok.newspaper.auth.http.RegistrationRequest;

public interface UserRegisterService {

    void validate(RegistrationRequest registerRequest);

    void validate(String username, String password);

    void register(RegistrationRequest registerRequest);

    void register(String username, String password);
}
