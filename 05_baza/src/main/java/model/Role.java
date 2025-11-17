package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private int roleId;

	private String name;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="role1")
	private List<UserRole> userRoles1;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="role2")
	private List<UserRole> userRoles2;

	public Role() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserRole> getUserRoles1() {
		return this.userRoles1;
	}

	public void setUserRoles1(List<UserRole> userRoles1) {
		this.userRoles1 = userRoles1;
	}

	public UserRole addUserRoles1(UserRole userRoles1) {
		getUserRoles1().add(userRoles1);
		userRoles1.setRole1(this);

		return userRoles1;
	}

	public UserRole removeUserRoles1(UserRole userRoles1) {
		getUserRoles1().remove(userRoles1);
		userRoles1.setRole1(null);

		return userRoles1;
	}

	public List<UserRole> getUserRoles2() {
		return this.userRoles2;
	}

	public void setUserRoles2(List<UserRole> userRoles2) {
		this.userRoles2 = userRoles2;
	}

	public UserRole addUserRoles2(UserRole userRoles2) {
		getUserRoles2().add(userRoles2);
		userRoles2.setRole2(this);

		return userRoles2;
	}

	public UserRole removeUserRoles2(UserRole userRoles2) {
		getUserRoles2().remove(userRoles2);
		userRoles2.setRole2(null);

		return userRoles2;
	}

}