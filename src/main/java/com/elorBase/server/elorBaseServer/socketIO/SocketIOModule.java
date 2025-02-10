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
import com.elorBase.server.elorBaseServer.dataBase.consultas.ConsultasAlumno;
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
		server.addEventListener(Events.GET_HORARIO_SEMANAL_ALUMNO.value, MessageInput.class, this.obtenerHorarioAlumno());
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
	
	private DataListener<MessageInput> obtenerCursos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private DataListener<MessageInput> obtenerHorarioProfesor() {
		return ((client, data, ackSender) -> {
			try {
                String message = data.getMessage();
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
                
                int idProfesor = jsonObject.get("idProfesor").getAsInt();
                
                List<Object[]> horario = ConsultasProfesor.mostrar_horario_profesor(idProfesor);
                System.out.println("Horario: " + horario);

                String answerMessage = gson.toJson(horario);
                System.out.println("Sending schedule: " + answerMessage);

                MessageOutput messageOutput = new MessageOutput(answerMessage);
                client.sendEvent(Events.GET_HORARIO_SEMANAL_PROFESOR_ANSWER.value, messageOutput);
                
            } catch (Exception e) {
                e.printStackTrace();
                MessageOutput errorMessage = new MessageOutput("Error fetching professor schedule: " + e.getMessage());
                client.sendEvent(Events.GET_HORARIO_SEMANAL_PROFESOR_ANSWER.value, errorMessage);
            }
		});
	}
	
	private DataListener<MessageInput> obtenerHorarioAlumno() {
		return ((client, data, ackSender) -> {
			try {
                String message = data.getMessage();
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
                
                int idAlumno = jsonObject.get("idAlumno").getAsInt();
                
                List<Object[]> horario = ConsultasAlumno.mostrar_horario_alumno(idAlumno);
                System.out.println("Horario: " + horario);

                String answerMessage = gson.toJson(horario);
                System.out.println("Sending schedule: " + answerMessage);

                MessageOutput messageOutput = new MessageOutput(answerMessage);
                client.sendEvent(Events.GET_HORARIO_SEMANAL_PROFESOR_ANSWER.value, messageOutput);
                
            } catch (Exception e) {
                e.printStackTrace();
                MessageOutput errorMessage = new MessageOutput("Error fetching professor schedule: " + e.getMessage());
                client.sendEvent(Events.GET_HORARIO_SEMANAL_PROFESOR_ANSWER.value, errorMessage);
            }
		});
	}

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
