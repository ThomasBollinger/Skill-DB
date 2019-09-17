package com.auticon.learning.authentication.api;

import com.auticon.learning.authentication.client.DBOperations;
import com.auticon.learning.authentication.entities.User;

public final class Login {

	public static boolean checkPW(String username, String pw) {
		User user = DBOperations.findUser(username);

		if (user == null) {
			return false;
		} else if (!DBOperations.decryptPW(user, pw)) {
			return false;
		}

		return true;
	}
}
