package com.rlima1877.hibernate.demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rlima1877.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		List<Student> theStudents;

		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
						
			// set email = 'foo@gmail.com' where student id = 1 
			System.out.println("\n\nUpdating email = 'foo@gmail.com' where student id = 1 ");
			session.createQuery("update Student set email='foo@gmail.com' where id=1 ").executeUpdate();
			
			// query all students from database
			theStudents = session.createQuery("from Student").getResultList();
			
			// display all the students
			System.out.println("\n\nAll records in the database Student");
			displayStudents(theStudents);
			
			// objects can also be updated using setters
			
			int studentId = 2;
			
			Student myStudent = session.get(Student.class, studentId);
			
			// you can change any property using setters, this will change the property only in memory.
			myStudent.setFirstName("Scooooooooby");
						
			// commit the transaction (here we're actually updating the value within the database.)
			session.getTransaction().commit();
			
			// display all the students
			System.out.println("\n\nAll records in the database Student");
			displayStudents(theStudents);
			
			System.out.println("All done...");
			
		}
		finally {
			// finally will be executed even if there's an exception/error above. (good practice to close)
			System.out.println("Closing the factory...");
			factory.close();
		}
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
