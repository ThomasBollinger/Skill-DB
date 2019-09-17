package com.auticon.learning.skilldb.client.api;

import org.hibernate.Session;

import com.auticon.learning.skilldb.client.SessionConfig;
import com.auticon.learning.skilldb.entities.Employee;

public class ManageEmployees {

	public static void deleteEmployee(int id) {
		Session session = SessionConfig.getSession();
		Employee employee = session.get(Employee.class, id);

		if (employee != null) {
			session.beginTransaction();
			session.delete(employee);
			session.getTransaction().commit();
		}
	}
}
