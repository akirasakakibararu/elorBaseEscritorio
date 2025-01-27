package com.elorBase.server.elorBaseServer;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.elorBase.server.elorBaseServer.dataBase.entity.Asignatura;
import com.elorBase.server.elorBaseServer.socketIO.SocketIOModule;

public class App {
	private static final String HOST_NAME = "localhost";
	private static final int PORT = 3000;
	
	public static void main(String[] args) {
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session session = sesion.openSession();

        String hql = "SELECT a.nombre FROM Asignatura a";

        // Crear la consulta HQL con tipo de retorno String (nombre de asignatura)
        Query query = session.createQuery(hql);

        // Obtener los resultados de la consulta
        List<String> nombres = query.list();

        // Imprimir los resultados
        for (String nombre : nombres) {
            System.out.println(nombre);
        }

        session.close();

        sesion.close();
        /*
		// Server configuration 
		Configuration config = new Configuration ();
		config.setHostname(HOST_NAME);
		config.setPort(PORT);
		
		// We start the server
		SocketIOServer server = new SocketIOServer(config);
		SocketIOModule module = new SocketIOModule(server);
		module.start();*/
	}
}
