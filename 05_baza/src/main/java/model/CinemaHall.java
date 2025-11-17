package model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/**
 * The persistent class for the cinema_halls database table.
 * 
 */
@Entity
@Table(name="cinema_halls")
@NamedQuery(name="CinemaHall.findAll", query="SELECT c FROM CinemaHall c")
public class CinemaHall implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="hall_id")
	private int hallId;

	@Column(name="is_active")
	private byte isActive;

	private String name;

	@Column(name="total_seats")
	private int totalSeats;

	//bi-directional many-to-one association to Screening
	@OneToMany(mappedBy="cinemaHall")
	private List<Screening> screenings;

	//bi-directional many-to-one association to Seat
	@OneToMany(mappedBy="cinemaHall")
	private List<Seat> seats;

	public CinemaHall() {
	}

	public int getHallId() {
		return this.hallId;
	}

	public void setHallId(int hallId) {
		this.hallId = hallId;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalSeats() {
		return this.totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public List<Screening> getScreenings() {
		return this.screenings;
	}

	public void setScreenings(List<Screening> screenings) {
		this.screenings = screenings;
	}

	public Screening addScreening(Screening screening) {
		getScreenings().add(screening);
		screening.setCinemaHall(this);

		return screening;
	}

	public Screening removeScreening(Screening screening) {
		getScreenings().remove(screening);
		screening.setCinemaHall(null);

		return screening;
	}

	public List<Seat> getSeats() {
		return this.seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Seat addSeat(Seat seat) {
		getSeats().add(seat);
		seat.setCinemaHall(this);

		return seat;
	}

	public Seat removeSeat(Seat seat) {
		getSeats().remove(seat);
		seat.setCinemaHall(null);

		return seat;
	}

}