package com.elorBase.server.elorBaseServer.dataBase.consultas;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.elorBase.server.elorBaseServer.HibernateUtil;

public class ConsultasProfesor {

	public static List<Object[]> mostrar_horario_profesor(int id) {
		
		List<Object[]> results = null;

		SessionFactory sesion = HibernateUtil.getSessionFactory();

		Session session = sesion.openSession();

		String hql = "SELECT h.hora, h.dia, h.aula, a.nombre " + "FROM Horario h "
				+ "JOIN Asignatura a ON h.idAsignatura = a.idAsignatura " + "WHERE a.idProfesor = :idProfesor";

		Query q = session.createQuery(hql);
		q.setParameter("idProfesor", id);

		results = q.list();
		
		return results;
	}
}
