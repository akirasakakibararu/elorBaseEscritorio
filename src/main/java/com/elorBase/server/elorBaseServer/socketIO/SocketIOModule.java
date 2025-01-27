package com.elorBase.server.elorBaseServer.socketIO;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

public class SocketIOModule {
	private SocketIOServer server = null;

	public SocketIOModule(SocketIOServer server) {
		super();
		this.server = server;

		// Default events (for control the connection of clients)
		server.addConnectListener(onConnect());
		server.addDisconnectListener(onDisconnect());

		// Custom events
		//server.addEventListener(Events.OBTENER_ASIGNATURAS.value, MessageInput.class, this.obtenerAsignaturas());
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
