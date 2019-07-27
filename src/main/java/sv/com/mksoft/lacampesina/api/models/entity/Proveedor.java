package sv.com.mksoft.lacampesina.api.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "proveedores")
public class Proveedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre_empresa")
	private String nombreEmpresa;
	
	@Column(name = "nombre_contacto")
	private String nombreContacto;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "telefono_fijo")
	private String telefonoFijo;
	
	@Column(name = "telefono_fijo2")
	private String telefonoFijo2;
	
	@Column(name = "telefono_movil")
	private String telefonoMovil;
	
	@Column(name = "telefono_movil2")
	private String telefonoMovil2;
	
	@Column(name = "notas")
	private String notas;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@PrePersist
	public void prePersist() {
		createdAt = new Date();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getNombreContacto() {
		return nombreContacto;
	}
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefonoFijo() {
		return telefonoFijo;
	}
	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}
	public String getTelefonoFijo2() {
		return telefonoFijo2;
	}
	public void setTelefonoFijo2(String telefonoFijo2) {
		this.telefonoFijo2 = telefonoFijo2;
	}
	public String getTelefonoMovil() {
		return telefonoMovil;
	}
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}
	public String getTelefonoMovil2() {
		return telefonoMovil2;
	}
	public void setTelefonoMovil2(String telefonoMovil2) {
		this.telefonoMovil2 = telefonoMovil2;
	}
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	

}
