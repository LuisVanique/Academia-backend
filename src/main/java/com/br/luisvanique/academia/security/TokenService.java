package com.br.luisvanique.academia.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.br.luisvanique.academia.domain.instrutor.Instrutor;

@Service
public class TokenService {

	@Value("${api.security.token.secret}") 
	private String secret;

	@Value("${api.security.token.issuer}")
	private String issuer;

	public String generateToken(Instrutor user) {
		return JWT.create().withIssuer(issuer).withSubject(user.getEmail()) // Identifica o usuário
				.withClaim("id", user.getId()) // Podemos adicionar qualquer informação relevante usando o withClaim
				.withClaim("nome", user.getNome()).withClaim("perfil", user.getPerfil().toString())
				.withExpiresAt(generateExpirationDate()) // É importante definir um tempo de expiração do token
				.sign(Algorithm.HMAC256(secret));
	}

	public String extractSubject(String token) {
		var algorithm = Algorithm.HMAC256(secret);
		var verifier = JWT.require(algorithm).withIssuer(issuer).build();

		var decodedJWT = verifier.verify(token); // Valida o token

		return decodedJWT.getSubject(); // Pega o subject
	}

	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
