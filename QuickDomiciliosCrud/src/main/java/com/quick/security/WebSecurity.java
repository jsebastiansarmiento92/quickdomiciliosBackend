package com.quick.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.quick.security.JWT.JwtEntryPoint;
import com.quick.security.JWT.JwtTokenFilter;
import com.quick.service.UserDetailsServiceImpl;



/**
 * clase donde se hace la parametrizacion de la seguridad de nuestro api
 * @author Juan Sebastian Sarmiento jsebastiansarmiento92@gmail.com
 *
 * @version 12/04/2020
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //sirve para que en los métodos del controlador se indique que privilegios son necesarios para acceder a sus recursos
public class WebSecurity extends WebSecurityConfigurerAdapter {
	//atributo el cual hace una instancia de userDeailsService, para, guardar,obtener, autentificar entre otras las sesiones en nuestro servidor
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	//instancia para vigilar errores y mensajes en los metodos 
	@Autowired
	JwtEntryPoint jwtEntryPoint;


	/**
	 * devuelve filtro donde cifra el token donde quita el "Bearer " del token
	 * @return
	 */
	@Bean
	public JwtTokenFilter jwtTokenFilter(){
		return new JwtTokenFilter();
	}
	//el metodo passwordEncoder que se usa para cifrar contraseñas
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	/**
	 * configuramos todas las opciones de seguridad del servidor. Digamos que es el método que “manda” sobre las demás clases
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());//En él se indica a qué recursos se puede acceder y con qué privilegios
	}

	/**
	 * utilizamos el objeto userDetailsServiceImpl y ciframos la contraseña
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.authorizeRequests()
		.antMatchers("/auth/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
