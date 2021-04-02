package ru.telenok.newspaper.auth.service;

import io.jsonwebtoken.Claims;

import java.util.function.Function;

public interface JwtTokenService {

    Boolean isTokenExpired(String token);

    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);

    Claims getAllClaimsFromToken(String token);
}
