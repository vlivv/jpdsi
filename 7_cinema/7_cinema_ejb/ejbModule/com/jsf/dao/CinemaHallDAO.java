package com.jsf.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import com.jsf.entities.CinemaHall;

public class CinemaHallDAO {
	 private static final String UNIT_NAME = "jsfcourse-simplePU";

	    @PersistenceContext(unitName = UNIT_NAME)
	    private EntityManager em;

	    public void create(CinemaHall cinemaHall) {
	        em.persist(cinemaHall);
	    }

	    public CinemaHall merge(CinemaHall cinemaHall) {
	        return em.merge(cinemaHall);
	    }

	    public void remove(CinemaHall cinemaHall) {
	        em.remove(em.merge(cinemaHall));
	    }

	    public CinemaHall find(Object id) {
	        return em.find(CinemaHall.class, id);
	    }

		public List<CinemaHall> getFullList() {
			List<CinemaHall> list = null;

			Query query = em.createQuery("select c from CinemaHall c");

			try {
				list = query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return list;
		}

}
