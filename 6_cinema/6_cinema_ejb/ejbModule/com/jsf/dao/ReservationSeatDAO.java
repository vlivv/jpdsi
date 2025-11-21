package com.jsf.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import com.jsf.entities.ReservationSeat;

@Stateless
public class ReservationSeatDAO {

    private static final String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;

    public void create(ReservationSeat reservationSeat) {
        em.persist(reservationSeat);
    }

    public ReservationSeat merge(ReservationSeat reservationSeat) {
        return em.merge(reservationSeat);
    }

    public void remove(ReservationSeat reservationSeat) {
        em.remove(em.merge(reservationSeat));
    }

    public ReservationSeat find(Object id) {
        return em.find(ReservationSeat.class, id);
    }
   
    public List<ReservationSeat> getFullList() {
		List<ReservationSeat> list = null;

		Query query = em.createQuery("select rs from ReservationSeat rs");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
