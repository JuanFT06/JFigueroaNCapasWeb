/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.DAO;

import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Empleado;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Empresa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digis
 */
@Repository
public class EmpleadoDAOImplementation implements IEmpleadoDAO {

    private EntityManager entityManager;

    @Autowired
    public EmpleadoDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Result GetAll(Empleado empleado) {
        Result result = new Result();
        try {
            TypedQuery<Object> query = entityManager.createQuery("FROM Empleado where upper(replace(nombre,' ','')) LIKE UPPER(:nombreempleado)"
                    + " AND upper(replace(apellidoPaterno,' ','')) like UPPER(:paterno)"
                    + " AND upper(replace(apellidoMaterno,' ','')) like UPPER(:materno)", Object.class);
            query.setParameter("nombreempleado", '%' + empleado.getNombre().replace(" ", "") + '%');
            query.setParameter("paterno", '%' + empleado.getApellidoPaterno().replace(" ", "") + '%');
            query.setParameter("materno", '%' + empleado.getApellidoMaterno().replace(" ", "") + '%');
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
    public Result GetById(String id) {
        Result result = new Result();
        try {
            result.object = entityManager.find(Empleado.class, id);
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
    public Result Add(Empleado empleado) {

        Result result = new Result();

        try {
            entityManager.persist(empleado);
            result.correct = true;
            result.object = empleado;
        } catch (Exception e) {
            result.correct = false;
            result.ErrorMessage = e.getLocalizedMessage();
            result.ex = e;
        }

        return result;
    }

    @Override
    @Transactional
    public Result Update(Empleado empleado) {
        Result result = new Result();

        try {
            entityManager.merge(empleado);
            result.correct = true;
            result.object = empleado;
        } catch (Exception e) {
            result.correct = false;
            result.ErrorMessage = e.getLocalizedMessage();
            result.ex = e;
        }

        return result;
    }

    @Override
    @Transactional
    public Result Delete(String id) {
        Result result = new Result();
        try {
            Empleado empleado = entityManager.find(Empleado.class, id);
            if (empleado != null) {
                entityManager.remove(empleado);
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
    public Result GetByEmpresa(int idempresa) {
        Result result = new Result();
        try {
            TypedQuery<Object> query = entityManager.createQuery("SELECT e FROM Empleado e WHERE e.empresa.idempresa=: id", Object.class);
            query.setParameter("id", idempresa);
            result.objects = query.getResultList();
            if(result.objects!=null){
                result.correct=true;
            }else{
               result.correct=false;
            }
        } catch (Exception e) {
            result.correct = false;
            result.ErrorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return result;
    }

}
