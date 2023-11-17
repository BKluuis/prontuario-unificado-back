package com.castelar.prontuario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebMvc
public class SecurityConfig {

    private final UserAuthProvider userAuthProvider;

    /**
     * 
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(new JwtFilter(userAuthProvider), BasicAuthenticationFilter.class) //Adiciona o filtro JWT antes do filtro de autenticação básico
            .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests((requests) -> requests.requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
            .anyRequest().authenticated()
        );
        return http.build();
    }
}
