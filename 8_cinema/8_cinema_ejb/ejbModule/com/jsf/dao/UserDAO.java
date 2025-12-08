package com.jsf.dao;

import java.util.ArrayList;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
    
	public User getUserFromDatabase(String login, String pass) {
		
		User u = null;

		if (login.equals("user") && pass.equals("password")) {
			u = new User();
			u.setLogin(login);
			u.setPassword(pass);
		}

		if (login.equals("manager") && pass.equals("password")) {
			u = new User();
			u.setLogin(login);
			u.setPassword(pass);
		}

		if (login.equals("admin") && pass.equals("password")) {
			u = new User();
			u.setLogin(login);
			u.setPassword(pass);
		}

		return u;
	}

	// simulate retrieving roles of a User from DB
	public List<String> getUserRolesFromDatabase(User user) {
		
		ArrayList<String> roles = new ArrayList<String>();
		
		if (user.getLogin().equals("user")) {
			roles.add("user");
		}
		if (user.getLogin().equals("manager")) {
			roles.add("manager");
		}
		if (user.getLogin().equals("admin")) {
			roles.add("admin");
		}
		
		return roles;
	}

}
