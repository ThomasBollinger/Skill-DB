package com.auticon.learning.skilldb.entities;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
public class Role_ {
	public static volatile SingularAttribute<Role, Integer> roleId;
	public static volatile SingularAttribute<Role, String> rolename;
	public static volatile ListAttribute<Role, Employee> employees;
}
