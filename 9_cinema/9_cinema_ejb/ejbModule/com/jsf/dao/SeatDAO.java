package com.jsf.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import com.jsf.entities.Seat;

@Stateless
public class SeatDAO {

    private static final String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;

    public void create(Seat seat) {
        em.persist(seat);
    }

    public Seat merge(Seat seat) {
        return em.merge(seat);
    }

    public void remove(Seat seat) {
        em.remove(em.merge(seat));
    }

    public Seat find(Object id) {
        return em.find(Seat.class, id);
    }

    public List<Seat> getFullList() {
		List<Seat> list = null;

		Query query = em.createQuery("select se from Seat se");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}