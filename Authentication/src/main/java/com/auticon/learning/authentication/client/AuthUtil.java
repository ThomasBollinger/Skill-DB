package com.auticon.learning.authentication.client;

import java.util.Set;

import com.auticon.learning.authentication.entities.Permission;
import com.auticon.learning.authentication.entities.Role;
import com.auticon.learning.authentication.entities.User;

public final class AuthUtil {

	public static Set<Permission> getPermissions(User user) {
		Set<Permission> permissions = user.getPermissions();
		for (Role role : user.getRoles()) {
			permissions.addAll(role.getPermissions());
		}

		return permissions;
	}
}
