package de.auticon.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.auticon.learning.skilldb.client.api.Queries;
import com.auticon.learning.skilldb.entities.Expertise_String;

@Named(value = "commons")
@ViewScoped
public final class Commons implements Serializable {
	private static final long serialVersionUID = 1016599747084525284L;

	private List<String> expertises = new ArrayList<String>();

	@PostConstruct
	public void init() {
		List<Expertise_String> expertiseObjects = Queries.findAllExpertises();

		for (Expertise_String singleExpertise : expertiseObjects) {
			expertises.add(singleExpertise.getExpertise() + " - " + singleExpertise.getFullNameString());
		}
	}

	public List<String> getExpertises() {
		return expertises;
	}

	public void setExpertises(List<String> expertises) {
		this.expertises = expertises;
	}
}
