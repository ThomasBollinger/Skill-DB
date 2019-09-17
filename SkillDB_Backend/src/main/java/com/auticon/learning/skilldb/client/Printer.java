package com.auticon.learning.skilldb.client;

import java.text.DateFormat;
import java.util.Collection;
import java.util.Iterator;

import com.auticon.learning.skilldb.entities.Employee;
import com.auticon.learning.skilldb.entities.Role;
import com.auticon.learning.skilldb.entities.Skill;

/**
 * Only for testing. Remove Class before going productiv
 * 
 * @author Lap98
 *
 */
public final class Printer {

	public static void printFullEmployee(Employee employee) {

		DateFormat dateFormat = DateFormat.getDateInstance();

		System.out.println("Full name: " + employee.getFirstname() + " " + employee.getLastname());

		if (!employee.getEmail().isEmpty()) {
			System.out.println("E-Mail: " + employee.getEmail());
		}

		System.out.println("Works on site: " + employee.getSite().getSitename());

		if (employee.getEntrydate() != null) {
			System.out.println("Began to work on: " + dateFormat.format(employee.getEntrydate()));
		}

		System.out.print("Has role(s): ");
		for (Iterator<Role> iterator = employee.getRoles().iterator(); iterator.hasNext();) {
			Role role = iterator.next();

			System.out.print(role.getRolename());
			if (iterator.hasNext()) {
				System.out.print(", ");
			}
		}
		System.out.print("\n");

		System.out.println("Has following skill(s) and expertise in it: ");
		printSkilllist(employee.getSkills(), false);
	}

	public static void printSkilllist(Collection<Skill> skills, boolean withEmployees) {
		for (Iterator<Skill> iterator = skills.iterator(); iterator.hasNext();) {
			Skill skill = iterator.next();

			System.out.print(skill.getSkill() + " : " + skill.getExpertiseString().getExpertise());
			if (skill.getDescription() != null && !skill.getDescription().isEmpty()) {
				System.out.print(", described: " + skill.getDescription());
			}

			if (withEmployees) {
				System.out.print("\nEmployees with this skill: ");
				for (Employee employee : skill.getEmployees()) {
					System.out.print(employee.getLastname() + ", ");
				}
			}

			System.out.print("\n");
		}
	}
}
