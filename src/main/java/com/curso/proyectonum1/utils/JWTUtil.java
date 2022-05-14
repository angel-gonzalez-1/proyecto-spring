package com.curso.proyectonum1.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
//clase que se crea para incio de secion, creacion de tokens y seguimiento de sesion 
// con component hace compatible a todos 
@Component
public class JWTUtil {
	
	@Value("${security.jwt.secret}")
	private String key;
	
	@Value("${security.jwt.issuer}")
	private String issuer;
	
	@Value("${security.jwt.ttlMillis}")
	private long ttlMillis;
	
	private final Logger log = LoggerFactory.getLogger(JWTUtil.class);
	
	
	public String create(String id, String subject) {
		
		//el JWT algoritmo que asigna el token
		SignatureAlgorithm signatureAlgorithm= SignatureAlgorithm.HS256;
		
		long nowMillis = System.currentTimeMillis();
		
		Date now =new Date(nowMillis);
		
		//sign JWT with our Api Key secret
		
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
		
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		
		//set the JWT Claims
		JwtBuilder builder =Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);
		
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		return builder.compact();
	}
	
	//validar y leer el JWT
	public String getValue(String jwt) {
		
		Claims claims = Jwts.parser().
				setSigningKey(DatatypeConverter.parseBase64Binary(key)).
				parseClaimsJws(jwt).getBody();
		return claims.getSubject();
	}
	//validar informacion
	public String getKey(String jwt) {
		
		Claims claims =Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(key))
				.parseClaimsJws(jwt).getBody();
		return claims.getId();
	}
	

}
