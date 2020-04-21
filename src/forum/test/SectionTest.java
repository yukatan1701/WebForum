package forum.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import forum.*;

public class SectionTest {
	private Forum forum = new Forum();

	@Test
	public void testFindById() {
		Section section = forum.sectionService.findById(1);
		Assert.assertEquals(section.getTitle(), "Музыка");
		section = forum.sectionService.findById(0);
		Assert.assertNull(section);
	}
	
	@Test
	public void testFindByTitle() {
		Section section = forum.sectionService.findByTitle("Музыка");
		Assert.assertNotNull(section);
		Assert.assertEquals(section.getTitle(), "Музыка");
		section = forum.sectionService.findByTitle("nullable");
		Assert.assertNull(section);
	}
	
	@Test(expectedExceptions = javax.persistence.PersistenceException.class)
	public void testAddNullSection() {
		Section section = new Section();
		forum.sectionService.addSection(section);
	}
	
	@Test(expectedExceptions = org.hibernate.exception.ConstraintViolationException.class)
	public void testAddExistingSection() {
		Section section = new Section("Игры");
		forum.sectionService.addSection(section);
	}
	
	@Test
	public void testAddNotNullSection() {
		Section section = new Section("Туризм");
		try {
			forum.sectionService.addSection(section);
		} catch (org.hibernate.exception.ConstraintViolationException ex) {
			Assert.fail(String.format("Failed to add section '%s': section already exists.", section.getTitle()));
		}
		Section sameSection = forum.sectionService.findByTitle("Туризм");
		Assert.assertEquals(sameSection.toString(), section.toString());
	}
	
	@Test(dependsOnMethods={"testAddNotNullSection"})
	public void testDeleteSection() {
		Section section = new Section();
		forum.sectionService.delete(section);
		Section testSection = forum.sectionService.findByTitle("Туризм");
		forum.sectionService.delete(testSection);
	}
	
	@Test
	public void testGetUsersBySections() {
		HashMap<Section, HashSet<User>> usersInSections = forum.sectionService.getUsersBySections();
		for (Section sec : usersInSections.keySet()) {
			String title = sec.getTitle();
			if (title.equals("Кухня")) {
				Assert.assertEquals(usersInSections.get(sec).size(), 0);
			} else if (title.equals("Книги")) {
				Assert.assertEquals(usersInSections.get(sec).size(), 0);
			} else if (title.equals("Игры")) {
				Assert.assertEquals(usersInSections.get(sec).size(), 3);
				Set<String> realLogins = new HashSet<>(0);
				usersInSections.get(sec).forEach(user -> realLogins.add(user.getLogin()));
				Set<String> expectedLogins = new HashSet<>(Arrays.asList("admin", "belarus", "finland"));
				Assert.assertEquals(realLogins, expectedLogins);
			} else if (title.equals("Музыка")) {
				Assert.assertEquals(usersInSections.get(sec).size(), 4);
				Set<String> realLogins = new HashSet<>(0);
				usersInSections.get(sec).forEach(user -> realLogins.add(user.getLogin()));
				Set<String> expectedLogins = new HashSet<>(Arrays.asList("admin", "vasya777", "moder", "medved"));
				Assert.assertEquals(realLogins, expectedLogins);
			} else if (title.equals("Фильмы")) {
				Assert.assertEquals(usersInSections.get(sec).size(), 0);
			}
		}
	}
}
