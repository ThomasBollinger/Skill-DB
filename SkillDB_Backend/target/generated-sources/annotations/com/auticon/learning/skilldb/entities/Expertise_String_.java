package com.auticon.learning.skilldb.entities;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Expertise_String.class)
public class Expertise_String_ {
	public static volatile SingularAttribute<Expertise_String, Short> expertise;
	public static volatile SingularAttribute<Expertise_String, String> fullNameString;
	public static volatile ListAttribute<Expertise_String, Skill> skills;
}
