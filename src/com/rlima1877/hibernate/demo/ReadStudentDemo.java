package com.rlima1877.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rlima1877.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			System.out.println("Creating new student object...");
			
			// create a student object
			Student tempStudent = new Student("Michael", "Jackson", "michael@luv2code.com");
			
			// start a transaction
			session.beginTransaction();
			
			System.out.println("Saving the student...");			
			// save the student object
			session.save(tempStudent);			
			
			// commit the transaction
			session.getTransaction().commit();
			
			// find out the student's id: primary key.
			System.out.println("Saved stuent. Generated id: " + tempStudent.getId());
			
			// now get a new session and start a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based the id: primary key
			System.out.println("\nGetting student with id: " + tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get complete: " + myStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("All done...");
			
		}
		finally {
			factory.close();
		}
		
	}

}
