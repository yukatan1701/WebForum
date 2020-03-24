package forum;

import org.testng.asserts.*;

import forum.enums.Permissions;
import forum.enums.Status;

import org.testng.annotations.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;

import org.testng.Assert;

public class UserTest {
	private Forum forum = new Forum();
	
	@Test
	public void testFindById() {
		User user = forum.userService.findById(1);
		Assert.assertEquals(user.getLogin(), "admin");
		user = forum.userService.findById(0);
		Assert.assertNull(user);
	}
	
	@Test
	public void testFindByLogin() {
		User user = forum.userService.findByLogin("medved");
		Assert.assertNotNull(user);
		Assert.assertEquals(user.getLogin(), "medved");
		user = forum.userService.findByLogin("nullable");
		Assert.assertNull(user);
	}
	
	@Test(expectedExceptions = javax.persistence.PersistenceException.class)
	public void testAddNullUser() {
		User user = new User();
		forum.userService.addUser(user);
	}
	
	@Test(expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
	public void testAddExistingUser() {
		User user = new User("finland", User.getSHA512("helsinki"), Date.valueOf("2017-04-24"), Permissions.USER, Status.NORMAL);
		forum.userService.addUser(user);
	}
	
	@Test
	public void testAddNotNullUser() {
		User user = new User("testUser", "t".getBytes(), new Date(System.currentTimeMillis()), Permissions.USER, Status.NORMAL);
		try {
			forum.userService.addUser(user);
		} catch (org.hibernate.exception.ConstraintViolationException ex) {
			Assert.fail(String.format("Failed to add user '%s': user already exists.", user.getLogin()));
		}
		User sameUser = forum.userService.findByLogin("testUser");
		Assert.assertEquals(user.toString(), sameUser.toString());
	}
	
	@Test
	public void testDeleteUser() {
		User user = new User();
		forum.userService.delete(user);
		User testUser = forum.userService.findByLogin("testUser");
		forum.userService.delete(testUser);
	}
	
	@Test
	public void testGetActiveUsers() {
		Timestamp begin = Timestamp.valueOf("2015-01-01 00:00:00.0"), end = Timestamp.valueOf("2016-01-01 00:00:00.0");
		LinkedHashMap<User, Integer> activeUsers = forum.userService.getActiveUsers(begin, end);
		java.util.List<User> realUsers = forum.userService.findAll();
		Assert.assertEquals(realUsers.size(), activeUsers.size());
		
		activeUsers = forum.userService.getActiveUsers(end, begin);
		Assert.assertEquals(activeUsers.size(), 0);
		
		Timestamp oneDayBegin = Timestamp.valueOf("2015-05-05 00:00:00.0");
		Timestamp oneDayEnd = Timestamp.valueOf("2015-05-06 00:00:00.0");
		activeUsers = forum.userService.getActiveUsers(oneDayBegin, oneDayEnd);
		Assert.assertEquals(activeUsers.size(), 4);
		
		Timestamp mayBegin = Timestamp.valueOf("2015-05-01 00:00:00.0");
		Timestamp mayEnd = Timestamp.valueOf("2015-06-01 00:00:00.0");
		activeUsers = forum.userService.getActiveUsers(mayBegin, mayEnd);
		Assert.assertEquals(activeUsers.size(), 5);
	}
	
	@Test
	public void testSHA512() {
		User user = forum.userService.findByLogin("finland");
		Assert.assertEquals(User.getSHA512("helsinki"), user.getPassword());
	}
	
	@Test
	public void testBlockUnblockUser() {
		User user = forum.userService.findByLogin("medved");
		Assert.assertEquals(user.getStatus(), Status.NORMAL);
		forum.userService.blockUser(user);
		user = forum.userService.findByLogin("medved");
		Assert.assertEquals(user.getStatus(), Status.BLOCKED);
		forum.userService.unblockUser(user);
		user = forum.userService.findByLogin("medved");
		Assert.assertEquals(user.getStatus(), Status.NORMAL);
	}
}
