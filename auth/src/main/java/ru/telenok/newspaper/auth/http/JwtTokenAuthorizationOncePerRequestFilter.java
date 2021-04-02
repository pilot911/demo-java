package ru.telenok.newspaper.auth.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.telenok.newspaper.auth.service.JwtAccessTokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtTokenAuthorizationOncePerRequestFilter extends OncePerRequestFilter {

    @Value("${jwt.http.request.header.token.access}")
    private String accessTokenHeader;

    private UserDetailsService userDetailsService;
    private JwtAccessTokenService jwtAccessTokenService;

    public JwtTokenAuthorizationOncePerRequestFilter(UserDetailsService userService, JwtAccessTokenService jwtAccessTokenService) {
        this.userDetailsService = userService;
        this.jwtAccessTokenService = jwtAccessTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.debug("Authentication request for '{}'", request.getRequestURL());

        final String requestTokenHeader = request.getHeader(accessTokenHeader);

        String jwtUsername = null;
        String jwtAccessTokenCode = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtAccessTokenCode = requestTokenHeader.substring(7);
            jwtUsername = jwtAccessTokenService.getSubject(jwtAccessTokenCode);
        } else {
            log.warn("JWT_TOKEN_DOES_NOT_START_WITH_BEARER_STRING");
        }

        log.debug("JWT_TOKEN_USERNAME_VALUE '{}'", jwtUsername);

        if (jwtUsername != null
                && (
                        SecurityContextHolder.getContext().getAuthentication() == null
                                || SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUsername);

            if (jwtAccessTokenService.validateTokenByUserDetailds(jwtAccessTokenCode, userDetails)) {

                final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
