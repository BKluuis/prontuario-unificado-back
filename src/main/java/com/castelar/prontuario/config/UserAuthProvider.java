package com.castelar.prontuario.config;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.castelar.prontuario.model.Role;
import com.castelar.prontuario.model.User;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

/**
 * Serviço responsável pela geração do token JWT na forma Base64(ENCODER+AUTHENTICATION.SECRETKEY)
 */
@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    private final UserDetailsService userDetailsService;

    /** Obtém a chave secreta como propriedade do arquivo application.properties, "secret-key" é o valor padrão */
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    /**
     * Transforma a chave secreta em Base64
     * Executa apenas após o atributo "secretKey" for alimentado por injeção de dependência
     */
    @PostConstruct
    protected void init(){
        Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * A partir de um usuário, gera o token JWT.
     * 
     * iss: issuer, é o emissor;
     * aud: audience, é o destinatário;
     * exp: expiration time, é o tempo de expiração;
     * nbf: not before, é a data de validade;
     * iat: issued at, é a data de início de validade;
     * sub: subject, é o sujeito.
     * @param user Usuário que requisitou a geração do token
     * @return Token JWS com informações sobre a sessão do usuário
     */
    public String generateToken(User user){
        Date now = new Date();
        Date expire = new Date(now.getTime() + 3_600_000);  //Uma hora para o token expirar

        return JWT.create()
        .withIssuer(user.getLogin())                        // iss: issuer que é o emissor;
        .withIssuedAt(now)                                  // iat: issued at que é a data de início de validade; 
        .withExpiresAt(expire)                              // exp: expiration time que é o tempo de expiração;
        .withClaim("firstName", user.getFirstName())   // Payload: informações sobre autenticação e autorização que queremos transmitir através das requisições;
        .withClaim("lastName", user.getLastName())
        .withClaim("role", user.getRole().name())
        .sign(Algorithm.HMAC256(secretKey));                // Assinatura: última etapa, informa o algoritmo de codificação, usa as informações anteriores + a secret key
    }

    /**
     * Valida o token JWS e gera um objeto de autenticação do Spring com as informações do usuário
     * Este método é chamado quando é feita uma requisição do tipo "GET"
     * @param token Token JWS incluso na requisição
     * @return Autenticação do usuário
     */
    public Authentication validateToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);

        UserDetails userDetails = User.builder()
                    .login(decoded.getIssuer())
                    .firstName(decoded.getClaim("firstName").asString())
                    .lastName(decoded.getClaim("lastName").asString())
                    .role(decoded.getClaim("role").as(Role.class))
                    .build();

        System.out.println("Token validado, usuário: " + userDetails);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    /**
     * Valida o token JWS, garante que o usuário existe no banco de dados e gera um objeto de autenticação do Spring com as informações do usuário
     * Este método é chamado quando é feita uma requisição do tipo "POST"
     * @param token Token JWS incluso na requisição
     * @return Autenticação do usuário
     */
    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);

        UserDetails userDetails = userDetailsService.loadUserByUsername(decoded.getIssuer());

        System.out.println("Usuário encontrado e token validado, usuário: " + userDetails);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}