package com.auticon.learning.skilldb.client.dtos;

import com.auticon.learning.skilldb.client.api.Queries;
import com.auticon.learning.skilldb.entities.Skill;

public class SkillsDTO {

	private String name;
	private short expertise;
	private String description;

	private SkillsDTO(String name, short expertise, String description) {
		this.name = name;
		this.expertise = expertise;
		this.description = description;
	}

	public SkillsDTO(Skill skill) {
		this.name = skill.getSkill();
		this.expertise = skill.getExpertiseString().getExpertise();
		this.description = skill.getDescription();
	}

	public Skill toSkill() {
		Skill skill = new Skill();

		skill.setSkill(name);
		skill.setExpertiseString(Queries.getExpertiseString(expertise));
		skill.setDescription(description);

		return skill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getExpertise() {
		return expertise;
	}

	public void setExpertise(short expertise) {
		this.expertise = expertise;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static class SkillBuilder {
		private String name;
		private short expertise;
		private String description;

		public SkillBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public SkillBuilder setExpertise(short expertise) {
			this.expertise = expertise;
			return this;
		}

		public SkillBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		public SkillsDTO build() {
			return new SkillsDTO(name, expertise, description);
		}
	}
}
