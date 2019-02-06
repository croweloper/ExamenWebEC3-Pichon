/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idat.SQL;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author xen
 */
public class SQL {
    
    public static List sqlConsulta(String sql){
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("EC03-WebPU");
        EntityManager em=emf.createEntityManager();
        Query query=em.createNativeQuery(sql);
        List list=query.getResultList();
        return list;
    }
    
}
