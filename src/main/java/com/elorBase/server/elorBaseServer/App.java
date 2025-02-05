	package com.elorBase.server.elorBaseServer;
	
	import java.util.List;
	import java.util.Map;
	
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
					Configuration config = new Configuration ();
					config.setHostname(HOST_NAME);
					config.setPort(PORT);
					
					// We start the server
					SocketIOServer server = new SocketIOServer(config);
					SocketIOModule module = new SocketIOModule(server);
					module.start();
					
					ConsultasProfesor.crear_reunion(
						    "Juan Pérez",     // Solicitante
						    "María López",    // Receptor
						    "Prueban",        // Título
						    "Prueba Insert",  // Asunto
						    1,                // idProfesor (suponiendo que es el id de un profesor en la base de datos que ya existe)
						    "Lunes",          // Día
						    2,                // Hora
						    101               // Aula
						);
					//probarMetodo_mostrar_horario_alumno(2);
					//probarMetodo_mostrar_horario_profesor(1);
		}
		
		private static void probarMetodo_mostrar_horario_profesor(int idProfesorPrueba) {
			int idProfesor = idProfesorPrueba;
			
			List<Object[]> horario = ConsultasProfesor.mostrar_horario_profesor(idProfesor);
			
			for (Object[] row : horario) {
		        System.out.println("Hora: " + row[0]);
		        System.out.println("Día: " + row[1]);
		        System.out.println("Aula: " + row[2]);
		        System.out.println("Asignatura: " + row[3]);
		        System.out.println("Nombre: " + row[4]);
		    }
		}
		
		private static void probarMetodo_mostrar_horario_alumno(int idAlumnoPrueba) {
			int idAlumno = idAlumnoPrueba;
			
			List<Object[]> horario2 = ConsultasAlumno.mostrar_horario_alumno(idAlumno);
			
			if (horario2 != null && !horario2.isEmpty()) {
		        for (Object[] row : horario2) {
		            // Imprimir información del horario
		        	System.out.println("Horario de los alumno");
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
	}
