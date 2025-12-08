package com.jsf.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import com.jsf.entities.UserRole;

@Stateless
public class UserRoleDAO {

    private static final String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;

    public void create(UserRole userRole) {
        em.persist(userRole);
    }

    public UserRole merge(UserRole userRole) {
        return em.merge(userRole);
    }

    public void remove(UserRole userRole) {
        em.remove(em.merge(userRole));
    }

    public UserRole find(Object id) {
        return em.find(UserRole.class, id);
    }

    public List<UserRole> getFullList() {
  		List<UserRole> list = null;

  		Query query = em.createQuery("select ur from UserRole ur");

  		try {
  			list = query.getResultList();
  		} catch (Exception e) {
  			e.printStackTrace();
  		}

  		return list;
  	}
}
