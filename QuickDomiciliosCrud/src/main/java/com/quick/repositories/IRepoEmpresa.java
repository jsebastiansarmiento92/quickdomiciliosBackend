package com.quick.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quick.model.Empresa;

@Repository
public interface IRepoEmpresa extends JpaRepository<Empresa, Integer>{

	
}
