package com.jsf.dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import com.jsf.entities.Ticket;

@Stateless
public class TicketDAO {

    private static final String UNIT_NAME = "jsfcourse-simplePU";

    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager em;

    public void create(Ticket ticket) {
        em.persist(ticket);
    }

    public Ticket merge(Ticket ticket) {
        return em.merge(ticket);
    }

    public void remove(Ticket ticket) {
        em.remove(em.merge(ticket));
    }

    public Ticket find(Object id) {
        return em.find(Ticket.class, id);
    }
      
    public List<Ticket> getFullList() {
  		List<Ticket> list = null;

  		Query query = em.createQuery("select t from Ticket t");

  		try {
  			list = query.getResultList();
  		} catch (Exception e) {
  			e.printStackTrace();
  		}

  		return list;
  	}
}
