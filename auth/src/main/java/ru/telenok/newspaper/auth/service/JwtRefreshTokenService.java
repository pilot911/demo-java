package ru.telenok.newspaper.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.telenok.newspaper.auth.model.JwtRefreshToken;
import ru.telenok.newspaper.common.model.User;

import java.util.Optional;

public interface JwtRefreshTokenService extends JwtTokenService {
    String generateToken(String accessTokenCode);

    Boolean validateTokens(String accessTokenCode, String refreshTokenCode);
}
