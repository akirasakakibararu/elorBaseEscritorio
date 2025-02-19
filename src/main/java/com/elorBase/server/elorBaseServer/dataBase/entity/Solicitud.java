package com.elorBase.server.elorBaseServer.dataBase.entity;
// Generated 27 ene 2025, 17:16:15 by Hibernate Tools 6.5.1.Final

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Solicitud generated by hbm2java
 */
@Entity
public class Solicitud implements java.io.Serializable {

	@Id
	private int idReunion;
	private int idProfesor;
	private String confirmada;

	public Solicitud() {
	}

	public Solicitud(int idReunion, int idProfesor) {
		this.idReunion = idReunion;
		this.idProfesor = idProfesor;
	}

	public Solicitud(int idReunion, int idProfesor, String confirmada) {
		this.idReunion = idReunion;
		this.idProfesor = idProfesor;
		this.confirmada = confirmada;
	}

	public int getIdReunion() {
		return this.idReunion;
	}

	public void setIdReunion(int idReunion) {
		this.idReunion = idReunion;
	}

	public int getIdProfesor() {
		return this.idProfesor;
	}

	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}

	public String getConfirmada() {
		return this.confirmada;
	}

	public void setConfirmada(String confirmada) {
		this.confirmada = confirmada;
	}

}
