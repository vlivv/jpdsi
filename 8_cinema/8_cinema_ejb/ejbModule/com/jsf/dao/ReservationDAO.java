package com.jsf.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import com.jsf.entities.Reservation;

@Stateless
public class ReservationDAO {

    private static final String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;

    public void create(Reservation reservation) {
        em.persist(reservation);
    }

    public Reservation merge(Reservation reservation) {
        return em.merge(reservation);
    }

    public void remove(Reservation reservation) {
        em.remove(em.merge(reservation));
    }

    public Reservation find(Object id) {
        return em.find(Reservation.class, id);
    }

	public List<Reservation> getFullList() {
		List<Reservation> list = null;

		Query query = em.createQuery("select r from Reservation r");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
