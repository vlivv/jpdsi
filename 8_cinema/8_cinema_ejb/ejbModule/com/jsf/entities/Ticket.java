package com.jsf.entities;
import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tickets database table.
 * 
 */
@Entity
@Table(name="tickets")
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ticket_id")
	private int ticketId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="issued_at")
	private Date issuedAt;

	@Column(name="ticket_code")
	private String ticketCode;

	//bi-directional many-to-one association to ReservationSeat
	@ManyToOne
	@JoinColumn(name="reservation_seat_id")
	private ReservationSeat reservationSeat;

	//bi-directional many-to-one association to Reservation
	@ManyToOne
	@JoinColumn(name="reservation_id")
	private Reservation reservation;

	public Ticket() {
	}

	public int getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public Date getIssuedAt() {
		return this.issuedAt;
	}

	public void setIssuedAt(Date issuedAt) {
		this.issuedAt = issuedAt;
	}

	public String getTicketCode() {
		return this.ticketCode;
	}

	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}

	public ReservationSeat getReservationSeat() {
		return this.reservationSeat;
	}

	public void setReservationSeat(ReservationSeat reservationSeat) {
		this.reservationSeat = reservationSeat;
	}

	public Reservation getReservation() {
		return this.reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

}