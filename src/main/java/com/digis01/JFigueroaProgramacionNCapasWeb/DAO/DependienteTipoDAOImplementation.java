/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digis
 */
@Repository
public class DependienteTipoDAOImplementation implements IDependienteTipoDAO{
    
    private EntityManager entityManager;

    @Autowired
    public DependienteTipoDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    

    @Override
    public Result GetAll() {
      Result result = new Result();
        try {
            TypedQuery<Object> query = entityManager.createQuery("FROM  DependienteTipo ", Object.class);
            result.objects = query.getResultList();
            result.correct = true;
        } catch (Exception e) {
            result.correct = false;
            result.ErrorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }
    
}
