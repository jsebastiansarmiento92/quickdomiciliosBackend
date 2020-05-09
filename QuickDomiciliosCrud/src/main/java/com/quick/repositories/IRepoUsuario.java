package com.quick.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quick.model.Usuario;

public interface IRepoUsuario extends JpaRepository<Usuario,Integer> {
	//Usuario findById_usuario(int id);
	Optional<Usuario> findByNombreUsuario(String nu);
	Optional<Usuario> findByEmail(String nu);
	boolean existsByNombreUsuario(String n);
	public boolean existsByEmail(String nombre);
	//List<Usuario> findByIdRol(int id);
	
	
	
}
