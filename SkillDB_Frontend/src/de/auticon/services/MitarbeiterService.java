package de.auticon.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.auticon.learning.skilldb.client.api.DBInserter;
import com.auticon.learning.skilldb.client.api.ManageEmployees;
import com.auticon.learning.skilldb.client.api.Queries;
import com.auticon.learning.skilldb.client.dtos.EmployeeDTO;
import com.auticon.learning.skilldb.client.dtos.EmployeeDTO.EmployeeBuilder;
import com.auticon.learning.skilldb.client.dtos.SkillsDTO;
import com.auticon.learning.skilldb.client.dtos.SkillsDTO.SkillBuilder;
import com.auticon.learning.skilldb.entities.Employee;

import de.auticon.beans.Mitarbeiter;
import de.auticon.beans.Skill;
import de.auticon.views.MitarbeiterView;

@Named(value = "mitarbeiterService")
@ViewScoped
public class MitarbeiterService implements Serializable {

	private static final long serialVersionUID = -445070832052637147L;

	@Inject
	private MitarbeiterView view;

	public void submitEmployee(Mitarbeiter mitarbeiter) {
		new DBInserter().submitEmployee(convertToEmployeeDTO(mitarbeiter));

		PrimeFaces.current().dialog().closeDynamic(null);
	}

	public Mitarbeiter convertToMitarbeiter(EmployeeDTO dto) {
		Mitarbeiter mitarbeiter = new Mitarbeiter();

		if (dto.getSkills() != null) {
			List<Skill> skills = convertDTOtoSkills(dto.getSkills());
			mitarbeiter.setSkills(skills);
		}

		mitarbeiter.setVorname(dto.getVorname());
		mitarbeiter.setName(dto.getName());
		mitarbeiter.setEmail(dto.getEmail());
		mitarbeiter.setEntryDate(dto.getEntryDate());
		mitarbeiter.setRole(dto.getRole());
		mitarbeiter.setSite(dto.getSite());
		mitarbeiter.setTravelReady(dto.isTravelReady());

		if (dto.getEnglish() != 0) {
			mitarbeiter.setEnglish(
					dto.getEnglish() + " - " + Queries.getExpertiseString(dto.getEnglish()).getFullNameString());
		} else {
			mitarbeiter.setEnglish("0");
		}

		return mitarbeiter;
	}

	private EmployeeDTO convertToEmployeeDTO(Mitarbeiter mitarbeiter) {
		List<SkillsDTO> skills = convertSkillsToDTO(mitarbeiter.getSkills());
		short english = 0;

		String englishString = mitarbeiter.getEnglish().substring(0, mitarbeiter.getEnglish().indexOf(" - "));
		try {
			english = Short.valueOf(englishString);
		} catch (Exception e) {
			english = 0;
		}

		EmployeeDTO employeeDto = new EmployeeBuilder().setVorname(mitarbeiter.getVorname())
				.setName(mitarbeiter.getName()).setEmail(mitarbeiter.getEmail())
				.setEntryDate(mitarbeiter.getEntryDate()).setRole(mitarbeiter.getRole()).setSite(mitarbeiter.getSite())
				.setSkills(skills).setTravelReady(mitarbeiter.isTravelReady()).setEnglish(english).build();

		return employeeDto;
	}

	private List<SkillsDTO> convertSkillsToDTO(List<Skill> skillsList) {
		List<SkillsDTO> skills = new ArrayList<SkillsDTO>();

		for (Skill singleSkill : skillsList) {
			SkillsDTO skillsDTO = new SkillBuilder().setName(singleSkill.getName())
					.setExpertise(singleSkill.getExpertise()).setDescription(singleSkill.getDescription()).build();

			skills.add(skillsDTO);
		}
		return skills;
	}

	private List<Skill> convertDTOtoSkills(List<SkillsDTO> dto) {
		List<Skill> skills = new ArrayList<Skill>();

		for (SkillsDTO singleDTO : dto) {
			Skill skill = new Skill();
			skill.setName(singleDTO.getName());
			skill.setExpertise(singleDTO.getExpertise());
			skill.setDescription(singleDTO.getDescription());
			skill.setExpertiseFullname(singleDTO.getExpertise() + " - "
					+ Queries.getExpertiseString(singleDTO.getExpertise()).getFullNameString());

			skills.add(skill);
		}
		return skills;
	}

	public void deleteEmployee(Employee mitarbeiter) {
		if (mitarbeiter != null) {
			ManageEmployees.deleteEmployee(mitarbeiter.getEmployeeId());
			view.updateMitarbeiter();
		}
	}
}
