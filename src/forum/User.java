package forum;
// Generated 26.02.2020 21:11:41 by Hibernate Tools 5.4.7.Final

import forum.enums.*;

import java.util.Arrays;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

public class User implements java.io.Serializable {
	private static final long serialVersionUID = -1181755035581891610L;
	private int userId;
	private String login;
	private String password;
	private Date dateOfRegistration;
	@Convert(converter = PermissionsAttributeConverter.class)
	private Permissions permissions;
	@Convert(converter = StatusAttributeConverter.class)
	private Status status;
	@SuppressWarnings("rawtypes")
	private Set posts = new HashSet(0);

	public User() {
	}
	
	public User(String login, String password, Date dateOfRegistration, Permissions permissions, Status status) {
		this.login = login;
		this.password = password;
		this.dateOfRegistration = dateOfRegistration;
		this.permissions = permissions;
		this.status = status;
	}

	public User(int userId, String login, String password, Date dateOfRegistration, Permissions permissions, Status status) {
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.dateOfRegistration = dateOfRegistration;
		this.permissions = permissions;
		this.status = status;
	}

	@SuppressWarnings("rawtypes")
	public User(int userId, String login, String password, Date dateOfRegistration, Permissions permissions, Status status,
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
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
	
	public static String getMD5(String password) {
		
		String result = password;
		if (password != null) {
			MessageDigest mda;
			try {
				mda = MessageDigest.getInstance("MD5");
				mda.update(password.getBytes());
			    BigInteger hash = new BigInteger(1, mda.digest());
			    result = hash.toString(16);
			    while (result.length() < 32) { // 40 for SHA-1
			        result = "0" + result;
			    }
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return null;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", login=" + login + ", password=" + password
				+ ", dateOfRegistration=" + dateOfRegistration + ", permissions=" + permissions + ", status=" + status
				+ "]";
	}

}
