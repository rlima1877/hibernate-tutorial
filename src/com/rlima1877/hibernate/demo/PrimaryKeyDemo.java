package com.rlima1877.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rlima1877.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

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
			System.out.println("Creating 3 new student objects...");
			
			// create 3 student objects
			Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
			Student tempStudent2 = new Student("Albert", "Freedom", "albert@luv2code.com");
			Student tempStudent3 = new Student("Chuck", "Norris", "chuck@luv2code.com");
			
			// start a transaction
			session.beginTransaction();
			
			System.out.println("Saving the student...");			
			// save the student object
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("All done...");
			
		}
		finally {
			factory.close();
		}
	}

}
