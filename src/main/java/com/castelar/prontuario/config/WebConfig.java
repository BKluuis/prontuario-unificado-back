package com.castelar.prontuario.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.castelar.prontuario.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebConfig {
    private final IUserRepository userRepository;

    /**
     * Bean de configuração do CORS (Cross-origin Resource sharing ou Compartilhamento de recursos com origens diferentes)
     * Permite o acesso sobre esta API para o frontend (vide prontuario-unificado-front de @IagoGMacedo)
     * @return Filtro CORS configurado
     */


    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        //Builder da configuração do CORS
        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:4200/"));
        config.setAllowedHeaders(Arrays.asList(
            HttpHeaders.ACCEPT,
            HttpHeaders.CONTENT_TYPE,
            HttpHeaders.AUTHORIZATION
        ));
        config.setAllowedMethods(Arrays.asList(
            HttpMethod.GET.name(),
            HttpMethod.PUT.name(),
            HttpMethod.POST.name(),
            HttpMethod.DELETE.name(),
            HttpMethod.OPTIONS.name()
        ));
        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
