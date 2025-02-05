package com.elorBase.server.elorBaseServer.socketIO.config;

public enum Events {

	GET_HORARIO_SEMANAL_PROFESOR ("getHorarioSemanalProfesor"),
	GET_HORARIO_SEMANAL_PROFESOR_ANSWER ("getHorarioSemanalProfesorAnswer"),
	GET_REUNION ("getReunion"),
	GET_HORARIO_SEMANAL_ALUMNO ("getHorarioSemanalAlumno"),
	GET_ALL_OFERTA_CURSOS ("getAllOfertaCursos"),
	OBTENER_CURSOS ("obtenerCursos");
	
	public final String value;

	private Events(String value) {
		this.value = value;
	}
}
