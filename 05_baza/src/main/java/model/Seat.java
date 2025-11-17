package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the seats database table.
 * 
 */
@Entity
@Table(name="seats")
@NamedQuery(name="Seat.findAll", query="SELECT s FROM Seat s")
public class Seat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="seat_id")
	private int seatId;

	@Column(name="row_label")
	private String rowLabel;

	@Column(name="seat_number")
	private int seatNumber;

	//bi-directional many-to-one association to ReservationSeat
	@OneToMany(mappedBy="seat")
	private List<ReservationSeat> reservationSeats;

	//bi-directional many-to-one association to CinemaHall
	@ManyToOne
	@JoinColumn(name="hall_id")
	private CinemaHall cinemaHall;

	public Seat() {
	}

	public int getSeatId() {
		return this.seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public String getRowLabel() {
		return this.rowLabel;
	}

	public void setRowLabel(String rowLabel) {
		this.rowLabel = rowLabel;
	}

	public int getSeatNumber() {
		return this.seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public List<ReservationSeat> getReservationSeats() {
		return this.reservationSeats;
	}

	public void setReservationSeats(List<ReservationSeat> reservationSeats) {
		this.reservationSeats = reservationSeats;
	}

	public ReservationSeat addReservationSeat(ReservationSeat reservationSeat) {
		getReservationSeats().add(reservationSeat);
		reservationSeat.setSeat(this);

		return reservationSeat;
	}

	public ReservationSeat removeReservationSeat(ReservationSeat reservationSeat) {
		getReservationSeats().remove(reservationSeat);
		reservationSeat.setSeat(null);

		return reservationSeat;
	}

	public CinemaHall getCinemaHall() {
		return this.cinemaHall;
	}

	public void setCinemaHall(CinemaHall cinemaHall) {
		this.cinemaHall = cinemaHall;
	}

}