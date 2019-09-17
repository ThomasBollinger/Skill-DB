package com.auticon.learning.authentication.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Permission.class)
public abstract class Permission_ {

	public static volatile SetAttribute<Permission, Role> roles;
	public static volatile SingularAttribute<Permission, String> action;
	public static volatile SingularAttribute<Permission, String> description;
	public static volatile SingularAttribute<Permission, Integer> id;
	public static volatile SetAttribute<Permission, User> users;

	public static final String ROLES = "roles";
	public static final String ACTION = "action";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String USERS = "users";

}

