package com.quick.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Caja {
	

	@Id
	private int idCaja;

	private int idAsignaDinero;
	
	//private int idUsuario;
	
	@ManyToOne //importante en versiones anteriores es recomendable colocar (fetch = FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	private int baseAsignadaCaja;
	
	private int dineroInicialCaja;
	
	private int ingresosCaja;
	
	private int egresosCaja;
	@Column(name = "fecha_caja", 
	           columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Calendar fechaCaja;
	
	private String estadoCaja;

	public Caja() {
	}
	
	
	public Caja(int idCaja, int idAsignaDinero, int idUsuario, int baseAsignadaCaja, int dineroInicialCaja,
			int ingresosCaja, int egresosCaja, String estadoCaja,Usuario usuario) {
	
		this.idCaja = idCaja;
		this.idAsignaDinero = idAsignaDinero;
		this.usuario = usuario;
		this.baseAsignadaCaja = baseAsignadaCaja;
		this.dineroInicialCaja = dineroInicialCaja;
		this.ingresosCaja = ingresosCaja;
		this.egresosCaja = egresosCaja;
		this.estadoCaja = estadoCaja;
	}

	public int getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(int idCaja) {
		this.idCaja = idCaja;
	}

	public int getIdAsignaDinero() {
		return idAsignaDinero;
	}

	public void setIdAsignaDinero(int idAsignaDinero) {
		this.idAsignaDinero = idAsignaDinero;
	}

	

	public int getBaseAsignadaCaja() {
		return baseAsignadaCaja;
	}

	public void setBaseAsignadaCaja(int baseAsignadaCaja) {
		this.baseAsignadaCaja = baseAsignadaCaja;
	}

	public int getDineroInicialCaja() {
		return dineroInicialCaja;
	}

	public void setDineroInicialCaja(int dineroInicialCaja) {
		this.dineroInicialCaja = dineroInicialCaja;
	}

	public int getIngresosCaja() {
		return ingresosCaja;
	}

	public void setIngresosCaja(int ingresosCaja) {
		this.ingresosCaja = ingresosCaja;
	}

	public int getEgresosCaja() {
		return egresosCaja;
	}

	public void setEgresosCaja(int egresosCaja) {
		this.egresosCaja = egresosCaja;
	}

	public Calendar getFechaCaja() {
		return fechaCaja;
	}

	public void setFechaCaja(Calendar fechaCaja) {
		this.fechaCaja = fechaCaja;
	}

	public String getEstadoCaja() {
		return estadoCaja;
	}

	public void setEstadoCaja(String estadoCaja) {
		this.estadoCaja = estadoCaja;
	}


	


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	public Usuario getUsuario() {
		return usuario;
	}


	@Override
	public String toString() {
		return "Caja [idCaja=" + idCaja + ", idAsignaDinero=" + idAsignaDinero + ", usuario=" + usuario.getNombreUsuario()
				+ ", baseAsignadaCaja=" + baseAsignadaCaja + ", dineroInicialCaja=" + dineroInicialCaja
				+ ", ingresosCaja=" + ingresosCaja + ", egresosCaja=" + egresosCaja + ", fechaCaja=" + fechaCaja
				+ ", estadoCaja=" + estadoCaja + "]";
	}
	
	
	
}
