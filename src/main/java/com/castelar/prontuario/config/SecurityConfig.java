package com.castelar.prontuario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.castelar.prontuario.model.Role;

@RequiredArgsConstructor
@Configuration
@EnableWebMvc
public class SecurityConfig {

    private final UserAuthProvider userAuthProvider;

    /**
     * Bean necessário para realizar o request matching em MVC
     * @param introspector
     * @return
     */
    @Bean
    public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector){
        return new MvcRequestMatcher.Builder(introspector);
    }

    /**
     * Adiciona o filtro JWT e especifica quais endpoints estão liberados para usuários não-autenticados (/login e /register)
     * Bloqueia todos os outros endpoints para apenas usuários autenticados
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
            .addFilterBefore(new JwtFilter(userAuthProvider), BasicAuthenticationFilter.class) //Adiciona o filtro JWT antes do filtro de autenticação básico
            .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests((requests) -> requests.requestMatchers(mvc.pattern("/login"), mvc.pattern("/register"), new AntPathRequestMatcher("/h2-console/**")) //OBS: o h2 tem que ser através do antmatcher
            .permitAll()
            .requestMatchers(mvc.pattern("api/hemogram**")).hasRole(Role.PROFESSIONAL.name())
            .anyRequest().authenticated()
        );
        return http.build();
    }
}
