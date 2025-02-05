package com.elorBase.server.elorBaseServer.dataBase.entity;
// Generated 27 ene 2025, 17:16:15 by Hibernate Tools 6.5.1.Final

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Reunion generated by hbm2java
 */
@Entity
public class Reunion implements java.io.Serializable {

	@Id
	private Integer idReunion;
	private String solicitante;
	private String receptor;
	private String estado;
	private String titulo;
	private String asunto;

	public Reunion() {
	}

	public Reunion(String solicitante, String receptor, String asunto) {
		this.solicitante = solicitante;
		this.receptor = receptor;
		this.asunto = asunto;
	}

	public Reunion(String solicitante, String receptor, String estado, String titulo, String asunto) {
		this.solicitante = solicitante;
		this.receptor = receptor;
		this.estado = estado;
		this.titulo = titulo;
		this.asunto = asunto;
	}

	public Integer getIdReunion() {
		return this.idReunion;
	}

	public void setIdReunion(Integer idReunion) {
		this.idReunion = idReunion;
	}

	public String getSolicitante() {
		return this.solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getReceptor() {
		return this.receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAsunto() {
		return this.asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

}
