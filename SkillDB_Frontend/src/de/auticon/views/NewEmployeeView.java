package de.auticon.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.auticon.learning.skilldb.client.api.Queries;
import com.auticon.learning.skilldb.entities.Role;
import com.auticon.learning.skilldb.entities.Site;

import de.auticon.beans.Mitarbeiter;
import de.auticon.beans.Skill;

@Named(value = "newEmployeeView")
@ViewScoped
public class NewEmployeeView implements Serializable {

	private static final long serialVersionUID = 789108010781037452L;

	private List<String> sites = new ArrayList<String>();
	private List<String> roles = new ArrayList<String>();
	private Skill skill = new Skill();

	@ManagedProperty(value = "#{mitarbeiter}")
	private Mitarbeiter newEmployee = new Mitarbeiter();

	@PostConstruct
	public void init() {
		List<Site> siteObjects = Queries.findAllSites();
		List<Role> roleObjects = Queries.findAllRoles();

		for (Site singleSite : siteObjects) {
			sites.add(singleSite.getSitename());
		}

		for (Role singleRole : roleObjects) {
			roles.add(singleRole.getRolename());
		}
	}

	public List<String> getSites() {
		return sites;
	}

	public void setSites(List<String> sites) {
		this.sites = sites;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Mitarbeiter getNewEmployee() {
		return newEmployee;
	}

	public void setNewEmployee(Mitarbeiter mitarbeiter) {
		this.newEmployee = mitarbeiter;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public void addSite(SelectEvent event) {
		String site = (String) event.getObject();
		sites.add(site);
	}
}
