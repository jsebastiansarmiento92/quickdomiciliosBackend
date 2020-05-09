package com.quick.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="empresa")
public class Empresa {

	@Id
	@Column(name="id_empresa")
	private int idEmpresa;
	
	@Column(name="estado_empresa")
	private String estadoEmpresa;
	
	@Column(name="nit_empresa")
	private int nitEmpresa;
	
	@Column(name="email_contacto_empresa")
	private String email;
	
	@Column(name="razon_social_empresa")
	private String razonSocial;
	
	@Column(name="telefono_empresa")
	private int telefonoEmpresa;
	
	@Column(name="id_lugar")
	private int idLugar;

	
	
	
	public Empresa() {
		
	}

	

	public Empresa(int idEmpresa, String estadoEmpresa, int nitEmpresa, String email, String razonSocial,
			int telefonoEmpresa, int idLugar) {
		super();
		this.idEmpresa = idEmpresa;
		this.estadoEmpresa = estadoEmpresa;
		this.nitEmpresa = nitEmpresa;
		this.email = email;
		this.razonSocial = razonSocial;
		this.telefonoEmpresa = telefonoEmpresa;
		this.idLugar = idLugar;
	}



	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getEstadoEmpresa() {
		return estadoEmpresa;
	}

	public void setEstadoEmpresa(String estadoEmpresa) {
		this.estadoEmpresa = estadoEmpresa;
	}

	public int getNitEmpresa() {
		return nitEmpresa;
	}

	public void setNitEmpresa(int nitEmpresa) {
		this.nitEmpresa = nitEmpresa;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public int getTelefonoEmpresa() {
		return telefonoEmpresa;
	}

	public void setTelefonoEmpresa(int telefonoEmpresa) {
		this.telefonoEmpresa = telefonoEmpresa;
	}

	public int getIdLugar() {
		return idLugar;
	}

	public void setIdLugar(int idLugar) {
		this.idLugar = idLugar;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Empresa [idEmpresa=" + idEmpresa + ", estadoEmpresa=" + estadoEmpresa + ", nitEmpresa=" + nitEmpresa
				+ ", email=" + email + ", razonSocial=" + razonSocial + ", telefonoEmpresa=" + telefonoEmpresa
				+ ", idLugar=" + idLugar + "]";
	}
	
	
	
	
	
}

