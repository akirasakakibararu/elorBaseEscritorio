package com.elorBase.server.elorBaseServer.dataBase.entity;
// Generated 27 ene 2025, 17:16:15 by Hibernate Tools 6.5.1.Final

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Horarioreunion generated by hbm2java
 */
@Entity
public class Horarioreunion implements java.io.Serializable {

	@Id
	private Integer idHorarioReunion;
	private String dia;
	private int hora;
	private Integer aula;
	private Integer idReunion;

	public Horarioreunion() {
	}

	public Horarioreunion(String dia, int hora) {
		this.dia = dia;
		this.hora = hora;
	}

	public Horarioreunion(String dia, int hora, Integer aula, Integer idReunion) {
		this.dia = dia;
		this.hora = hora;
		this.aula = aula;
		this.idReunion = idReunion;
	}

	public Integer getIdHorarioReunion() {
		return this.idHorarioReunion;
	}

	public void setIdHorarioReunion(Integer idHorarioReunion) {
		this.idHorarioReunion = idHorarioReunion;
	}

	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public int getHora() {
		return this.hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public Integer getAula() {
		return this.aula;
	}

	public void setAula(Integer aula) {
		this.aula = aula;
	}

	public Integer getIdReunion() {
		return this.idReunion;
	}

	public void setIdReunion(Integer idReunion) {
		this.idReunion = idReunion;
	}

}
