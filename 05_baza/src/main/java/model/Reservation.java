package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the reservations database table.
 * 
 */
@Entity
@Table(name="reservations")
@NamedQuery(name="Reservation.findAll", query="SELECT r FROM Reservation r")
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reservation_id")
	private int reservationId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="reservation_time")
	private Date reservationTime;

	private String status;

	@Column(name="total_amount")
	private BigDecimal totalAmount;

	//bi-directional many-to-one association to ReservationSeat
	@OneToMany(mappedBy="reservation")
	private List<ReservationSeat> reservationSeats;

	//bi-directional many-to-one association to Screening
	@ManyToOne
	@JoinColumn(name="screening_id")
	private Screening screening;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user2;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="reservation")
	private List<Ticket> tickets;

	public Reservation() {
	}

	public int getReservationId() {
		return this.reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public Date getReservationTime() {
		return this.reservationTime;
	}

	public void setReservationTime(Date reservationTime) {
		this.reservationTime = reservationTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<ReservationSeat> getReservationSeats() {
		return this.reservationSeats;
	}

	public void setReservationSeats(List<ReservationSeat> reservationSeats) {
		this.reservationSeats = reservationSeats;
	}

	public ReservationSeat addReservationSeat(ReservationSeat reservationSeat) {
		getReservationSeats().add(reservationSeat);
		reservationSeat.setReservation(this);

		return reservationSeat;
	}

	public ReservationSeat removeReservationSeat(ReservationSeat reservationSeat) {
		getReservationSeats().remove(reservationSeat);
		reservationSeat.setReservation(null);

		return reservationSeat;
	}

	public Screening getScreening() {
		return this.screening;
	}

	public void setScreening(Screening screening) {
		this.screening = screening;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setReservation(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setReservation(null);

		return ticket;
	}

}