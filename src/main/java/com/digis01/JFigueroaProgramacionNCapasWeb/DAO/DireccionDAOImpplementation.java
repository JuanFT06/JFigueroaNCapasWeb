/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.DAO;

import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Direccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author digis
 */
@Repository
public class DireccionDAOImpplementation implements IDireccionDAO {

    private EntityManager entityManager;

    public DireccionDAOImpplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Result GetAll() {
        Result result = new Result();
        try {
            TypedQuery<Object> query = entityManager.createQuery("FROM Direccion", Object.class);
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
    public Result Add(Direccion direccion) {
        Result result = new Result();

        try {
            entityManager.persist(direccion);
            result.correct = true;
            result.object = direccion;
        } catch (Exception e) {
            result.correct = false;
            result.ErrorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }
    @Override
    @Transactional
    public Result Update(Direccion direccion) {
        Result result = new Result();

        try {
            entityManager.merge(direccion);
            result.correct = true;
            result.object = direccion;
        } catch (Exception e) {
            result.correct = false;
            result.ErrorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }

    @Override
    public Result GetByUser(int id) {
        Result result = new Result();
        try {
            TypedQuery<Direccion> query = entityManager.createQuery("SELECT d FROM Direccion d WHERE d.usuario.idusuario = :id", Direccion.class);
            query.setParameter("id", id);
            Direccion direccion = (Direccion) query.getSingleResult();
            result.object=direccion;
            result.correct = true;
        } catch (Exception e) {
            result.correct = false;
            result.ErrorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }

}
