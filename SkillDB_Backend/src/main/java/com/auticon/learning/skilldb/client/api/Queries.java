package com.auticon.learning.skilldb.client.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.auticon.learning.skilldb.client.SessionConfig;
import com.auticon.learning.skilldb.client.dtos.EmployeeDTO;
import com.auticon.learning.skilldb.client.dtos.SkillsDTO;
import com.auticon.learning.skilldb.entities.Employee;
import com.auticon.learning.skilldb.entities.Employee_;
import com.auticon.learning.skilldb.entities.Expertise_String;
import com.auticon.learning.skilldb.entities.Expertise_String_;
import com.auticon.learning.skilldb.entities.Role;
import com.auticon.learning.skilldb.entities.Role_;
import com.auticon.learning.skilldb.entities.Site;
import com.auticon.learning.skilldb.entities.Site_;
import com.auticon.learning.skilldb.entities.Skill;
import com.auticon.learning.skilldb.entities.Skill_;

public final class Queries {

	public static Employee findEmployeeByFullname(String firstname, String lastname) {
		Session session = SessionConfig.getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
		Root<Employee> root = criteria.from(Employee.class);

		criteria.where(builder.and(builder.like(root.get(Employee_.firstname), firstname)),
				builder.like(root.get(Employee_.lastname), lastname));
		Query<Employee> query = session.createQuery(criteria);

		return query.getSingleResult();
	}

	public static Site findSiteByName(String name) {
		CriteriaBuilder builder = SessionConfig.getSession().getCriteriaBuilder();
		CriteriaQuery<Site> criteriaQuery = builder.createQuery(Site.class);
		Root<Site> root = criteriaQuery.from(Site.class);

		criteriaQuery.where(builder.equal(root.get(Site_.sitename), name));
		Query<Site> query = SessionConfig.getSession().createQuery(criteriaQuery);

		return query.getSingleResult();
	}

	public static Role findRoleByName(String name) {
		CriteriaBuilder builder = SessionConfig.getSession().getCriteriaBuilder();
		CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);
		Root<Role> root = criteriaQuery.from(Role.class);

		criteriaQuery.where(builder.equal(root.get(Role_.rolename), name));
		Query<Role> query = SessionConfig.getSession().createQuery(criteriaQuery);

		return query.getSingleResult();
	}

	public static Skill findSkillByNameAndDescription(SkillsDTO skill) {
		CriteriaBuilder builder = SessionConfig.getSession().getCriteriaBuilder();
		CriteriaQuery<Skill> criteriaQuery = builder.createQuery(Skill.class);
		Root<Skill> root = criteriaQuery.from(Skill.class);

		criteriaQuery.where(builder.and(builder.equal(root.get(Skill_.skill), skill.getName()),
				builder.equal(root.get(Skill_.description), skill.getDescription())));
		Query<Skill> query = SessionConfig.getSession().createQuery(criteriaQuery);

		return query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public static List<EmployeeDTO> findAllEmployees() {
		List<EmployeeDTO> dtos = new ArrayList<EmployeeDTO>();
		List<Employee> employees = SessionConfig.getSession().createNamedQuery("Employee.findAll").getResultList();

		for (Employee singleEmployee : employees) {
			dtos.add(new EmployeeDTO(singleEmployee));
		}

		return dtos;
	}

	public static List<EmployeeDTO> findEmployeesBySite(String site) {

		Session session = SessionConfig.getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
		Root<Employee> root = criteriaQuery.from(Employee.class);

		criteriaQuery.where(builder.equal(root.get(Employee_.site).get(Site_.sitename), site));
		Query<Employee> query = session.createQuery(criteriaQuery);

		return wrapInDTO(query);
	}

	@SuppressWarnings("unchecked")
	public static Collection<SkillsDTO> findAllAvailableSkills() {
		Collection<SkillsDTO> dtos = new ArrayList<SkillsDTO>();
		Query<Skill> query = SessionConfig.getSession().createNamedQuery("Skill.findAll");

		Collection<Skill> skills = query.getResultList();

		for (Skill skill : skills) {

			// CleanUp Skilllist
			if (skill.getEmployees().isEmpty()) {
				skills.remove(skill);
				continue;
			}

			dtos.add(new SkillsDTO(skill));
		}

		return dtos;
	}

	public static Set<Skill> findSkillsByExpertise(short expertise) {
		return SessionConfig.getSession().find(Expertise_String.class, expertise).getSkills();
	}

	@SuppressWarnings("unchecked")
	public static List<Site> findAllSites() {
		return SessionConfig.getSession().createNamedQuery("Site.findAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	public static List<Role> findAllRoles() {
		return SessionConfig.getSession().createNamedQuery("Role.findAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	public static List<Expertise_String> findAllExpertises() {
		return SessionConfig.getSession().createNamedQuery("Expertise_String.findAll").getResultList();
	}

	public static Expertise_String getExpertiseString(short id) {
		return SessionConfig.getSession().find(Expertise_String.class, id);
	}

	public static List<EmployeeDTO> findEmployeesBySkill(String skillname) {

		Session session = SessionConfig.getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);

		Root<Employee> root = criteriaQuery.from(Employee.class);
		// TODO: Somehow, working with the Metamodel returns null, but plain old String
		// works...
		Join<Employee, Skill> skill = root.join("skills");

		criteriaQuery.where(builder.equal(skill.get(Skill_.skill), skillname));

		Query<Employee> query = session.createQuery(criteriaQuery);

		return wrapInDTO(query);
	}

	public static List<EmployeeDTO> findEmployeesBySkillAndExpertiseGtE(String skillname, short expertise) {

		Session session = SessionConfig.getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);

		Root<Employee> root = criteriaQuery.from(Employee.class);
		// TODO: Somehow, working with the Metamodel returns null, but plain old String
		// works...
		Join<Employee, Skill> skill = root.join("skills");
		Join<Skill, Expertise_String> expertiseJoin = skill.join("expertiseString");

		criteriaQuery.where(builder.and(builder.equal(skill.get(Skill_.skill), skillname),
				builder.greaterThanOrEqualTo(expertiseJoin.get(Expertise_String_.expertise), expertise)));
		Query<Employee> query = session.createQuery(criteriaQuery);

		return wrapInDTO(query);
	}

	private static List<EmployeeDTO> wrapInDTO(Query<Employee> query) {
		List<EmployeeDTO> dtos = new ArrayList<EmployeeDTO>();
		List<Employee> employees = query.getResultList();

		for (Employee singleEmployee : employees) {
			dtos.add(new EmployeeDTO(singleEmployee));
		}

		return dtos;
	}
}
