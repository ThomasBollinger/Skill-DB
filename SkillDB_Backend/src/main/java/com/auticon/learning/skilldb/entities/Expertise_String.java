package com.auticon.learning.skilldb.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the "Expertise_Strings" database table.
 * 
 */
@Entity
@Table(name = "\"Expertise_Strings\"")
@NamedQuery(name = "Expertise_String.findAll", query = "SELECT e FROM Expertise_String e")
public class Expertise_String implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Short expertise;

	@Column(name = "full_name_string")
	private String fullNameString;

	// bi-directional many-to-one association to Skill
	@OneToMany(mappedBy = "expertiseString", fetch = FetchType.EAGER)
	private Set<Skill> skills;

	public Expertise_String() {
	}

	public Short getExpertise() {
		return this.expertise;
	}

	public void setExpertise(Short expertise) {
		this.expertise = expertise;
	}

	public String getFullNameString() {
		return this.fullNameString;
	}

	public void setFullNameString(String fullNameString) {
		this.fullNameString = fullNameString;
	}

	public Set<Skill> getSkills() {
		return this.skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public Skill addSkill(Skill skill) {
		getSkills().add(skill);
		skill.setExpertiseString(this);

		return skill;
	}

	public Skill removeSkill(Skill skill) {
		getSkills().remove(skill);
		skill.setExpertiseString(null);

		return skill;
	}

}