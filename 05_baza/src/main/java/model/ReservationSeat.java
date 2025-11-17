package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the reservation_seats database table.
 * 
 */
@Entity
@Table(name="reservation_seats")
@NamedQuery(name="ReservationSeat.findAll", query="SELECT r FROM ReservationSeat r")
public class ReservationSeat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reservation_seat_id")
	private int reservationSeatId;

	private BigDecimal price;

	//bi-directional many-to-one association to Reservation
	@ManyToOne
	@JoinColumn(name="reservation_id")
	private Reservation reservation;

	//bi-directional many-to-one association to Screening
	@ManyToOne
	@JoinColumn(name="screening_id")
	private Screening screening;

	//bi-directional many-to-one association to Seat
	@ManyToOne
	@JoinColumn(name="seat_id")
	private Seat seat;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="reservationSeat")
	private List<Ticket> tickets;

	public ReservationSeat() {
	}

	public int getReservationSeatId() {
		return this.reservationSeatId;
	}

	public void setReservationSeatId(int reservationSeatId) {
		this.reservationSeatId = reservationSeatId;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Reservation getReservation() {
		return this.reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Screening getScreening() {
		return this.screening;
	}

	public void setScreening(Screening screening) {
		this.screening = screening;
	}

	public Seat getSeat() {
		return this.seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setReservationSeat(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setReservationSeat(null);

		return ticket;
	}

}