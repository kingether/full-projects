package cz.jiripinkas.example.chat.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
		@NamedQuery(name = Role.FIND_ALL, query = "select r from Role r"),
		@NamedQuery(name = Role.FIND_BY_NAME, query = "select r from Role r where r.name = :name"),
		@NamedQuery(name = Role.COUNT, query = "select count(r) from Role r"),
		@NamedQuery(name = Role.FIND_BY_USER_NAME, query = "select ur.role from UserRole ur where ur.user.name = :name") })
public class Role {

	public static final String FIND_ALL = "Role.findAll";

	public static final String FIND_BY_NAME = "Role.findByName";

	public static final String COUNT = "Role.count";

	public static final String FIND_BY_USER_NAME = "Role.findByUserName";

	@Id
	@GeneratedValue
	private int id;

	private String name;

	@OneToMany(mappedBy = "role")
	private List<UserRole> userRoles;

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

}
