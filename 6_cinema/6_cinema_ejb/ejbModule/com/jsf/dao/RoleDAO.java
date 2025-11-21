package com.jsf.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import com.jsf.entities.Role;

@Stateless
public class RoleDAO {

    private static final String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;

    public void create(Role role) {
        em.persist(role);
    }

    public Role merge(Role role) {
        return em.merge(role);
    }

    public void remove(Role role) {
        em.remove(em.merge(role));
    }

    public Role find(Object id) {
        return em.find(Role.class, id);
    }

    public List<Role> getFullList() {
		List<Role> list = null;

		Query query = em.createQuery("select ro from Role ro");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
