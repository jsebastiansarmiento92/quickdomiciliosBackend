package com.quick.security.JWT;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.quick.service.UserDetailsServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Esta clase lo que hace es obtener el token y el usuario, este último a partir del nombre de usuario y comprueba si es la autenticación es correcta. 
 * En caso afirmativo la añade al contexto y, por último, se añade el filtro
 * @author Juan Sebastian Sarmiento jsebastiansarmiento92@gmail.com
 *
 * @version 12/04/2020
 */
public class JwtTokenFilter  extends OncePerRequestFilter {

	
	
	
	//atributo para imprimir en nuestro servidor cualquier anomalia o aviso
	private static  final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
	//atributo que provee de token a las sesiones y asigna tiempo de sesion a esta
    @Autowired
    JwtProvider jwtProvider;
    //atributo donde opera a travez de la base de datos donde extrae usuarios por nombre
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(req);
            if(token !=null && jwtProvider.validateToken(token)){
                String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(nombreUsuario);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (Exception e){
            logger.error("fail en método doFilter " + e.getMessage());
        }
        filterChain.doFilter(req, res);
    }
    /**
     * metodo que retorna el token generado
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request){
        String authReq = request.getHeader("Authorization");
        if(authReq != null && authReq.startsWith("Bearer "))
            return authReq.replace("Bearer ", "");
        return null;
    }


}
