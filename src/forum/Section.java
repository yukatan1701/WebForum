package forum;
// Generated 26.02.2020 21:11:41 by Hibernate Tools 5.4.7.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Section generated by hbm2java
 */
public class Section implements java.io.Serializable {
	private static final long serialVersionUID = 7521761184125173059L;
	private int sectionId;
	private String title;
	@SuppressWarnings("rawtypes")
	private Set topics = new HashSet(0);

	public Section() {
	}
	
	public Section(String title) {
		this.title = title;
	}

	public Section(int sectionId, String title) {
		this.sectionId = sectionId;
		this.title = title;
	}

	@SuppressWarnings("rawtypes")
	public Section(int sectionId, String title, Set topics) {
		this.sectionId = sectionId;
		this.title = title;
		this.topics = topics;
	}

	public int getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@SuppressWarnings("rawtypes")
	public Set getTopics() {
		return this.topics;
	}

	@SuppressWarnings("rawtypes")
	public void setTopics(Set topics) {
		this.topics = topics;
	}

	@Override
	public String toString() {
		return "Section [sectionId=" + sectionId + ", title=" + title + ", topics=" + topics + "]";
	}

}
