package com.auticon.learning.skilldb.client.dtos;

import javax.persistence.NoResultException;

import com.auticon.learning.skilldb.client.api.Queries;

public class DTOUtils {

	protected static boolean existSite(String sitename) {
		try {
			Queries.findSiteByName(sitename);
			return true;
		} catch (NoResultException exception) {
			return false;
		}
	}

	protected static boolean existRole(String rolename) {
		try {
			Queries.findRoleByName(rolename);
			return true;
		} catch (NoResultException exception) {
			return false;
		}
	}

	protected static boolean existSkill(SkillsDTO skill) {
		try {
			Queries.findSkillByNameAndDescription(skill);
			return true;
		} catch (NoResultException exception) {
			return false;
		}
	}
}
