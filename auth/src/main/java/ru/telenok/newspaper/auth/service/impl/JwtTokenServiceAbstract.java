package ru.telenok.newspaper.auth.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClock;
import ru.telenok.newspaper.auth.service.JwtTokenService;

import java.util.Date;
import java.util.function.Function;

public abstract class JwtTokenServiceAbstract implements JwtTokenService {

    protected Clock clock = DefaultClock.INSTANCE;

    abstract protected long getExpirationTime();

    abstract protected String getSecret();

    public Boolean isTokenExpired(String token) {
        final Date expirationDateFromToken = getExpirationDateFromToken(token);
        return expirationDateFromToken.before(clock.now());
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(getSecret()).parseClaimsJws(token).getBody();
    }

    protected Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    protected Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    protected Date calculateExpirationDateToken(Date createdDate) {
        return new Date(createdDate.getTime() + getExpirationTime());
    }

    protected String getSubject(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
}
