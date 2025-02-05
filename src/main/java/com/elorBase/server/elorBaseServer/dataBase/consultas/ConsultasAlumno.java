package com.elorBase.server.elorBaseServer.dataBase.consultas;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.elorBase.server.elorBaseServer.HibernateUtil;

public class ConsultasAlumno {
	
	public static List<Object[]> mostrar_horario_alumno(int id) {
		List<Object[]> results = null;
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();

		Session session = sesion.openSession();
		Transaction transaction = session.beginTransaction();

		String hql = "SELECT h.hora, h.dia, h.aula, a.nombre, a.idAsignatura, p.nombre, p.apellidos "
		           + "FROM com.elorBase.server.elorBaseServer.dataBase.entity.Alumno al "
		           + "JOIN com.elorBase.server.elorBaseServer.dataBase.entity.Matriculacion m "
		           + "ON al.idAlumno = m.idAlumno "
		           + "JOIN com.elorBase.server.elorBaseServer.dataBase.entity.Formacion f "
		           + "ON m.idCiclo = f.id.idCiclo "
		           + "JOIN com.elorBase.server.elorBaseServer.dataBase.entity.Asignatura a "
		           + "ON f.id.idAsignatura = a.idAsignatura "
		           + "LEFT JOIN com.elorBase.server.elorBaseServer.dataBase.entity.Profesor p "
		           + "ON a.idProfesor = p.idProfesor "
		           + "JOIN com.elorBase.server.elorBaseServer.dataBase.entity.Horario h "
		           + "ON a.idAsignatura = h.idAsignatura "
		           + "WHERE al.idAlumno = :idAlumno";

		
		Query q = session.createQuery(hql);
		q.setParameter("idAlumno", id);

		results = q.list();
		transaction.commit();
		return results;
	}
	
	public static List<Object[]> mostrar_cursos() {
		List<Object[]> results = null;
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();

		Session session = sesion.openSession();
		Transaction transaction = session.beginTransaction();
		
		String hql = "SELECT ";
		
		return results;
	}

}
