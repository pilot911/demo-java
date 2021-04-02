package ru.telenok.newspaper.auth.service.impl;

import org.springframework.stereotype.Service;
import ru.telenok.newspaper.auth.dao.JwtRefreshTokenRepository;
import ru.telenok.newspaper.auth.model.JwtRefreshToken;
import ru.telenok.newspaper.auth.service.JwtRefreshTokenStoreService;
import ru.telenok.newspaper.common.model.User;

import java.util.Optional;

@Service
public class JwtRefreshTokenStoreServiceImpl implements JwtRefreshTokenStoreService {

    private JwtRefreshTokenRepository jwtTokenRepository;

    public JwtRefreshTokenStoreServiceImpl(JwtRefreshTokenRepository jwtTokenRepository) {
        this.jwtTokenRepository = jwtTokenRepository;
    }

    @Override
    public Optional<JwtRefreshToken> findByUserAndTokenCode(User user, JwtRefreshToken token) {
        return jwtTokenRepository.findByUserAndTokenCode(user.getId(), token.getCode());
    }

    @Override
    public JwtRefreshToken storeRefreshToken(Long userId, String refreshTokenCode) {

        final JwtRefreshToken token = new JwtRefreshToken();
        token.setUserId(userId);
        token.setCode(refreshTokenCode);

        final Optional<JwtRefreshToken> tokenCodeAndUser = jwtTokenRepository.findByUserAndTokenCode(userId, refreshTokenCode);

        if (!tokenCodeAndUser.isPresent()) {
            jwtTokenRepository.save(token);
        }

        return token;
    }
}
