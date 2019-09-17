package com.auticon.learning.authentication.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> mail;
	public static volatile SetAttribute<User, Permission> permissions;
	public static volatile SetAttribute<User, Role> roles;
	public static volatile SingularAttribute<User, Integer> id;

	public static final String PASSWORD = "password";
	public static final String MAIL = "mail";
	public static final String PERMISSIONS = "permissions";
	public static final String ROLES = "roles";
	public static final String ID = "id";

}

