//package ru.telenok.newspaper.auth.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
//import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
//import org.springframework.boot.actuate.health.HealthEndpoint;
//import org.springframework.boot.actuate.info.InfoEndpoint;
//import org.springframework.boot.actuate.metrics.export.prometheus.PrometheusScrapeEndpoint;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import ru.telenok.newspaper.auth.service.JwtUserDetailsService;
//
//import java.util.List;
//
//@Configuration
//@ComponentScan(basePackages = {
//        "ru.telenok.newspaper.auth"
//})
//@EnableJpaRepositories(
//        basePackages = {"ru.telenok.newspaper.auth.dao"},
//        entityManagerFactoryRef = AuthConstants.Beans.ENTITY_MANAGER_FACTORY,
//        transactionManagerRef = AuthConstants.Beans.TRANSACTION_MANAGER
//)
//@EnableTransactionManagement
//@Order(1)
//public class AuthConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private JwtUserDetailsService userDetailsService;
//    @Autowired
//    private WebEndpointProperties webEndpointProperties;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http
//                .antMatcher(webEndpointProperties.getBasePath() + "/**")
//                    .authorizeRequests()
//                    .requestMatchers(EndpointRequest.to(InfoEndpoint.class, HealthEndpoint.class, PrometheusScrapeEndpoint.class)).permitAll()
//                    .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole(ru.telenok.newspaper.auth.config.AuthConstants.Roles.ROLE_ACTUATOR_ADMIN)
//                    .and()
//                    .httpBasic();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint() {
//        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
//        entryPoint.setRealmName("admin realm");
//        return entryPoint;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedMethods(List.of("*"));
//        configuration.setAllowedOrigins(List.of("*"));
//        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setAllowCredentials(true);
//        configuration.setMaxAge(3600L);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return new CorsFilter(source);
//    }
//}