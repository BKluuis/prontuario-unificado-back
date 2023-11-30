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

/**
 * Essa classe define um filtro de requisições que é garantido acontecer apenas uma vez por requisição HTTP
 */
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter{
    private final UserAuthProvider userAuthProvider;

    /**
     * Obtém o header "Authorization", extrai o token JWT fornecido e em seguida realiza a autenticação com ele
     * Caso o método da requisição seja "GET", simplesmente gera o token com base no usuário atual
     * Caso o método da requisição NÃO seja "GET", verifica se o usuário existe no banco de dados, então gera o token com base no usuário atual
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
                        SecurityContextHolder.getContext().setAuthentication(userAuthProvider.validateToken(splitAuth[1]));
                    } else {
                        SecurityContextHolder.getContext().setAuthentication(userAuthProvider.validateTokenStrongly(splitAuth[1]));
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
