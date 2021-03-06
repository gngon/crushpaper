/*
Copyright 2015 CrushPaper.com.

This file is part of CrushPaper.

CrushPaper is free software: you can redistribute it and/or modify
it under the terms of version 3 of the GNU Affero General Public
License as published by the Free Software Foundation.

CrushPaper is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with CrushPaper.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.crushpaper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/** Tests modifying an entry. */
public class DbModifyEntryTest extends DbLogicTestBase {
	// edit root
	@Test
	public void test1() {
		final TestEntrySet before = new TestEntrySet(new TestEntry[] { new TestEntry(
				"1") });

		final TestEntrySet expectedAfter = new TestEntrySet(
				new TestEntry[] { new TestEntry("2") });

		final Errors errors = new Errors();
		
		final User user = dbLogic.getOrCreateUser("user");
		assertTrue(dbLogic.addEntries(before, user, createTime,
				errors));

		final String nodeToEditId = before.getIdForValue("1");
		final Entry editedNode = dbLogic.editEntry(user,
				nodeToEditId, "2", null, false, 2L, false, errors);
		assertNotNull(editedNode);
		assertFalse(errors.hasErrors());
		final TestEntrySet actualAfter = finalAssertions(user, expectedAfter);
		final String editedNodeId = actualAfter.getIdForValue("2");
		assertEquals(editedNodeId, nodeToEditId);
		assertEquals(2L, editedNode.getModTime());
	}

	// edit non root
	@Test
	public void test2() {
		final TestEntrySet before = new TestEntrySet(new TestEntry[] { new TestEntry(
				"1", new TestEntry[] { new TestEntry("2") }) });

		final TestEntrySet expectedAfter = new TestEntrySet(
				new TestEntry[] { new TestEntry("1",
						new TestEntry[] { new TestEntry("3") }) });

		final Errors errors = new Errors();
		
		final User user = dbLogic.getOrCreateUser("user");
		assertTrue(dbLogic.addEntries(before, user, createTime,
				errors));

		final String nodeToEditId = before.getIdForValue("2");
		final Entry editedNode = dbLogic.editEntry(user,
				nodeToEditId, "3", "", false, 2L, false, errors);
		assertNotNull(editedNode);
		assertFalse(errors.hasErrors());
		final TestEntrySet actualAfter = finalAssertions(user, expectedAfter);
		final String editedNodeId = actualAfter.getIdForValue("3");
		assertEquals(editedNodeId, nodeToEditId);
		assertEquals(2L, editedNode.getModTime());
	}

	@Test
	public void test3() {
		final TestEntrySet before = new TestEntrySet(new TestEntry[] { new TestEntry(
				"1") });

		final TestEntrySet expectedAfter = before;

		final Errors errors = new Errors();
		
		final User user = dbLogic.getOrCreateUser("user");
		assertTrue(dbLogic.addEntries(before, user, createTime,
				errors));

		final String nodeToEditId = before.getIdForValue("2");
		assertNull(dbLogic.editEntry(null, nodeToEditId, "2", "",
				false, 2L, false, errors));
		assertTrue(errors.compare(errorMessages.errorUserIsNull()));
		finalAssertions(user, expectedAfter);
	}

	@Test
	public void test4() {
		final TestEntrySet before = new TestEntrySet(new TestEntry[] { new TestEntry(
				"1") });

		final TestEntrySet expectedAfter = before;

		final Errors errors = new Errors();
		
		final User user = dbLogic.getOrCreateUser("user");
		assertTrue(dbLogic.addEntries(before, user, createTime,
				errors));

		final String nodeToEditId = before.getIdForValue("2");
		assertNull(dbLogic.editEntry(user, nodeToEditId, "2", "",
				false, null, false, errors));
		assertTrue(errors.compare(errorMessages.errorModTimeIsNull()));
		finalAssertions(user, expectedAfter);
	}

	@Test
	public void test5() {
		final TestEntrySet before = new TestEntrySet(new TestEntry[] { new TestEntry(
				"1") });

		final TestEntrySet expectedAfter = before;

		final Errors errors = new Errors();
		
		final User user = dbLogic.getOrCreateUser("user");
		assertTrue(dbLogic.addEntries(before, user, createTime,
				errors));

		final String nodeToEditId = before.getIdForValue("2");
		assertNull(dbLogic
				.editEntry(
						user,
						"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
						"2", "", false, 2L, false, errors));
		assertTrue(errors.compare(errorMessages.errorIdIsInvalid()));
		finalAssertions(user, expectedAfter);
	}

	@Test
	public void test6() {
		final TestEntrySet before = new TestEntrySet(new TestEntry[] { new TestEntry(
				"1") });

		final TestEntrySet expectedAfter = before;

		final Errors errors = new Errors();
		
		final User user = dbLogic.getOrCreateUser("user");
		assertTrue(dbLogic.addEntries(before, user, createTime,
				errors));

		final String nodeToEditId = before.getIdForValue("2");
		assertNull(dbLogic.editEntry(user, dbLogic
				.getIdGenerator().getAnotherId(), "2", "", false, 2L,
				false, errors));
		assertTrue(errors.compare(errorMessages
				.errorTheEntryCouldNotBeFound()));
		finalAssertions(user, expectedAfter);
	}
}