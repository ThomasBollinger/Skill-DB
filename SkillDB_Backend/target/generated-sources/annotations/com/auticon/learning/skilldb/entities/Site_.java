package com.auticon.learning.skilldb.entities;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Site.class)
public class Site_ {
	public static volatile SingularAttribute<Site, Integer> siteId;
	public static volatile SingularAttribute<Site, String> sitename;
	public static volatile ListAttribute<Site, Employee> employees;
}
