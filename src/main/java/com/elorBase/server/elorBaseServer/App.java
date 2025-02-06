package com.elorBase.server.elorBaseServer;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.elorBase.server.elorBaseServer.dataBase.consultas.ConsultasAlumno;
import com.elorBase.server.elorBaseServer.dataBase.consultas.ConsultasProfesor;
import com.elorBase.server.elorBaseServer.dataBase.entity.Asignatura;
import com.elorBase.server.elorBaseServer.socketIO.SocketIOModule;
import com.elorBase.server.elorBaseServer.socketIO.SocketIOModule;

public class App {
	private static final String HOST_NAME = "localhost";
	private static final int PORT = 3000;

	public static void main(String[] args) {
		// Server configuration
		Configuration config = new Configuration();
		config.setHostname(HOST_NAME);
		config.setPort(PORT);

		// We start the server
		SocketIOServer server = new SocketIOServer(config);
		SocketIOModule module = new SocketIOModule(server);
		module.start();

		// probarMetodo_mostrar_horario_alumno(2);
		// probarMetodo_mostrar_horario_profesor(1);
		// probar_metodo_mostrar_datos_alumno(10);
		// probar_metodo_cambiar_estado_reunion("Aceptada", 5);
		//probar_metodo_crear_reunion();
		probarMetodo_mostrar_horario_reunion("Maria Lopez");
	}

	private static void probarMetodo_mostrar_horario_profesor(int idProfesorPrueba) {
		int idProfesor = idProfesorPrueba;

		List<Object[]> horario = ConsultasProfesor.mostrar_horario_profesor(idProfesor);

		if (horario != null && !horario.isEmpty()) {
			for (Object[] row : horario) {
				System.out.println("Hora: " + row[0]);
				System.out.println("Día: " + row[1]);
				System.out.println("Aula: " + row[2]);
				System.out.println("Asignatura: " + row[3]);
				System.out.println("Nombre: " + row[4]);
			}
		} else {
			System.out.println("No se encontraron resultados para el horario del alumno.");
		}
	}
	
	private static void probarMetodo_mostrar_horario_reunion(String profesor) {
	    String nombreProfesor = profesor;

	    List<Object[]> horarioProfesor = ConsultasProfesor.mostrar_reuniones_por_receptor(nombreProfesor);

	    if (horarioProfesor != null && !horarioProfesor.isEmpty()) {
	        for (Object[] row : horarioProfesor) {
	            System.out.println("Reuniones:");
	            System.out.println("Titulo: " + row[0]);
	            System.out.println("Asunto: " + row[1]);
	            System.out.println("Dia: " + row[2]);
	            System.out.println("Hora: " + row[3]);
	            System.out.println("Aula: " + row[4]);
	            System.out.println("Solicitante: " + row[5]);
	            System.out.println("Receptor: " + row[6]);
	        }
	    } else {
	        System.out.println("No existen reuniones del profesor: " + nombreProfesor);
	    }
	}

	private static void probarMetodo_mostrar_horario_alumno(int idAlumnoPrueba) {
		int idAlumno = idAlumnoPrueba;

		List<Object[]> horario2 = ConsultasAlumno.mostrar_horario_alumno(idAlumno);

		if (horario2 != null && !horario2.isEmpty()) {
			for (Object[] row : horario2) {

				System.out.println("Horario del alumno");
				System.out.println("Hora: " + row[0]);
				System.out.println("Día: " + row[1]);
				System.out.println("Aula: " + row[2]);
				System.out.println("Asignatura: " + row[3]);
				System.out.println("ID Asignatura: " + row[4]);
				System.out.println("Profesor: " + row[5] + " " + row[6]);
			}
		} else {
			System.out.println("No se encontraron resultados para el horario del alumno.");
		}
	}

	private static void probar_metodo_mostrar_datos_alumno(int idAlumnoPrueba) {
		int idAlumno = idAlumnoPrueba;

		List<Object[]> datosAlumno = ConsultasAlumno.mostrar_datos_alumno(idAlumno);

		if (datosAlumno != null && !datosAlumno.isEmpty()) {
			for (Object[] row : datosAlumno) {

				System.out.println("datos del alumno");
				System.out.println("ID: " + row[0]);
				System.out.println("Email" + row[1]);
				System.out.println("DNI: " + row[2]);
				System.out.println("Nombre: " + row[3]);
				System.out.println("Apellidos: " + row[4]);
				System.out.println("Telefono 1: " + row[5]);
				System.out.println("Telefono 2: " + row[6]);
				System.out.println("Curso: " + row[7]);
				System.out.println("Fecha de matriculación: " + row[8]);
				System.out.println("Ciclo: " + row[9]);
			}
		} else {
			System.out.println("No se encontraron resultados para el horario del alumno.");
		}
	}
	
	private static void probar_metodo_cambiar_estado_reunion (String estadoNuevo, int idReunion) {
		 ConsultasProfesor.cambiar_estado_reunion(estadoNuevo, idReunion);
	}
	
	private static void probar_metodo_crear_reunion() {
	    Scanner scanner = new Scanner(System.in);

	    System.out.println("Introduzca los datos de la reunión:");

	    System.out.print("Solicitante: ");
	    String solicitante = scanner.nextLine();

	    System.out.print("Receptor: ");
	    String receptor = scanner.nextLine();

	    System.out.print("Título: ");
	    String titulo = scanner.nextLine();

	    System.out.print("Asunto: ");
	    String asunto = scanner.nextLine();

	    System.out.print("ID del profesor: ");
	    int idProfesor = Integer.parseInt(scanner.nextLine());

	    System.out.print("Día de la reunión (Lunes-Viernes): ");
	    String dia = scanner.nextLine();

	    System.out.print("Hora de la reunión (1-6): ");
	    int hora = Integer.parseInt(scanner.nextLine());

	    System.out.print("Aula de la reunión: ");
	    int aula = Integer.parseInt(scanner.nextLine());

	    ConsultasProfesor.crear_reunion(solicitante, receptor, titulo, asunto, idProfesor, dia, hora, aula);

	    System.out.println("Reunión creada correctamente.");

	    scanner.close();
	}
}
