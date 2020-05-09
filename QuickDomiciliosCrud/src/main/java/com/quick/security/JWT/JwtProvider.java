package com.quick.security.JWT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.quick.security.UserPrincipal;

import io.jsonwebtoken.*;


import org.springframework.security.core.Authentication;

import java.util.Date;
/**
 * esta clase es el corazón de JWT, donde se crea el token, se valida y se extrae el nombre del usuario
 * @author Juan Sebastian Sarmiento jsebastiansarmiento92@gmail.com
 * @version 12/04/2020
 */
@Component
public class JwtProvider {
	//atributo para imprimir en nuestro servidor cualquier anomalia o aviso
	private static  final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

	//atributo donde trae un valor que aporta para la creacion de token 
	@Value("${jwt.secret}")	
	private String secret;
	//atributo para asignar valor detiempo en entero de la sesion del usuario
	@Value("${jwt.expiration}")
	private int expiration;
	/**
	 * Metodo el cual genera un token y lo devuelve en string para guardarlo en el localStorage del navegador
	 * @param authentication
	 * @return
	 */
	public String generateToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		return Jwts.builder().setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 1000))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	/**
	 * metodo el cual devuelve el nombre de usuario de la sesion para guardarlo en el locar storage
	 * @param token
	 * @return
	 */
	public String getNombreUsuarioFromToken(String token){
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	/**
	 * metodo para hacer validacion del token del navegador vs el token del servivio debe ser igual 
	 * @param token
	 * @return
	 */
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("token mal formado " +e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("token no soportado " +e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("token expirado " +e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("token vacío " +e.getMessage());
		} catch (SignatureException e) {
			logger.error("error en la firma " +e.getMessage());
		}
		return false;
	}

}
