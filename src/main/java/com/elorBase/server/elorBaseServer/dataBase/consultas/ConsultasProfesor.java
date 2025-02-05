package com.elorBase.server.elorBaseServer.dataBase.consultas;

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

		String hql = "SELECT h.hora, h.dia, h.aula, h.idAsignatura, a.nombre " + "FROM Horario h "
				+ "JOIN Asignatura a ON h.idAsignatura = a.idAsignatura " + "WHERE a.idProfesor = :idProfesor";

		Query q = session.createQuery(hql);
		q.setParameter("idProfesor", id);

		results = q.list();
		transaction.commit();
		return results;
	}

	public static List<Object[]> mostrar_horario_reunion(int id) {

		List<Object[]> results = null;

		SessionFactory sesion = HibernateUtil.getSessionFactory();

		Session session = sesion.openSession();

		String hql = "SELECT r.solicitante, r.receptor, r.estado, r.titulo, r.asunto, "
				+ " hr.dia, hr.hora, hr.aula" 
				+ "FROM HorarioReunion hr "
				+ "JOIN Reunion r ON hr.idReunion = r.idReunion " + "WHERE a.idProfesor = :idProfesor";

		Query q = session.createQuery(hql);
		q.setParameter("idProfesor", id);

		results = q.list();

		return results;
	}
	
	public static void crear_reunion(
			String solicitanteIntroducido,
		    String receptorIntroducido,
		    String tituloIntroducido,
		    String asuntoIntroducido,
		    int idProfesorIntroducido,
		    String diaIntroducido,
		    int horaIntroducido,
		    int aulaIntroducido
		) {
		    SessionFactory sesion = HibernateUtil.getSessionFactory();
		    Session session = sesion.openSession();
		    Transaction tx = session.beginTransaction();

		    // Crear objeto reunion
		    Reunion reunion = new Reunion();
		    reunion.setSolicitante(solicitanteIntroducido);
		    reunion.setReceptor(receptorIntroducido);
		    reunion.setEstado("Solicitada");
		    reunion.setTitulo(tituloIntroducido);
		    reunion.setAsunto(asuntoIntroducido);
		    // Guardar la reunion y obtener el ID generado
		    session.save(reunion);
		    session.flush();
		    Integer idReunionGenerado = reunion.getIdReunion();

		    // Insertar en la tabla solicitud
		    Solicitud solicitud = new Solicitud();
		    solicitud.setIdReunion(idReunionGenerado);
		    solicitud.setConfirmada("N");
		    solicitud.setIdProfesor(idProfesorIntroducido); // Referencia al profesor
		    session.save(solicitud);

		    // Insertar en la tabla horarioReunion
		    Horarioreunion horarioReunion = new Horarioreunion();
		    horarioReunion.setDia(diaIntroducido);
		    horarioReunion.setHora(horaIntroducido);
		    horarioReunion.setAula(aulaIntroducido);
		    horarioReunion.setIdReunion(idReunionGenerado); // Relación con la reunion
		    session.save(horarioReunion);

		    // Confirmar la transacción
		    tx.commit();

		    // Cerrar la sesión
		    session.close();
	}
}
