package com.quick.security.JWT;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Esta clase se utiliza para comprobar las credenciales en el login
 * @author Juan Sebastian Sarmiento jsebastiansarmiento92@gmail.com
 *
 * @version 12/04/2020
 */
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{
	//atributo para imprimir en nuestro servidor cualquier anomalia o aviso
    private static  final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
	
	
	/**
	 * Utilizamos un objeto de la clase Logger para imprimir por consola el error y comprobar cual es el método que produce error
	 */
	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e)
			throws IOException, ServletException {
		System.out.println("ingeso a metodo de commence en jwtEntrypoint");
		logger.error("fail en el método commence");
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "credenciales erróneas");
	}

	
}
