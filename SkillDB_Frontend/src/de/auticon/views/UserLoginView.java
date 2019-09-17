package de.auticon.views;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.auticon.learning.authentication.api.Login;

@Named(value = "loginView")
@ViewScoped
public class UserLoginView implements Serializable {

	private static final long serialVersionUID = 2114839885759223856L;

	private static String menuFilename = "MainMenu.xhtml";

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		FacesMessage message = null;

		if (!Login.checkPW(username, password)) {
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
			FacesContext.getCurrentInstance().addMessage(null, message);

			return "";
		} else {
			return menuFilename + "?faces-redirect=true";
		}
	}
}