package ru.telenok.newspaper.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import ru.telenok.newspaper.common.CommonConstants;
import ru.telenok.newspaper.core.config.CoreConfiguration;
import ru.telenok.newspaper.auth.http.JwtTokenAuthorizationOncePerRequestFilter;
import ru.telenok.newspaper.auth.jwt.JwtUnAuthorizedResponseAuthentacationEntryPoint;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;
import static ru.telenok.newspaper.auth.config.AuthConstants.AuthController.V1.AUTHENTICATE_JWT_TOKENT_CREATE;

@Configuration
@ComponentScan(basePackages = {
        "ru.telenok.newspaper.auth"
})
@EnableJpaRepositories(
//        basePackages = {"ru.telenok.newspaper.auth.dao"},
        entityManagerFactoryRef = AuthConstants.Beans.ENTITY_MANAGER_FACTORY,
        transactionManagerRef = AuthConstants.Beans.TRANSACTION_MANAGER
)
@Import({CoreConfiguration.class})
public class JwtWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${news.webImageDirectory}")
    private String webImageDirectory;

    @Value("${news.webDocDirectory}")
    private String webDocDirectory;

    private JwtUnAuthorizedResponseAuthentacationEntryPoint jwtUnAuthorizedResponseAuthentacationEntryPoint;
    private UserDetailsService userDetailsService;
    private JwtTokenAuthorizationOncePerRequestFilter jwtTokenauthorizationOncePerRequestFilter;

    public JwtWebSecurityConfiguration(JwtUnAuthorizedResponseAuthentacationEntryPoint jwtUnAuthorizedResponseAuthentacationEntryPoint,
                                       UserDetailsService userDetailsService,
                                       JwtTokenAuthorizationOncePerRequestFilter jwtTokenauthorizationOncePerRequestFilter) {
        this.jwtUnAuthorizedResponseAuthentacationEntryPoint = jwtUnAuthorizedResponseAuthentacationEntryPoint;
        this.jwtTokenauthorizationOncePerRequestFilter = jwtTokenauthorizationOncePerRequestFilter;
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .cors().and()
                .exceptionHandling().authenticationEntryPoint(jwtUnAuthorizedResponseAuthentacationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .anyRequest().authenticated();

        httpSecurity
                .addFilterBefore(jwtTokenauthorizationOncePerRequestFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity
                .headers()
                .frameOptions().sameOrigin();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                .antMatchers(POST, AUTHENTICATE_JWT_TOKENT_CREATE)
                .antMatchers(OPTIONS, "/**")
                .antMatchers(GET, "/login", "/" + webImageDirectory + "/**", "/" + webDocDirectory + "/**");

    }
}
