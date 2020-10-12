package cz.jiripinkas.example.chat.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
		@NamedQuery(name = User.FIND_ALL, query = "select distinct u from User u left join fetch u.userRoles ur left join fetch ur.role"),
		// @NamedQuery(name = User.FIND_ALL, query = "select u from User u"),
		@NamedQuery(name = User.FIND_BY_NAME, query = "select u from User u where u.name = :name"),
		@NamedQuery(name = User.COUNT, query = "select count(u) from User u"),
		@NamedQuery(name = User.FIND_BY_ROLE_NAME, query = "select ur.user from UserRole ur where ur.role.name = :name") })
public class User {

	public static final String FIND_ALL = "User.findAll";

	public static final String FIND_BY_NAME = "User.findByName";

	public static final String COUNT = "User.count";

	public static final String FIND_BY_ROLE_NAME = "User.findByRoleName";

	@Id
	@GeneratedValue
	private int id;

	@Size(min = 1, message = "Name must not be empty!")
	private String name;

	@Size(min = 1, message = "Password must not be empty!")
	private String password;

	private boolean enabled;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<UserRole> userRoles;

	@OneToMany(mappedBy = "user")
	private List<Chatmessage> chatmessages;

	public void setChatmessages(List<Chatmessage> chatmessages) {
		this.chatmessages = chatmessages;
	}

	public List<Chatmessage> getChatmessages() {
		return chatmessages;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
