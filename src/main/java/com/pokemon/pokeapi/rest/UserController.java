package com.pokemon.pokeapi.rest;

import java.security.Key;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.pokeapi.model.Usuario;
import com.pokemon.pokeapi.repository.IUsuarioRepository;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUsuarioRepository usuarioRepo;

	@PostMapping
	public Usuario login(@RequestParam("user") String username, @RequestParam("password") String password) {
		Usuario user = usuarioRepo.findByNombre(username);

		if(user != null) {
			String token = createJWT("personalJWT", "pokeapi", username, 600000L);
			user.setToken(token);
		}

		return user;
	}

	private String createJWT(String id, String issuer, String subject, long ttlMillis) {
		String secretKey = "mySecretKey";
		List grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ADMIN");
		
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder().setId(id)
				.setIssuedAt(now)
				.setSubject(subject)
				.setIssuer(issuer)
				.claim("authorities", grantedAuthorities)
				.signWith(signatureAlgorithm, signingKey);

		if (ttlMillis > 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}  

		return "Bearer " + (builder.compact());
	}
}
