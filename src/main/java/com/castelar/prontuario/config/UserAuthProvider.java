package com.castelar.prontuario.config;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.castelar.prontuario.dto.UserDTO;
import com.castelar.prontuario.exception.AppException;
import com.castelar.prontuario.mapper.UserMapper;
import com.castelar.prontuario.model.User;
import com.castelar.prontuario.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

/**
 * Serviço responsável pela geração do token JWT na forma Base64(ENCODER+AUTHENTICATION.SECRETKEY)
 */
@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /** Obtém a chave secreta como propriedade do arquivo application.properties */
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    /**
     *  Transforma a chave secreta em Base64
     */
    @PostConstruct
    protected void init(){
        Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * iss: issuer que é o emissor;
     * aud: audience que é o destinatário;
     * exp: expiration time que é o tempo de expiração;
     * nbf: not before que é a data de validade;
     * iat: issued at que é a data de início de validade;
     * sub: subject que é o sujeito.
     * @param user
     * @return
     */
    public String generateToken(UserDTO user){
        Date now = new Date();
        Date expire = new Date(now.getTime() + 3_600_000);  //Uma hora para o token expirar

        return JWT.create()
        .withIssuer(user.getLogin())                        // iss: issuer que é o emissor;
        .withIssuedAt(now)                                  // iat: issued at que é a data de início de validade; 
        .withExpiresAt(expire)                              // exp: expiration time que é o tempo de expiração;
        .withClaim("firstName", user.getFirstName())   // Payload: informações sobre autenticação e autorização que queremos transmitir através das requisições;
        .withClaim("lastName", user.getLastName())     
        .sign(Algorithm.HMAC256(secretKey));                // Assinatura: última etapa, informa o algoritmo de codificação, usa as informações anteriores + a secret key
    }

    /**
     * 
     * @param token
     * @return Bean de autenticação
     */
    public Authentication validateToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);

        UserDTO user = UserDTO.builder()
            .login(decoded.getIssuer())
            .firstName(decoded.getClaim("firstName").asString())
            .lastName(decoded.getClaim("lastName").asString())
            .build();

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    /**
     * 
     * @param token
     * @return Bean de autenticação
     */
    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);

        User user = userRepository.findByLogin(decoded.getIssuer())
            .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        return new UsernamePasswordAuthenticationToken(userMapper.toUserDTO(user), null, Collections.emptyList());
    }
}