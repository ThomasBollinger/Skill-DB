package com.auticon.learning.authentication.client;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;

import com.auticon.learning.authentication.entities.User;
import com.auticon.learning.authentication.entities.User_;

public final class DBOperations {

	private static String USER_TABLE = "PUBLIC.\"Users\"";
	private static String PW_FIELD = "password";
	private static String USERNAME_FIELD = "mail";

	public static User findUser(String username) {
		CriteriaBuilder builder = SessionConfig.getSession().getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);

		criteriaQuery.where(builder.equal(root.get(User_.MAIL), username));
		Query<User> query = SessionConfig.getSession().createQuery(criteriaQuery);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public static boolean decryptPW(User user, String givenPW) {
		Query<?> query = SessionConfig.getSession()
				.createNativeQuery("SELECT " + PW_FIELD + " = crypt('" + givenPW + "', " + PW_FIELD + ") FROM "
						+ USER_TABLE + " WHERE " + USER_TABLE + "." + USERNAME_FIELD + " = '" + user.getMail() + "';");

		try {
			return (boolean) query.getSingleResult();
		} catch (NoResultException | ClassCastException e) {
			return false;
		}
	}
}
