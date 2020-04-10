package forum.test;

import forum.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TopicTest {
	
	private Forum forum = new Forum();
	/*
	@Test
	public void testFindById() {
		Topic topic = forum.topicService.findById(1);
		Assert.assertEquals(topic.getTitle(), "Рок, метал и смежное");
		topic = forum.topicService.findById(0);
		Assert.assertNull(topic);
	}
	
	
	@Test(expectedExceptions = RuntimeException.class)
	public void testAddTopicInNullSection() {
		Topic topic = new Topic(null, "Some topic.");
		forum.topicService.addTopic(topic);
	}
	
	@Test(expectedExceptions = javax.persistence.PersistenceException.class)
	public void testAddTopicInEmptySection() {
		Section section = new Section();
		Topic topic = new Topic(section, "Topic.");
		forum.topicService.addTopic(topic);
	}
	
	@Test
	public void testIfTopicInSection() {
		Section music = forum.sectionService.findByTitle("Музыка");
		Topic topic = new Topic(music, "Фантастика");
		Assert.assertEquals(forum.topicService.ifTopicInSection(music, topic), false);
		Section books = forum.sectionService.findByTitle("Книги");
		topic.setSection(books);
		Assert.assertEquals(forum.topicService.ifTopicInSection(books, topic), true);
	}
	
	@Test(expectedExceptions = RuntimeException.class)
	public void testAddExistingTopic() {
		Section section = forum.sectionService.findByTitle("Музыка");
		Assert.assertNotNull(section);
		Topic topic = new Topic(section, "Отечественный шансон");
		topic.setSection(section);
		forum.topicService.addTopic(topic);
	}
	
	@Test(expectedExceptions = NullPointerException.class)
	void testFindByTitleNull() {
		@SuppressWarnings("unused")
		Topic topic = forum.topicService.findByTitle(null, "Отечественный шансон");
	}
	
	@Test
	void testFindByTitle() {
		Section section = forum.sectionService.findByTitle("Фильмы");
		Topic topic = forum.topicService.findByTitle(section, "Зарубежное кино");
		Assert.assertNotNull(topic);
		Assert.assertEquals(topic.getTitle(), "Зарубежное кино");
	}
	
	@Test
	public void testAddTopic() {
		Section section = forum.sectionService.findByTitle("Фильмы");
		Topic topic = new Topic(section, "Финское кино");
		try {
			forum.topicService.addTopic(topic);
		} catch (RuntimeException ex) {
			Assert.fail(String.format("Failed to add topic '%s': topic already exists.", topic.getTitle()));
		}
		Topic sameTopic = forum.topicService.findByTitle(section, "Финское кино");
		Assert.assertEquals(topic.toString(), sameTopic.toString());
	}
	
	@Test(dependsOnMethods={"testAddTopic"})
	public void testDeleteTopic() {
		Topic topic = new Topic();
		forum.topicService.delete(topic);
		Section section = forum.sectionService.findByTitle("Фильмы");
		Topic testTopic = forum.topicService.findByTitle(section, "Финское кино");
		forum.topicService.delete(testTopic);
	}*/
}
