package ru.telenok.newspaper.auth.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.telenok.newspaper.auth.service.JwtRefreshTokenService;

import java.util.Date;

@Service
public class JwtRefreshTokenServiceImpl extends JwtTokenServiceAbstract implements JwtRefreshTokenService {

    @Value("${jwt.signing.key.secret}")
    private String secret;

    @Value("${jwt.token.expiration.in.seconds.access}")
    private Long expiration;

    public String generateToken(String token) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDateToken(createdDate);

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);

        return Jwts.builder().setSubject(token).setClaims(claims).signWith(SignatureAlgorithm.HS512, getSecret()).compact();
    }

    public Boolean validateTokens(String accessTokenCode, String refreshTokenCode) {
        return accessTokenCode.equals(getSubject(refreshTokenCode));
    }

    @Override
    protected long getExpirationTime() {
        return expiration;
    }

    @Override
    protected String getSecret() {
        return secret;
    }
}
