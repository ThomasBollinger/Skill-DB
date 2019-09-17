package com.auticon.learning.authentication.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ {

	public static volatile SingularAttribute<Role, String> rolename;
	public static volatile SetAttribute<Role, Permission> permissions;
	public static volatile SingularAttribute<Role, Integer> id;
	public static volatile SetAttribute<Role, User> users;

	public static final String ROLENAME = "rolename";
	public static final String PERMISSIONS = "permissions";
	public static final String ID = "id";
	public static final String USERS = "users";

}

