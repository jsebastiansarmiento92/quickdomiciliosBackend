package com.quick.model;


import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.sun.istack.NotNull;

@Entity
public class Usuario {
	@Column(name = "id_usuario", nullable = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(name = "cc_usuario")
	private int cc_usuario;
	private String nombreUsuario;
	private String email;
	@Column(name = "confirmacion_cuenta", nullable = true)
	private Date confirmacion_cuenta;
	private String password;
	@Column(name = "fecha_creacion", 
	           columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Calendar fecha_creacion;
	//private int idRol;
	
	@ManyToOne //importante en versiones anteriores es recomendable colocar (fetch = FetchType.LAZY)
	@JoinColumn(name="id_rol")
	private Rol rol;
	
	
    //@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	@NotNull
    @ManyToMany
    private Set<Rol> roles = new HashSet<>();
	
	@Column(name = "estado_domiciliario", nullable = true)
	private String estado_domiciliario;
	@Column(name = "estado_recepcionista", nullable = true)
	private String estado_recepcionista;
	
	
	@OneToMany (mappedBy = "usuario",cascade = CascadeType.ALL)
	private List<Caja> cajas=new ArrayList<Caja>();
	
	
	
	
	
	
	public Usuario() {
    }
	public Usuario(@NotNull String email,@NotNull Rol idRol, @NotNull String password) {
        this.nombreUsuario = email;
        this.email = email;
        this.password = password;
        this.rol=idRol;
    }
	public int getId_usuario() {
		return id;
	}
	public void setId_usuario(int id_usuario) {
		this.id = id_usuario;
	}
	public int getCc_usuario() {
		return cc_usuario;
	}
	public void setCc_usuario(int cc_usuario) {
		this.cc_usuario = cc_usuario;
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
	public Date getConfirmacion_cuenta() {
		return confirmacion_cuenta;
	}
	public void setConfirmacion_cuenta(Date confirmacion_cuenta) {
		this.confirmacion_cuenta = confirmacion_cuenta;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Calendar getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Calendar fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
//	public List<Caja> getCajas() {
//		return cajas;
	//}
	public void setCajas(List<Caja> cajas) {
		this.cajas = cajas;
	}
	public Set<Rol> getRoles() {
		return roles;
	}
	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	public String getEstado_domiciliario() {
		return estado_domiciliario;
	}
	public void setEstado_domiciliario(String estado_domiciliario) {
		this.estado_domiciliario = estado_domiciliario;
	}
	public String getEstado_recepcionista() {
		return estado_recepcionista;
	}
	public void setEstado_recepcionista(String estado_recepcionista) {
		this.estado_recepcionista = estado_recepcionista;
	}
	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id + ", cc_usuario=" + cc_usuario + ", nombreUsuario=" + nombreUsuario
				+ ", email=" + email + ", confirmacion_cuenta=" + confirmacion_cuenta + ", password=" + password
				+ ", fecha_creacion=" + fecha_creacion + ", idRol=" + rol.getRolNombre() + ", roles=" + roles
				+ ", estado_domiciliario=" + estado_domiciliario + ", estado_recepcionista=" + estado_recepcionista
				+ "]";
	}
	


}
