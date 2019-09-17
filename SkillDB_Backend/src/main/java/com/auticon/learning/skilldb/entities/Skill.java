package com.auticon.learning.skilldb.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the "Skills" database table.
 * 
 */
@Entity
@Table(name = "\"Skills\"")
@NamedQuery(name = "Skill.findAll", query = "SELECT s FROM Skill s")
public class Skill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	private String description;

	private String skill;

	// bi-directional many-to-many association to Employee
	@ManyToMany(mappedBy = "skills", fetch = FetchType.EAGER)
	private Set<Employee> employees;

	// bi-directional many-to-one association to Expertise_String
	@ManyToOne
	@JoinColumn(name = "expertise")
	private Expertise_String expertiseString;

	public Skill() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSkill() {
		return this.skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Expertise_String getExpertiseString() {
		return this.expertiseString;
	}

	public void setExpertiseString(Expertise_String expertiseString) {
		this.expertiseString = expertiseString;
	}

}