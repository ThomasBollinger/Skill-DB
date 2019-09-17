package com.auticon.learning.skilldb.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Employee.class)
public class Employee_ {
	public static volatile SingularAttribute<Employee, Integer> employeeId;
	public static volatile SingularAttribute<Employee, Timestamp> createdOn;
	public static volatile SingularAttribute<Employee, String> email;
	public static volatile SingularAttribute<Employee, Date> entrydate;
	public static volatile SingularAttribute<Employee, String> firstname;
	public static volatile SingularAttribute<Employee, String> lastname;
	public static volatile SingularAttribute<Employee, Site> site;
	public static volatile ListAttribute<Employee, Role> roles;
	public static volatile ListAttribute<Employee, Skill> skills;
}
