package com.jsf.dao;

import java.util.List;
import java.util.Map;

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
	
	public List<Movie> getList(Map<String, Object> searchParams) {
	    List<Movie> list = null;

	    String select = "select m ";
	    String from = "from Movie m ";
	    String where = "";
	    String orderby = "order by m.title asc";

	    String title = (String) searchParams.get("title");
	    if (title != null && !title.isEmpty()) {
	        where += (where.isEmpty() ? "where " : "and ");
	        where += "m.title like :title ";
	    }

	    String genre = (String) searchParams.get("genre");
	    if (genre != null && !genre.isEmpty()) {
	        where += (where.isEmpty() ? "where " : "and ");
	        where += "m.genre like :genre ";
	    }

	    String director = (String) searchParams.get("director");
	    if (director != null && !director.isEmpty()) {
	        where += (where.isEmpty() ? "where " : "and ");
	        where += "m.director like :director ";
	    }

	    Query query = em.createQuery(select + from + where + orderby);

	    if (title != null && !title.isEmpty()) {
	        query.setParameter("title", title + "%");
	    }

	    if (genre != null && !genre.isEmpty()) {
	        query.setParameter("genre", genre + "%");
	    }

	    
	    if (director != null && !director.isEmpty()) {
	        query.setParameter("director", director + "%");
	    }


	    try {
	        list = query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}
}
