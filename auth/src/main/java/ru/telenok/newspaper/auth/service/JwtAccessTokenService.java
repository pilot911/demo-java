package ru.telenok.newspaper.auth.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtAccessTokenService extends JwtTokenService {
    String generateToken(UserDetails userDetails);

    Boolean validateTokenByUserDetailds(String token, UserDetails userDetails);

    String getSubject(String accessTokenCode);
}
