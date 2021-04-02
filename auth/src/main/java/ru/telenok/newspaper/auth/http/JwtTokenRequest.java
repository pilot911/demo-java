package ru.telenok.newspaper.auth.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtTokenRequest implements Serializable {
    private String username;
    private String password;
}
