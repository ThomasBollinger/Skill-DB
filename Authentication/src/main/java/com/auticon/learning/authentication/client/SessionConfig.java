package com.auticon.learning.authentication.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class SessionConfig {

	private static Session session = null;

	public static void initSession() {
		SessionFactory factory = new Configuration().configure("/hibernate_auth.cfg.xml").buildSessionFactory();
		session = factory.openSession();
	}

	public static void closeSession() {
		session.close();
	}

	public static Session getSession() {
		if (session == null || !session.getEntityManagerFactory().getProperties().get("hibernate.connection.url")
				.toString().endsWith("Credentials")) {
			initSession();
		}

		return session;
	}
}
