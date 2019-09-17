package de.auticon.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.auticon.learning.skilldb.client.api.Queries;
import com.auticon.learning.skilldb.client.dtos.EmployeeDTO;
import com.auticon.learning.skilldb.client.dtos.SkillsDTO;
import com.auticon.learning.skilldb.entities.Expertise_String;

import de.auticon.beans.Mitarbeiter;
import de.auticon.beans.Skill;
import de.auticon.services.MitarbeiterService;

@Named(value = "skillView")
@ViewScoped
public class SkillView implements Serializable {
	private static final long serialVersionUID = -3256509521249071048L;

	@Inject
	private MitarbeiterView mitarbeiterView;
	@Inject
	private MitarbeiterService mitarbeiterService;

	private Mitarbeiter employee;
	private String autocompletedSkillname;
	private String expertise;
	private Skill skill = new Skill();
	private List<String> expertises = new ArrayList<String>();

	@PostConstruct
	public void init() {
		List<Expertise_String> expertiseObjects = Queries.findAllExpertises();
		for (Expertise_String singleExpertise : expertiseObjects) {
			expertises.add(singleExpertise.getExpertise() + " - " + singleExpertise.getFullNameString());
		}
	}

	public void addSkill() {
		employee.getSkills().add(skill);
		closeDialog();
	}

	public void closeDialog() {
		PrimeFaces.current().dialog().closeDynamic(null);
	}

	public void filterBySkills(String skill, String expertise) {
		List<EmployeeDTO> dtos = new ArrayList<EmployeeDTO>();
		List<Mitarbeiter> mitarbeiter = new ArrayList<Mitarbeiter>();

		if (expertise.isEmpty()) {
			dtos = Queries.findEmployeesBySkill(skill);
		} else {
			dtos = Queries.findEmployeesBySkillAndExpertiseGtE(skill,
					Short.valueOf(expertise.substring(0, expertise.indexOf(" - "))));
		}

		for (EmployeeDTO employeeDTO : dtos) {
			mitarbeiter.add(mitarbeiterService.convertToMitarbeiter(employeeDTO));
		}

		mitarbeiterView.setMitarbeiter(mitarbeiter);
		closeDialog();
	}

	public List<String> autocompleteSkill(String query) {
		List<String> results = new ArrayList<String>();
		Collection<SkillsDTO> availableSkills = Queries.findAllAvailableSkills();

		for (SkillsDTO skill : availableSkills) {
			if (skill.getName().toLowerCase().startsWith(query.toLowerCase())) {
				results.add(skill.getName());
			}
		}

		return results;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public List<String> getExpertises() {
		return expertises;
	}

	public void setExpertises(List<String> expertises) {
		this.expertises = expertises;
	}

	public Mitarbeiter getEmployee() {
		return employee;
	}

	public void setEmployee(Mitarbeiter employee) {
		this.employee = employee;
	}

	public String getAutocompletedSkillname() {
		return autocompletedSkillname;
	}

	public void setAutocompletedSkillname(String autocompletedSkillname) {
		this.autocompletedSkillname = autocompletedSkillname;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
}
