package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private String email;

	private String login;

	private String password;

	//bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy="user1")
	private List<Reservation> reservations1;

	//bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy="user2")
	private List<Reservation> reservations2;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user1")
	private List<UserRole> userRoles1;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user2")
	private List<UserRole> userRoles2;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<Reservation> getReservations1() {
		return this.reservations1;
	}

	public void setReservations1(List<Reservation> reservations1) {
		this.reservations1 = reservations1;
	}

	public Reservation addReservations1(Reservation reservations1) {
		getReservations1().add(reservations1);
		reservations1.setUser1(this);

		return reservations1;
	}

	public Reservation removeReservations1(Reservation reservations1) {
		getReservations1().remove(reservations1);
		reservations1.setUser1(null);

		return reservations1;
	}

	public List<Reservation> getReservations2() {
		return this.reservations2;
	}

	public void setReservations2(List<Reservation> reservations2) {
		this.reservations2 = reservations2;
	}

	public Reservation addReservations2(Reservation reservations2) {
		getReservations2().add(reservations2);
		reservations2.setUser2(this);

		return reservations2;
	}

	public Reservation removeReservations2(Reservation reservations2) {
		getReservations2().remove(reservations2);
		reservations2.setUser2(null);

		return reservations2;
	}

	public List<UserRole> getUserRoles1() {
		return this.userRoles1;
	}

	public void setUserRoles1(List<UserRole> userRoles1) {
		this.userRoles1 = userRoles1;
	}

	public UserRole addUserRoles1(UserRole userRoles1) {
		getUserRoles1().add(userRoles1);
		userRoles1.setUser1(this);

		return userRoles1;
	}

	public UserRole removeUserRoles1(UserRole userRoles1) {
		getUserRoles1().remove(userRoles1);
		userRoles1.setUser1(null);

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
		userRoles2.setUser2(this);

		return userRoles2;
	}

	public UserRole removeUserRoles2(UserRole userRoles2) {
		getUserRoles2().remove(userRoles2);
		userRoles2.setUser2(null);

		return userRoles2;
	}

}