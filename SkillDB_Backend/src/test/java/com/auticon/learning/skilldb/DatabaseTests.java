package com.auticon.learning.skilldb;

import org.junit.Test;

import com.auticon.learning.skilldb.client.Printer;
import com.auticon.learning.skilldb.client.api.Queries;

/**
 * Do REAL Testcases
 * 
 * @author Lap98
 *
 */
public class DatabaseTests {

	@Test
	public void testFullEmployee() {
		Printer.printFullEmployee(Queries.findEmployeeByFullname("Thomas", "Bollinger"));
	}

	@Test
	public void findAllSkillsWithExpertise() {
		Printer.printSkilllist(Queries.findSkillsByExpertise((short) 2), true);
	}
}
