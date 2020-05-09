package com.quick.DTO;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
/**clase que define los estandares de autenticacion
 * 
 * @author Juan Sebastian Sarmiento jsebastiansarmiento92@gmail.com
 * @version 12/04/2020
 */
public class JwtDTO {
	//atributo donde guarda el token de autenticacion (la cadena larga de caracteres)
	private String token;
	//atributo donde define el esquema de autenticacion "Bearer" es la que ultimamente se usa mas
	private String bearer = "Bearer";
	//atributo donde guarda el nombre de usuario
	private String nombreUsuario;
	//atributo donde guarda arreglo o lista de roles o permisos de tipo GrantedAuthority
	private Collection<? extends GrantedAuthority> authorities;
	private String id;
	
	/**metodo constructor instanciado desde un servicio para almacenar los datos de sesion
	 * @param token, ingresa token a la instancia  
	 * @param nombreUsuario, ingresa el nombre de usuario
	 * @param authorities, arreglo de permisos o privilegios de la sesion
	 */
	public JwtDTO(String token, String nombreUsuario, Collection<? extends GrantedAuthority> authorities, String id) {
		//guarda en memoria de la instancia las variables ingresadas
		this.token = token;
		this.nombreUsuario = nombreUsuario;
		this.authorities = authorities;
		this.id=id;
	}
	
	//Metodos segt y geter donde son llamados los atributos encapsulados en esta clase
	/**
	 * 
	 * @return
	 */
	public String getToken() {
		return token;
	}
	/**
	 * 
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * 
	 * @return
	 */
	public String getBearer() {
		return bearer;
	}
	/**
	 * 
	 * @param bearer
	 */
	public void setBearer(String bearer) {
		this.bearer = bearer;
	}
	/**
	 * 
	 * @return
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	/**
	 * 
	 * @param nombreUsuario
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	/**
	 * 
	 * @return
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	/**
	 * 
	 * @param authorities
	 */
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


}
