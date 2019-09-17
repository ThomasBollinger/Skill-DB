package com.auticon.learning.authentication.api;

import com.auticon.learning.authentication.client.AuthUtil;
import com.auticon.learning.authentication.client.DBOperations;
import com.auticon.learning.authentication.entities.Permission;
import com.auticon.learning.authentication.entities.User;

public final class Permissionchecker {

	public static boolean isUserAllowedForAction(String username, String action) {
		User user = DBOperations.findUser(username);

		for (Permission singlePermission : AuthUtil.getPermissions(user)) {
			if (singlePermission.getAction().equals(action)) {
				return true;
			}
		}

		return false;
	}
}