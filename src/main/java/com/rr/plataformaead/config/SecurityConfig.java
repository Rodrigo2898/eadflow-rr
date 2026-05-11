package com.rr.plataformaead.config;

import com.rr.plataformaead.security.AuthoritiesLoggingAfterFilter;
import com.rr.plataformaead.security.CsrfCookieFilter;
import com.rr.plataformaead.security.JWTValidateTokenFilter;
import com.rr.plataformaead.security.jwt.AuthEntryPointJwt;
import com.rr.plataformaead.security.jwt.CustomAcessDenied;
import com.rr.plataformaead.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();

        http.cors(Customizer.withDefaults());

        http.csrf(csrfConfig -> csrfConfig.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                    .ignoringRequestMatchers("/api/auth/pessoa-fisica/signup",
                            "/api/auth/pessoa-fisica/signin", "/api/auth/all")
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));

        http    .addFilterAfter(csrfCookieFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(authoritiesLoggingAfterFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtValidateTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/api/pessoa-fisica").hasAnyRole("ADMIN")
                                .requestMatchers("/api/pessoa-juridica").hasAnyRole("ADMIN")

                                .requestMatchers(HttpMethod.POST, "/api/curso").hasAnyRole("ADMIN", "INSTRUTOR")
                                .requestMatchers(HttpMethod.GET, "/api/curso").hasAnyRole("ADMIN", "INSTRUTOR", "ALUNO")

                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/error").permitAll()
                                .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());

        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new AuthEntryPointJwt()));
        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAcessDenied()));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public JWTValidateTokenFilter jwtValidateTokenFilter() {
        return new JWTValidateTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CsrfCookieFilter csrfCookieFilter() {
        return new CsrfCookieFilter();
    }

    @Bean
    public AuthoritiesLoggingAfterFilter authoritiesLoggingAfterFilter() {
        return new AuthoritiesLoggingAfterFilter();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept"));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return (CorsConfigurationSource) source;
    }
}
