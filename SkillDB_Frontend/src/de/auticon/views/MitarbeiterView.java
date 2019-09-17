package de.auticon.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.auticon.learning.skilldb.client.SessionConfig;
import com.auticon.learning.skilldb.client.api.Queries;
import com.auticon.learning.skilldb.client.dtos.EmployeeDTO;

import de.auticon.beans.Mitarbeiter;
import de.auticon.services.MitarbeiterService;
import de.auticon.util.Commons;

@Named(value = "mitarbeiterView")
@ViewScoped
public class MitarbeiterView implements Serializable {

	private static final long serialVersionUID = 7924178697538784022L;

	@Inject
	MitarbeiterService mitarbeiterService;

	private List<Mitarbeiter> mitarbeiter;
	private Mitarbeiter selectedEmployee;

	@PostConstruct
	public void init() {
		SessionConfig.initSession();
		new Commons();
		updateMitarbeiter();
	}

	public void updateMitarbeiter() {
		mitarbeiter = new ArrayList<Mitarbeiter>();
		List<EmployeeDTO> dtos = Queries.findAllEmployees();

		for (EmployeeDTO employeeDTO : dtos) {
			mitarbeiter.add(mitarbeiterService.convertToMitarbeiter(employeeDTO));
		}

		update();
	}

	public List<Mitarbeiter> getMitarbeiter() {
		return mitarbeiter;
	}

	public void setMitarbeiter(List<Mitarbeiter> mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}

	public void setSelectedEmployee(Mitarbeiter selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

	public Mitarbeiter getSelectedEmployee() {
		return selectedEmployee;
	}

	public void update() {
		PrimeFaces.current().ajax().update("form:table");
	}
}
