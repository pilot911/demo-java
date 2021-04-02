package ru.telenok.newspaper.auth.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.telenok.newspaper.auth.model.JwtUserDetails;
import ru.telenok.newspaper.auth.service.JwtAccessTokenService;

import java.util.Date;
import java.util.HashMap;

@Service
public class JwtAccessTokenServiceImpl extends JwtTokenServiceAbstract implements JwtAccessTokenService {

    @Value("${jwt.signing.key.secret}")
    private String secret;

    @Value("${jwt.token.expiration.in.seconds.access}")
    private Long expiration;

    private UserDetailsService userDetailsService;

    public JwtAccessTokenServiceImpl(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(UserDetails userDetails) {
        final HashMap<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    public Boolean validateTokenByUserDetailds(String accessTokenCode, UserDetails userDetails) {
        final JwtUserDetails user = (JwtUserDetails) userDetails;
        final String username = getSubject(accessTokenCode);
        return username.equals(user.getUsername()) && !isTokenExpired(accessTokenCode);
    }

    @Override
    public String getSubject(String accessTokenCode) {
        return super.getSubject(accessTokenCode);
    }

    protected String doGenerateToken(HashMap<String, Object> claims, String subject) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDateToken(createdDate);
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, getSecret()).compact();
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
