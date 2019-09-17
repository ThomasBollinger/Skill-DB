package de.auticon.views;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import de.auticon.beans.Mitarbeiter;

@Named(value = "adminView")
@ViewScoped
public class AdminView implements Serializable {

	private static final long serialVersionUID = 5252224062484767900L;

	@Inject
	private SkillView skillView;
	private static Mitarbeiter mitarbeiter;

	public void addNewEmployee() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("id", "newEmployeeDialogID");
		options.put("widgetVar", "newEmployeeDialogVar");
		options.put("resizable", false);
		options.put("modal", false);
		PrimeFaces.current().dialog().openDynamic("/dialogs/newEmployee", options, null);
	}

	public void addNewSite() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("id", "newSiteDialogID");
		options.put("widgetVar", "newSiteDialogVar");
		options.put("resizable", true);
		options.put("modal", false);
		options.put("contentWidth", 420);
		PrimeFaces.current().dialog().openDynamic("/dialogs/newSite", options, null);
	}

	public void openNewSkillDialog(Mitarbeiter employee) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", true);
		options.put("modal", false);
		PrimeFaces.current().dialog().openDynamic("/dialogs/newSkill", options, null);

		skillView.setEmployee(employee);
	}

	public void openSkillDialog(Mitarbeiter employee) {
		Map<String, Object> options = new HashMap<String, Object>();
		mitarbeiter = employee;
		options.put("header", "Skillsheet von " + employee.getVorname() + " " + employee.getName());
		options.put("resizable", true);
		options.put("modal", true);
		PrimeFaces.current().dialog().openDynamic("/dialogs/skillsDialog", options, null);
	}

	public void openSkillsSearchDialog() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", true);
		options.put("modal", true);
		PrimeFaces.current().dialog().openDynamic("/dialogs/skillsSearch", options, null);
	}

	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}

	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		AdminView.mitarbeiter = mitarbeiter;
	}
}
