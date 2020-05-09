package com.quick.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

@Entity
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name="rol_nombre")
	private RolNombre rolNombre;
	
	public Rol() {
    }
	public Rol(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
	


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public RolNombre getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	
	
	
	
	
}
