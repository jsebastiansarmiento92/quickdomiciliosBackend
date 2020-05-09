package com.quick.service;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quick.model.Usuario;
import com.quick.repositories.IRepoUsuario;
import com.quick.security.UserPrincipal;


/**
 * clas ela cual implementa el servicio de detalle de usuario, en esta clase maneja y devuelve sesion de usuario
 * @author Juan Sebastian Sarmiento jsebastiansarmiento92@gmail.com
 *
 * @version 12/04/2020
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	//atributo que carga o instancia el repositorio de usuario cada vez que se necesite 
	@Autowired
    IRepoUsuario userRepo;
	
	/**
	 * metodo que devuelve el detalle de sesion de un usario buscado por el nombre de este(nickname)
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String nameUser) throws UsernameNotFoundException {
		Usuario usuario = userRepo.findByNombreUsuario(nameUser).get();
		System.out.println("usaurio solicitado en user details es " + usuario.getNombreUsuario());
		System.out.println("privilegios son: " + usuario.getRoles());
        return UserPrincipal.build(usuario);
	}

	
}
