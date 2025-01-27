package com.elorBase.server.elorBaseServer.dataBase.entity;
// Generated 27 ene 2025, 17:16:15 by Hibernate Tools 6.5.1.Final

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Profesor generated by hbm2java
 */
@Entity
public class Profesor implements java.io.Serializable {

	@Id
	private int idProfesor;
	private String email;
	private int contrasenna;
	private String dni;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String telefono1;
	private String telefono2;

	public Profesor() {
	}

	public Profesor(int idProfesor, int contrasenna, String dni, String nombre, String apellidos, String telefono1) {
		this.idProfesor = idProfesor;
		this.contrasenna = contrasenna;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono1 = telefono1;
	}

	public Profesor(int idProfesor, String email, int contrasenna, String dni, String nombre, String apellidos,
			String direccion, String telefono1, String telefono2) {
		this.idProfesor = idProfesor;
		this.email = email;
		this.contrasenna = contrasenna;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
	}

	public int getIdProfesor() {
		return this.idProfesor;
	}

	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getContrasenna() {
		return this.contrasenna;
	}

	public void setContrasenna(int contrasenna) {
		this.contrasenna = contrasenna;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono1() {
		return this.telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

}
