package com.quick.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quick.model.Caja;

@Repository
public interface IRepoCaja extends JpaRepository<Caja, Integer>{
	
	
}
