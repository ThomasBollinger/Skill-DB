package com.auticon.learning.skilldb.client.dtos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.auticon.learning.skilldb.client.api.DBInserter;
import com.auticon.learning.skilldb.client.api.Queries;
import com.auticon.learning.skilldb.entities.Employee;
import com.auticon.learning.skilldb.entities.Role;
import com.auticon.learning.skilldb.entities.Skill;

public class EmployeeDTO {

	private String name;
	private String vorname;
	private String email;
	private Date entryDate;
	private Set<String> role;
	private String site;
	private Timestamp createdOn;
	private List<SkillsDTO> skills;
	private boolean travelReady;
	private short english;

	private EmployeeDTO(String name, String vorname, String email, Date entryDate, Set<String> role, String site,
			List<SkillsDTO> skills, boolean travelReady, short english) {

		this.name = name;
		this.vorname = vorname;
		this.email = email;
		this.entryDate = entryDate;
		this.role = role;
		this.site = site;
		this.skills = skills;
		this.createdOn = new Timestamp(System.currentTimeMillis());
		this.travelReady = travelReady;
		this.english = english;
	}

	public EmployeeDTO(Employee employee) {
		Set<String> roles = new HashSet<String>();
		List<SkillsDTO> skills = new ArrayList<SkillsDTO>();

		this.vorname = employee.getFirstname();
		this.name = employee.getLastname();
		this.email = employee.getEmail();
		this.entryDate = employee.getEntrydate();
		this.site = employee.getSite().getSitename();
		this.travelReady = employee.getTravelReady() != null ? employee.getTravelReady() : false;
		this.english = employee.getEnglish() != null ? employee.getEnglish().getExpertise() : 0;

		for (Role singleRole : employee.getRoles()) {
			roles.add(singleRole.getRolename());
		}

		for (Skill singleSkill : employee.getSkills()) {
			SkillsDTO dto = new SkillsDTO(singleSkill);
			dto.setName(singleSkill.getSkill());
			dto.setExpertise(singleSkill.getExpertiseString().getExpertise());
			dto.setDescription(singleSkill.getDescription());

			skills.add(dto);
		}

		this.role = roles;
		this.skills = skills;
	}

	public Employee toEmployee() {
		Employee employee = new Employee();
		Set<Role> roles = new HashSet<Role>();
		Set<Skill> skills = new HashSet<Skill>();

		employee.setFirstname(vorname);
		employee.setLastname(name);
		employee.setEmail(email);
		employee.setCreatedOn(createdOn);
		employee.setEntrydate(entryDate);
		employee.setTravelReady(travelReady);
		employee.setEnglish(Queries.getExpertiseString(english));

		if (DTOUtils.existSite(site)) {
			employee.setSite(Queries.findSiteByName(site));
		} else {
			employee.setSite(new DBInserter().insertSite(site));
		}

		for (String singleRole : role) {
			if (DTOUtils.existRole(singleRole)) {
				roles.add(Queries.findRoleByName(singleRole));
			} else {
				roles.add(new DBInserter().insertRole(singleRole));
			}
		}
		employee.setRoles(roles);

		for (SkillsDTO singleSkill : this.skills) {
			if (DTOUtils.existSkill(singleSkill)) {
				skills.add(Queries.findSkillByNameAndDescription(singleSkill));
			} else {
				skills.add(new DBInserter().insertSkill(singleSkill));
			}
		}
		employee.setSkills(skills);

		return employee;
	}

	public String getName() {
		return name;
	}

	public String getVorname() {
		return vorname;
	}

	public String getEmail() {
		return email;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public Set<String> getRole() {
		return role;
	}

	public String getSite() {
		return site;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public List<SkillsDTO> getSkills() {
		return skills;
	}

	public boolean isTravelReady() {
		return travelReady;
	}

	public short getEnglish() {
		return english;
	}

	public static class EmployeeBuilder {
		private String name;
		private String vorname;
		private String email;
		private Date entryDate;
		private Set<String> role;
		private String site;
		private List<SkillsDTO> skills;
		private boolean travelReady;
		private short english;

		public EmployeeBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public EmployeeBuilder setVorname(String vorname) {
			this.vorname = vorname;
			return this;
		}

		public EmployeeBuilder setEmail(String email) {
			this.email = email;
			return this;
		}

		public EmployeeBuilder setEntryDate(Date entryDate) {
			this.entryDate = entryDate;
			return this;
		}

		public EmployeeBuilder setRole(Set<String> role) {
			this.role = role;
			return this;
		}

		public EmployeeBuilder setSite(String site) {
			this.site = site;
			return this;
		}

		public EmployeeBuilder setSkills(List<SkillsDTO> skills) {
			this.skills = skills;
			return this;
		}

		public EmployeeBuilder setTravelReady(boolean travelReady) {
			this.travelReady = travelReady;
			return this;
		}

		public EmployeeBuilder setEnglish(short english) {
			this.english = english;
			return this;
		}

		public EmployeeDTO build() {
			return new EmployeeDTO(name, vorname, email, entryDate, role, site, skills, travelReady, english);
		}
	}
}
