package com.auticon.learning.skilldb.client.api;

import org.hibernate.Session;

import com.auticon.learning.skilldb.client.SessionConfig;
import com.auticon.learning.skilldb.client.dtos.EmployeeDTO;
import com.auticon.learning.skilldb.client.dtos.SkillsDTO;
import com.auticon.learning.skilldb.entities.Employee;
import com.auticon.learning.skilldb.entities.Role;
import com.auticon.learning.skilldb.entities.Site;
import com.auticon.learning.skilldb.entities.Skill;

public final class DBInserter {

	private static Session session;

	public DBInserter() {
		session = SessionConfig.getSession();
	}

	public void submitEmployee(EmployeeDTO dto) {
		Employee employee = dto.toEmployee();

		session.beginTransaction();
		session.saveOrUpdate(employee);
		session.getTransaction().commit();
	}

	public Site insertSite(String sitename) {
		Site site = new Site();
		site.setSitename(sitename);

		session.beginTransaction();
		session.save(site);
		session.getTransaction().commit();

		return site;
	}

	public Role insertRole(String rolename) {
		Role role = new Role();
		role.setRolename(rolename);

		session.beginTransaction();
		session.save(role);
		session.getTransaction().commit();

		return role;
	}

	public Skill insertSkill(SkillsDTO skillDto) {
		Skill skill = new Skill();
		skill.setSkill(skillDto.getName());
		skill.setDescription(skillDto.getDescription());
		skill.setExpertiseString(Queries.getExpertiseString(new Integer(skillDto.getExpertise()).shortValue()));

		session.beginTransaction();
		session.save(skill);
		session.getTransaction().commit();

		return skill;
	}
}
