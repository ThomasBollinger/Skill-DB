package de.auticon.beans;

import javax.validation.constraints.NotNull;

public class Skill {

	@NotNull(message = "Skill fehlt")
	private String name;
	@NotNull(message = "Bitte Ausprägung angeben")
	private short expertise;
	private String description;
	private String expertiseFullname;

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

	public String getExpertiseFullname() {
		return expertiseFullname;
	}

	public void setExpertiseFullname(String expertiseFullname) {
		this.expertiseFullname = expertiseFullname;
	}

	public void setExpertise(String fullName) {
		try {
			this.expertise = Short.valueOf(fullName.substring(0, fullName.indexOf(" - ")));
		} catch (Exception e) {
			this.expertise = 0;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
