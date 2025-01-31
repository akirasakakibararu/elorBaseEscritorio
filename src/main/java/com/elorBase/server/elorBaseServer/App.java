package com.elorBase.server.elorBaseServer;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
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
		 /*int idProfesor = 1;
		    List<Object[]> horario = ConsultasProfesor.mostrar_horario_profesor(idProfesor);

		    for (Object[] row : horario) {
		        System.out.println("Hora: " + row[0]);
		        System.out.println("Día: " + row[1]);
		        System.out.println("Aula: " + row[2]);
		        System.out.println("Asignatura: " + row[3]);
		    }*/

		/*
		 * // Server configuration Configuration config = new Configuration ();
		 * config.setHostname(HOST_NAME); config.setPort(PORT);
		 * 
		 * // We start the server SocketIOServer server = new SocketIOServer(config);
		 * SocketIOModule module = new SocketIOModule(server); module.start();
		 */
	}
}
