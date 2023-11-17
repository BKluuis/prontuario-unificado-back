package com.castelar.prontuario.config;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{
    private final UserAuthProvider userAuthProvider;

    /**
     * Obtém o header "Authorization", extrai o token fornecido e em seguida 
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(header != null){
            String[] splitAuth = header.split(" ");

            if(splitAuth.length == 2 && "Bearer".equals(splitAuth[0])){
                try {
                    if(request.getMethod().equals("GET")){
                        SecurityContextHolder.getContext().setAuthentication(userAuthProvider.validateToken(splitAuth[2]));
                    } else {
                        SecurityContextHolder.getContext().setAuthentication(userAuthProvider.validateTokenStrongly(splitAuth[2]));
                    }
                } catch (RuntimeException e) {
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }

        filterChain.doFilter(request, response); //Continua o "Chain of responsibilites"
    }
}
