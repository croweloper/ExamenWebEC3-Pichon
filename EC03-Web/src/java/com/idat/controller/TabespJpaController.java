/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idat.controller;

import com.idat.controller.exceptions.NonexistentEntityException;
import com.idat.controller.exceptions.PreexistingEntityException;
import com.idat.model.Tabesp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author crowe
 */
public class TabespJpaController implements Serializable {

    public TabespJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tabesp tabesp) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tabesp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTabesp(tabesp.getCode()) != null) {
                throw new PreexistingEntityException("Tabesp " + tabesp + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tabesp tabesp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tabesp = em.merge(tabesp);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tabesp.getCode();
                if (findTabesp(id) == null) {
                    throw new NonexistentEntityException("The tabesp with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tabesp tabesp;
            try {
                tabesp = em.getReference(Tabesp.class, id);
                tabesp.getCode();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tabesp with id " + id + " no longer exists.", enfe);
            }
            em.remove(tabesp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tabesp> findTabespEntities() {
        return findTabespEntities(true, -1, -1);
    }

    public List<Tabesp> findTabespEntities(int maxResults, int firstResult) {
        return findTabespEntities(false, maxResults, firstResult);
    }

    private List<Tabesp> findTabespEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tabesp.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tabesp findTabesp(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tabesp.class, id);
        } finally {
            em.close();
        }
    }

    public int getTabespCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tabesp> rt = cq.from(Tabesp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
