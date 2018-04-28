package com.rlima1877.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rlima1877.hibernate.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// use the session object to save Java object
			System.out.println("Creating new student object...");
			
			// create a student object
			Student tempStudent = new Student("Paul", "Wall", "paul@gmail.com");
			
			// start a transaction
			session.beginTransaction();
			
			System.out.println("Saving the student...");			
			// save the student object
			session.save(tempStudent);
			
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("All done...");
			
		}
		finally {
			factory.close();
		}
		
	}

}
