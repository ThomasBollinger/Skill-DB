package com.auticon.learning.skilldb.entities;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Skill.class)
public class Skill_ {
	public static volatile SingularAttribute<Skill, Integer> id;
	public static volatile SingularAttribute<Skill, String> description;
	public static volatile SingularAttribute<Skill, String> skill;
	public static volatile SingularAttribute<Skill, Expertise_String> expertiseString;
	public static volatile ListAttribute<Skill, Employee> employees;
}
