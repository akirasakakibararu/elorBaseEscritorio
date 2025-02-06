package com.elorBase.server.elorBaseServer.dataBase.consultas;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.elorBase.server.elorBaseServer.HibernateUtil;
import com.elorBase.server.elorBaseServer.dataBase.entity.Horarioreunion;
import com.elorBase.server.elorBaseServer.dataBase.entity.Reunion;
import com.elorBase.server.elorBaseServer.dataBase.entity.Solicitud;

public class ConsultasProfesor {

	public static List<Object[]> mostrar_horario_profesor(int id) {

		List<Object[]> results = null;

		SessionFactory sesion = HibernateUtil.getSessionFactory();

		Session session = sesion.openSession();
		Transaction transaction = session.beginTransaction();

		String hql = "SELECT h.hora, h.dia, h.aula, h.idAsignatura, a.nombre "
				+ "FROM Horario h "
				+ "JOIN Asignatura a ON h.idAsignatura = a.idAsignatura "
				+ "WHERE a.idProfesor = :idProfesor";

		Query q = session.createQuery(hql);
		q.setParameter("idProfesor", id);

		results = q.list();
		transaction.commit();
		return results;
	}

	public static List<Object[]> mostrar_reuniones_por_receptor(String profesor) {

	    List<Object[]> results = null;

	    SessionFactory sesion = HibernateUtil.getSessionFactory();
	    Session session = sesion.openSession();
	    Transaction transaction = session.beginTransaction();

	    String hql = "SELECT r.titulo, r.asunto, hr.dia, hr.hora, hr.aula, r.solicitante, r.receptor "
	               + "FROM Reunion r "
	               + "JOIN Horarioreunion hr ON r.idReunion = hr.idReunion "
	               + "WHERE r.receptor = :receptorBuscado";

	    Query query = session.createQuery(hql);
	    query.setParameter("receptorBuscado", profesor);

	    results = query.list();
	    transaction.commit();

	    session.close();
	    return results;
	}

	public static void crear_reunion(String solicitanteIntroducido, String receptorIntroducido,
			String tituloIntroducido, String asuntoIntroducido, int idProfesorIntroducido, String diaIntroducido,
			int horaIntroducido, int aulaIntroducido) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();

		Reunion reunion = new Reunion();
		reunion.setSolicitante(solicitanteIntroducido);
		reunion.setReceptor(receptorIntroducido);
		reunion.setEstado("Solicitada");
		reunion.setTitulo(tituloIntroducido);
		reunion.setAsunto(asuntoIntroducido);

		session.save(reunion);
		session.flush();
		Integer idReunionGenerado = reunion.getIdReunion();

		Solicitud solicitud = new Solicitud();
		solicitud.setIdReunion(idReunionGenerado);
		solicitud.setConfirmada("N");
		solicitud.setIdProfesor(idProfesorIntroducido);
		session.save(solicitud);

		Horarioreunion horarioReunion = new Horarioreunion();
		horarioReunion.setDia(diaIntroducido);
		horarioReunion.setHora(horaIntroducido);
		horarioReunion.setAula(aulaIntroducido);
		horarioReunion.setIdReunion(idReunionGenerado);
		session.save(horarioReunion);

		tx.commit();

		session.close();
	}

	public static void cambiar_estado_reunion(String estado_nuevo, int idReunion) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();

		List<String> estadosPermitidos = Arrays.asList("Pendiente", "Aceptada", "Confirmada", "Cancelada", "Forzada");

		if (!estadosPermitidos.contains(estado_nuevo)) {
			System.out.println("El estado '" + estado_nuevo + "' no es válido.");
			return;
		}

		int idReunionModificar = idReunion;
		String estadoModificar = estado_nuevo;

		Reunion reunion = session.get(Reunion.class, idReunionModificar);

		if (reunion != null) {
			if (reunion.getEstado().equals(estadoModificar)) {
	            System.out.println("La reunión ya está " + estadoModificar);
	            tx.commit();
	            session.close();
	            return;
	        }
			
			reunion.setEstado(estado_nuevo);
	        session.update(reunion);
	        System.out.println("El estado de la reunión con ID " + idReunion + " Pasa a ser: " + estado_nuevo);
		} else {
			System.out.println("No existe la reunión con id: " + idReunionModificar);
		}

		tx.commit();
		session.close();
	}
}
