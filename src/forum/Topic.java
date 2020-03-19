package forum;
// Generated 26.02.2020 21:11:41 by Hibernate Tools 5.4.7.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Topic generated by hbm2java
 */
public class Topic implements java.io.Serializable {

	private int topicId;
	private Section section;
	private String title;
	private Set posts = new HashSet(0);

	public Topic() {
	}

	public Topic(int topicId, Section section, String title) {
		this.topicId = topicId;
		this.section = section;
		this.title = title;
	}

	public Topic(int topicId, Section section, String title, Set posts) {
		this.topicId = topicId;
		this.section = section;
		this.title = title;
		this.posts = posts;
	}

	public int getTopicId() {
		return this.topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set getPosts() {
		return this.posts;
	}

	public void setPosts(Set posts) {
		this.posts = posts;
	}

}
