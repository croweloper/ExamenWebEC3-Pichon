/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idat.controller;

import com.idat.controller.exceptions.NonexistentEntityException;
import com.idat.controller.exceptions.PreexistingEntityException;
import com.idat.model.Medicos;
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
public class MedicosJpaController implements Serializable {

    public MedicosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medicos medicos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(medicos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMedicos(medicos.getCodmed()) != null) {
                throw new PreexistingEntityException("Medicos " + medicos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Medicos medicos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            medicos = em.merge(medicos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = medicos.getCodmed();
                if (findMedicos(id) == null) {
                    throw new NonexistentEntityException("The medicos with id " + id + " no longer exists.");
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
            Medicos medicos;
            try {
                medicos = em.getReference(Medicos.class, id);
                medicos.getCodmed();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medicos with id " + id + " no longer exists.", enfe);
            }
            em.remove(medicos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Medicos> findMedicosEntities() {
        return findMedicosEntities(true, -1, -1);
    }

    public List<Medicos> findMedicosEntities(int maxResults, int firstResult) {
        return findMedicosEntities(false, maxResults, firstResult);
    }

    private List<Medicos> findMedicosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medicos.class));
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

    public Medicos findMedicos(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medicos.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medicos> rt = cq.from(Medicos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
