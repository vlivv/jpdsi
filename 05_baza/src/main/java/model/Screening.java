package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the screenings database table.
 * 
 */
@Entity
@Table(name="screenings")
@NamedQuery(name="Screening.findAll", query="SELECT s FROM Screening s")
public class Screening implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="screening_id")
	private int screeningId;

	@Column(name="base_price")
	private BigDecimal basePrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time")
	private Date startTime;

	//bi-directional many-to-one association to ReservationSeat
	@OneToMany(mappedBy="screening")
	private List<ReservationSeat> reservationSeats;

	//bi-directional many-to-one association to Reservation
	@OneToMany(mappedBy="screening")
	private List<Reservation> reservations;

	//bi-directional many-to-one association to CinemaHall
	@ManyToOne
	@JoinColumn(name="hall_id")
	private CinemaHall cinemaHall;

	//bi-directional many-to-one association to Movy
	@ManyToOne
	@JoinColumn(name="movie_id")
	private Movy movy;

	public Screening() {
	}

	public int getScreeningId() {
		return this.screeningId;
	}

	public void setScreeningId(int screeningId) {
		this.screeningId = screeningId;
	}

	public BigDecimal getBasePrice() {
		return this.basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public List<ReservationSeat> getReservationSeats() {
		return this.reservationSeats;
	}

	public void setReservationSeats(List<ReservationSeat> reservationSeats) {
		this.reservationSeats = reservationSeats;
	}

	public ReservationSeat addReservationSeat(ReservationSeat reservationSeat) {
		getReservationSeats().add(reservationSeat);
		reservationSeat.setScreening(this);

		return reservationSeat;
	}

	public ReservationSeat removeReservationSeat(ReservationSeat reservationSeat) {
		getReservationSeats().remove(reservationSeat);
		reservationSeat.setScreening(null);

		return reservationSeat;
	}

	public List<Reservation> getReservations() {
		return this.reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Reservation addReservation(Reservation reservation) {
		getReservations().add(reservation);
		reservation.setScreening(this);

		return reservation;
	}

	public Reservation removeReservation(Reservation reservation) {
		getReservations().remove(reservation);
		reservation.setScreening(null);

		return reservation;
	}

	public CinemaHall getCinemaHall() {
		return this.cinemaHall;
	}

	public void setCinemaHall(CinemaHall cinemaHall) {
		this.cinemaHall = cinemaHall;
	}

	public Movy getMovy() {
		return this.movy;
	}

	public void setMovy(Movy movy) {
		this.movy = movy;
	}

}