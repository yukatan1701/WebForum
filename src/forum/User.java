package forum;
// Generated 26.02.2020 21:11:41 by Hibernate Tools 5.4.7.Final

import forum.enums.*;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

public class User implements java.io.Serializable {
	private static final long serialVersionUID = -1181755035581891610L;
	private int userId;
	private String login;
	private byte[] password;
	private Date dateOfRegistration;
	@Convert(converter = PermissionsAttributeConverter.class)
	private Permissions permissions;
	@Convert(converter = StatusAttributeConverter.class)
	private Status status;
	@SuppressWarnings("rawtypes")
	private Set posts = new HashSet(0);

	public User() {
	}

	public User(int userId, String login, byte[] password, Date dateOfRegistration, Permissions permissions, Status status) {
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.dateOfRegistration = dateOfRegistration;
		this.permissions = permissions;
		this.status = status;
	}

	@SuppressWarnings("rawtypes")
	public User(int userId, String login, byte[] password, Date dateOfRegistration, Permissions permissions, Status status,
			Set posts) {
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.dateOfRegistration = dateOfRegistration;
		this.permissions = permissions;
		this.status = status;
		this.posts = posts;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public byte[] getPassword() {
		return this.password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public Date getDateOfRegistration() {
		return this.dateOfRegistration;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	public Permissions getPermissions() {
		return this.permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@SuppressWarnings("rawtypes")
	public Set getPosts() {
		return this.posts;
	}

	@SuppressWarnings("rawtypes")
	public void setPosts(Set posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", login=" + login + ", password=" + Arrays.toString(password)
				+ ", dateOfRegistration=" + dateOfRegistration + ", permissions=" + permissions + ", status=" + status
				+ ", posts=" + posts + "]";
	}

}
