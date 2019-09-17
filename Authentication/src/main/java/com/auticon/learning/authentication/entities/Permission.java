package com.auticon.learning.authentication.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the "Permissions" database table.
 * 
 */
@Entity
@Table(name="\"Permissions\"")
@NamedQuery(name="Permission.findAll", query="SELECT p FROM Permission p")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Integer id;

	private String action;

	private String description;

	//bi-directional many-to-many association to Role
	@ManyToMany(mappedBy="permissions", fetch=FetchType.EAGER)
	private Set<Role> roles;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="permissions", fetch=FetchType.EAGER)
	private Set<User> users;

	public Permission() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}