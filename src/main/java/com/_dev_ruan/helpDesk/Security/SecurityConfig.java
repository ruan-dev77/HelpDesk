package com._dev_ruan.helpDesk.Security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_MATCHES = { "/h2-console/**", "/auth/**" };

    private final UserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    private final Environment env;

    public SecurityConfig(UserDetailsService userDetailsService, JWTUtil jwtUtil, Environment env) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.env = env;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers(headers -> headers.frameOptions(FrameOptionsConfig::disable));
        }

        http.csrf(csrf -> csrf.disable())
            .addFilter(new JWTAuthenticationFilter(authenticationManager, jwtUtil))
            .addFilter(new JWTAuthorizationFilter(authenticationManager, jwtUtil, userDetailsService))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers(PUBLIC_MATCHES).permitAll()
            .anyRequest().authenticated());

        return http.build();
    }
}
