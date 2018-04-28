package com.rlima1877.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rlima1877.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			// retrieve object and delete
			
			int studentId = 2;
			
			Student myStudent = session.get(Student.class, studentId);
			
			//delete the student 
			session.delete(myStudent);
		
			// delete without retrieving object
			session.createQuery("delete from Student WHERE id=3").executeUpdate();
			
			session.getTransaction().commit();	
		}
		finally {
			factory.close();
		}
		
	}

}
