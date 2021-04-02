package ru.telenok.newspaper.auth.controller.impl.update;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.telenok.newspaper.auth.controller.LoginUpdateController;
import ru.telenok.newspaper.auth.exception.LoginException;
import ru.telenok.newspaper.auth.http.JwtTokenRequest;
import ru.telenok.newspaper.auth.http.JwtTokenResponse;
import ru.telenok.newspaper.auth.model.JwtUserDetails;
import ru.telenok.newspaper.auth.service.JwtAccessTokenService;
import ru.telenok.newspaper.auth.service.JwtRefreshTokenService;
import ru.telenok.newspaper.auth.service.JwtRefreshTokenStoreService;
import ru.telenok.newspaper.auth.service.JwtUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class LoginUpdateControllerImpl implements LoginUpdateController {

    @Value("${jwt.http.request.header.token.refresh}")
    private String tokenHeaderRefreshToken;
    @Value("${jwt.http.request.header.token.access}")
    private String tokenHeaderAccessToken;

    private AuthenticationManager authenticationManager;
    private JwtUserDetailsService userDetailsService;
    private JwtRefreshTokenStoreService jwtRefreshTokenStoreService;
    private JwtRefreshTokenService jwtRefreshTokenService;
    private JwtAccessTokenService jwtAccessTokenService;

    public LoginUpdateControllerImpl(AuthenticationManager authenticationManager, JwtUserDetailsService userDetailsService,
                                     JwtRefreshTokenStoreService jwtRefreshTokenStoreService, JwtRefreshTokenService jwtRefreshTokenService,
                                     JwtAccessTokenService jwtAccessTokenService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtRefreshTokenStoreService = jwtRefreshTokenStoreService;
        this.jwtRefreshTokenService = jwtRefreshTokenService;
        this.jwtAccessTokenService = jwtAccessTokenService;
    }

    public ResponseEntity<?> createAuthenticationtoken(@RequestBody JwtTokenRequest authenticationRequest) throws UsernameNotFoundException {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final JwtUserDetails userDetails = (JwtUserDetails) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String accessTokenCode = jwtAccessTokenService.generateToken(userDetails);
        final String refreshTokenCode = jwtRefreshTokenService.generateToken(accessTokenCode);

        jwtRefreshTokenStoreService.storeRefreshToken(userDetails.getId(), refreshTokenCode);

        return ResponseEntity.ok(new JwtTokenResponse(accessTokenCode, refreshTokenCode));
    }

    public ResponseEntity<?> refreshTokens(HttpServletRequest request) {
        final String refreshTokenHeader = request.getHeader(tokenHeaderRefreshToken);
        final String accessTokenHeader = request.getHeader(tokenHeaderAccessToken);

        final String refreshTokenCode = refreshTokenHeader.substring(7);
        final String accessTokenCode = accessTokenHeader.substring(7);

        if (!jwtAccessTokenService.isTokenExpired(accessTokenCode)
            && !jwtRefreshTokenService.isTokenExpired(refreshTokenCode)
            && jwtRefreshTokenService.validateTokens(accessTokenCode, refreshTokenCode)) {

            final JwtUserDetails userDetails = (JwtUserDetails) userDetailsService.loadUserByUsername(jwtAccessTokenService.getSubject(accessTokenCode));


            final String accessTokenCodeNew = jwtAccessTokenService.generateToken(userDetails);
            final String refreshedTokenCodeNew = jwtRefreshTokenService.generateToken(accessTokenCode);
            return ResponseEntity.ok(new JwtTokenResponse(accessTokenCodeNew, refreshedTokenCodeNew));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Set<String>> handlerAuthenticationException(LoginException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getErrors());
    }

    private void authenticate(String username, String password) {

        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new LoginException(Set.of("USER_DiSABLED"), e);
        } catch (BadCredentialsException e) {
            throw new LoginException(Set.of("INVALID_CREDENTIALS"), e);
        }
    }
}
