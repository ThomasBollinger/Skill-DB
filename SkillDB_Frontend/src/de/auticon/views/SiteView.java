package de.auticon.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.auticon.learning.skilldb.client.api.DBInserter;
import com.auticon.learning.skilldb.client.api.Queries;
import com.auticon.learning.skilldb.client.dtos.EmployeeDTO;

import de.auticon.beans.Mitarbeiter;
import de.auticon.services.MitarbeiterService;

@Named(value = "siteView")
@ViewScoped
public class SiteView implements Serializable {

	private static final long serialVersionUID = -6599498896339181430L;

	@Inject
	MitarbeiterService mitarbeiterService;

	private String sitename;
	private String selectedSite;
	private List<Mitarbeiter> employees;

	@PostConstruct
	public void init() {
		sitename = "";
		employees = new ArrayList<>();
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getSitename() {
		return sitename;
	}

	public void addSite(String newSite) {
		new DBInserter().insertSite(newSite);
		PrimeFaces.current().dialog().closeDynamic(newSite);
	}

	public String getSelectedSite() {
		return selectedSite;
	}

	public void setSelectedSite(String selectedSite) {
		this.selectedSite = selectedSite;
	}

	public List<Mitarbeiter> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Mitarbeiter> employees) {
		this.employees = employees;
	}
	
	public void filter() {
		List<EmployeeDTO> dtos = Queries.findEmployeesBySite(selectedSite);

		for (EmployeeDTO employeeDTO : dtos) {
			employees.add(mitarbeiterService.convertToMitarbeiter(employeeDTO));
		}
	}

	public void showNone() {
		employees.clear();
	}
}
