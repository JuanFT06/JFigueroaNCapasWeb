/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.DAO;

import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Dependiente;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Empleado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author digis
 */
@Repository
public class DependienteDAOImplementation implements IDependienteDAO {

    private EntityManager entityManager;

    @Autowired
    public DependienteDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Result GetAll(String numeroEmpleado) {
        Result result = new Result();
        try {
            TypedQuery<Object> query = entityManager.createQuery("SELECT d FROM Dependiente d WHERE d.empleado.numeroEmpleado = :numero", Object.class);
            query.setParameter("numero", numeroEmpleado);
            result.objects = query.getResultList();
            if (result.objects.size()>0) {
                result.correct = true;
            } else {
                result.correct = false;
            }

        } catch (Exception e) {
            result.correct = false;
            result.ErrorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;

    }

    @Override
    @Transactional
    public Result Add(Dependiente dependiente) {
        Result result = new Result();

        try {
            entityManager.persist(dependiente);
            result.correct = true;
            result.object = dependiente;
        } catch (Exception e) {
            result.correct = false;
            result.ErrorMessage = e.getLocalizedMessage();
            result.ex = e;
        }

        return result;
    }

    @Override
    @Transactional
    public Result Update(Dependiente dependiente) {
        Result result = new Result();

        try {
            entityManager.merge(dependiente);
            result.correct = true;
            result.object = dependiente;
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
            Dependiente dependiente = entityManager.find(Dependiente.class, id);
            if (dependiente != null) {
                entityManager.remove(dependiente);
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
            result.object = entityManager.find(Dependiente.class, id);
            result.correct = true;
        } catch (Exception e) {
            result.ErrorMessage = e.getLocalizedMessage();
            result.correct = false;
            result.ex = e;
        }

        return result;
    }

}
