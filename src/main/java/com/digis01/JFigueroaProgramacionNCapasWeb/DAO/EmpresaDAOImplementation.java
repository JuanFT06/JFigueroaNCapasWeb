/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.DAO;

import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Empresa;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author digis
 */
@Repository
public class EmpresaDAOImplementation implements IEmpresaDAO{
    
    private EntityManager entityManager;

    @Autowired
    public EmpresaDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
   
    @Override
    public Result GetAll(Empresa empresa) {
        Result result= new Result();
        try {
            TypedQuery<Object> query = entityManager.createQuery("FROM Empresa WHERE UPPER(nombre)  LIKE UPPER(:nombreempresa) ORDER BY idempresa", Object.class);
            query.setParameter("nombreempresa", '%'+empresa.getNombre()+'%');
            result.objects = query.getResultList();
            result.correct = true;
        } catch (Exception e) {
            result.correct = false;
            result.ErrorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
        
    }

    @Override
    @Transactional
    public Result Add(Empresa empresa) {

         Result result = new Result();

        try {
            entityManager.persist(empresa);
            result.correct = true;
            result.object=empresa;
        } catch (Exception e) {
            result.correct = false;
            result.ErrorMessage = e.getLocalizedMessage();
            result.ex = e;
        }

        return result;
    }
    
    
    @Override
    @Transactional
    public Result Delete(int id) {
        Result result = new Result();
        try {
            Empresa empresa = entityManager.find(Empresa.class, id);
            if (empresa != null) {
                entityManager.remove(empresa);
                result.correct = true;
            } else {
                result.correct = false;
            }

        } catch (Exception e) {
            result.ErrorMessage = e.getLocalizedMessage();
            result.correct = false;
            result.ex = e;
        }

        return result;
    }

    @Override
    public Result GetById(int id) {
        Result result = new Result();
        try {
            result.object = entityManager.find(Empresa.class, id);
            result.correct = true;
        } catch (Exception e) {
            result.ErrorMessage = e.getLocalizedMessage();
            result.correct = false;
            result.ex = e;
        }

        return result;
    }

    @Override
    @Transactional
    public Result Update(Empresa empresa) {
        Result result = new Result();
        
        try {
            entityManager.merge(empresa);
            result.correct = true;
        } catch (Exception e) {
            result.ErrorMessage = e.getLocalizedMessage();
            result.correct = false;
            result.ex = e;
        }
        return result;
    }

}
