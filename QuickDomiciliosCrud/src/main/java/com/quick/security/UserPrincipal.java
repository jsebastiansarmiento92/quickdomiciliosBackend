package com.quick.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.quick.model.Usuario;

/**
 * clase donde implementa Userdetails el cual define metodos los cuales la clase JWTProvider usa para tener informacion del usuario y sesion de este
 * @author Juan Sebastian Sarmiento jsebastiansarmiento92@gmail.com
 *
 * @version 12/04/2020
 */
public class UserPrincipal implements UserDetails{

	//atributo identificador del usuario (unico)
	private int id;
	//atributo nombre completo del usuario
	//atributo nickname o nombre de cuenta del usuario
	private String nombreUsuario;
	//atributo donde guarda email del usuario
	private String email;
	//atributo donde guarda la contraseña ya encripatada del usuario
	private String password;
	//atributo don guarda privilegios o roles
	private Collection<? extends GrantedAuthority> authorities;



	/**
	 * cosntructor donde guarda la informacion del usuario 
	 * @param id
	 * @param nombre
	 * @param nombreUsuario
	 * @param email
	 * @param password
	 * @param authorities
	 */
	public UserPrincipal(int id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.nombreUsuario = email;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	/**
	 * metodo el cual devuelve el usuario de la sesion
	 * @param usuario
	 * @return
	 */
	public static UserPrincipal build(Usuario usuario){
		List<GrantedAuthority> authorities =
				usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
		return new UserPrincipal(usuario.getId_usuario(), usuario.getEmail(), usuario.getPassword(), authorities);
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nombreUsuario;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}
