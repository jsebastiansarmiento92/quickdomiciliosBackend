package com.quick.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quick.model.Rol;
import com.quick.model.RolNombre;

public interface IRepoRol extends JpaRepository<Rol, Integer> {

	Optional<Rol> findByRolNombre(RolNombre rolNombre);
	
}
