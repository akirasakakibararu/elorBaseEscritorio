package com.elorBase.server.elorBaseServer.socketIO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.elorBase.server.elorBaseServer.HibernateUtil;
import com.elorBase.server.elorBaseServer.dataBase.consultas.ConsultasProfesor;
import com.elorBase.server.elorBaseServer.dataBase.entity.Horario;
import com.elorBase.server.elorBaseServer.socketIO.config.Events;
import com.elorBase.server.elorBaseServer.socketIO.model.MessageInput;
import com.elorBase.server.elorBaseServer.socketIO.model.MessageOutput;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SocketIOModule {
	private SocketIOServer server = null;

	public SocketIOModule(SocketIOServer server) {
		super();
		this.server = server;

		// Default events (for control the connection of clients)
		server.addConnectListener(onConnect());
		server.addDisconnectListener(onDisconnect());

		// Custom events
		server.addEventListener(Events.GET_HORARIO_SEMANAL_PROFESOR.value, MessageInput.class, this.obtenerHorarioProfesor());
	}

	// Default events

	private ConnectListener onConnect() {
		return (client -> {
			client.joinRoom("default-room");
			System.out.println("New connection, Client: " + client.getRemoteAddress());
		});
	}

	private DisconnectListener onDisconnect() {
		return (client -> {
			client.leaveRoom("default-room");
			System.out.println(client.getRemoteAddress() + " disconected from server");
		});
	}

	// Custom events
	
	private DataListener<MessageInput> obtenerHorarioProfesor() {
		return ((client, data, ackSender) -> {
			try {
                String message = data.getMessage();
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
                
                //int idProfesor = jsonObject.get("idProfesor").getAsInt();
                int idProfesor = 1;
                
                List<Object[]> horario = ConsultasProfesor.mostrar_horario_profesor(idProfesor);

                String answerMessage = gson.toJson(horario);

                MessageOutput messageOutput = new MessageOutput(answerMessage);
                client.sendEvent(Events.GET_HORARIO_SEMANAL_PROFESOR_ANSWER.value, messageOutput);
                
            } catch (Exception e) {
                e.printStackTrace();
                MessageOutput errorMessage = new MessageOutput("Error fetching professor schedule: " + e.getMessage());
                client.sendEvent(Events.GET_HORARIO_SEMANAL_PROFESOR_ANSWER.value, errorMessage);
            }
		});
	}
	
	/*private DataListener<MessageInput> obtenerHorarioProfesor() {
		return ((client, data, ackSender) -> {
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            // Start transaction
	            Transaction transaction = session.beginTransaction();

	            // Query to fetch the schedule for the professor with id = 1
	            String hql = "SELECT new map(" +
	                         "h.hora as hora, " +
	                         "h.dia as dia, " +
	                         "h.aula as aula, " +
	                         "a.nombre as asignatura) " +
	                         "FROM Horario h " +
	                         "INNER JOIN h.asignatura a " +
	                         "WHERE a.profesor.id = :idProfesor " +
	                         "ORDER BY h.dia, h.hora";

	            // Execute the query and set the professor's ID
	            Query query = session.createQuery(hql);
	            query.setParameter("idProfesor", 1); // Replace 1 with a dynamic value if needed

	            // Fetch results
	            List<?> horarios = query.list();
	            transaction.commit();

	            // Convert result to JSON
	            String answerMessage = new Gson().toJson(horarios);

	            // Send the result back to the client
	            MessageOutput messageOutput = new MessageOutput(answerMessage);
	            client.sendEvent(Events.GET_HORARIO_SEMANAL_PROFESOR_ANSWER.value, messageOutput);
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Handle error and send an error response to the client
	            MessageOutput errorMessage = new MessageOutput("Error fetching professor schedule: " + e.getMessage());
	            client.sendEvent(Events.GET_HORARIO_SEMANAL_PROFESOR_ANSWER.value, errorMessage);
	        }
	    });
	}*/


	/*private DataListener<MessageInput> obtenerAsignaturas() {
		return ((client, data, ackSender) -> {
			System.out.println("Client from " + client.getRemoteAddress() + " wants to getAsignaturas");

			List<Asignatura> asignaturas = new ArrayList<>();

			Asignatura matematicas = new Asignatura();
			matematicas.setNombre("Matemáticas");
			asignaturas.add(matematicas);

			Asignatura historia = new Asignatura();
			historia.setNombre("Historia");
			asignaturas.add(historia);

			Asignatura fisica = new Asignatura();
			fisica.setNombre("Física");
			asignaturas.add(fisica);

			Asignatura lengua = new Asignatura();
			lengua.setNombre("Lengua Española");
			asignaturas.add(lengua);

			Asignatura quimica = new Asignatura();
			quimica.setNombre("Química");
			asignaturas.add(quimica);

			String answerMessage = new Gson().toJson(asignaturas);
			MessageOutput messageOutput = new MessageOutput(answerMessage);
			client.sendEvent(Events.OBTENER_ASIGNATURAS.value, messageOutput);
		});
	}*/
	
	/*private DataListener<MessageInput> obtenerCicloFormativo() {
		return ((client, data, ackSender) -> {
			System.out.println("Client from " + client.getRemoteAddress() + " wants to get Ciclos Formativos");

			List<Cicloformativo> cicloFormativo = new ArrayList<Cicloformativo>();

			

			String answerMessage = new Gson().toJson(cicloFormativo);
			MessageOutput messageOutput = new MessageOutput(answerMessage);
			client.sendEvent(Events.OBTENER_ASIGNATURAS.value, messageOutput);
		});
	}*/

	// Server control

	public void start() {
		server.start();
		System.out.println("Server started...");
	}

	public void stop() {
		server.stop();
		System.out.println("Server stopped");
	}
}
