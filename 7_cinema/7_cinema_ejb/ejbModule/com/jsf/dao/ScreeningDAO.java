package com.jsf.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import com.jsf.entities.Screening;

@Stateless
public class ScreeningDAO {

    private static final String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;

    public void create(Screening screening) {
        em.persist(screening);
    }

    public Screening merge(Screening screening) {
        return em.merge(screening);
    }

    public void remove(Screening screening) {
        em.remove(em.merge(screening));
    }

    public Screening find(Object id) {
        return em.find(Screening.class, id);
    }
    
    public List<Screening> getFullList() {
		List<Screening> list = null;

		Query query = em.createQuery("select sc from Screening sc");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
