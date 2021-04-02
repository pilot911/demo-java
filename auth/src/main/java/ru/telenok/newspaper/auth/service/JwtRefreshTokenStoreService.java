package ru.telenok.newspaper.auth.service;

import ru.telenok.newspaper.auth.model.JwtRefreshToken;
import ru.telenok.newspaper.common.model.User;

import java.util.Optional;

public interface JwtRefreshTokenStoreService {

    Optional<JwtRefreshToken> findByUserAndTokenCode(User user, JwtRefreshToken refreshToken);

    JwtRefreshToken storeRefreshToken(Long userId, String refreshToken);
}
