package com.jsf.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import com.jsf.entities.User;

@Stateless
public class UserDAO {

    private static final String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;

    public void create(User user) {
        em.persist(user);
    }

    public User merge(User user) {
        return em.merge(user);
    }

    public void remove(User user) {
        em.remove(em.merge(user));
    }

    public User find(Object id) {
        return em.find(User.class, id);
    }
   
    public List<User> getFullList() {
  		List<User> list = null;

  		Query query = em.createQuery("select us from User us");

  		try {
  			list = query.getResultList();
  		} catch (Exception e) {
  			e.printStackTrace();
  		}

  		return list;
  	}
}
