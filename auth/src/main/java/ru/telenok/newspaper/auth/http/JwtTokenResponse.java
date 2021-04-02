package ru.telenok.newspaper.auth.http;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class JwtTokenResponse implements Serializable {
    private String accessTokenCode;
    private String refreshTokenCode;

    public JwtTokenResponse(String accessTokenCode, String refreshTokenCode) {
        this.accessTokenCode = accessTokenCode;
        this.refreshTokenCode = refreshTokenCode;
    }
}
