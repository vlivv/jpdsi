package com.jsf.dao;

import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import com.jsf.entities.Movie;


@Stateless
public class MovieDAO {

    private static final String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;

    public void create(Movie movie) {
        em.persist(movie);
    }

    public Movie merge(Movie movie) {
        return em.merge(movie);
    }

    public void remove(Movie movie) {
        em.remove(em.merge(movie));
    }

    public Movie find(Object id) {
        return em.find(Movie.class, id);
    }

	public List<Movie> getFullList() {
		List<Movie> list = null;

		Query query = em.createQuery("select m from Movie m");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
