/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.DAO;

import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Rol;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Usuario;
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
public class UsuarioDAOImplementation implements IUsuarioDAO {

    private EntityManager entityManager;

    @Autowired
    public UsuarioDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Result GetAll(Usuario usuario) {
        Result result = new Result();
        try {
            TypedQuery<Object> query = entityManager.createQuery("FROM Usuario where upper(replace(nombre,' ','')) LIKE UPPER(:nombreusuario)"
                    + " AND upper(replace(apellido_paterno,' ','')) like UPPER(:paterno)"
                    + " AND upper(replace(apellido_materno,' ','')) like UPPER(:materno) ORDER BY idusuario", Object.class);
            query.setParameter("nombreusuario", '%'+usuario.getNombre().replace(" ","")+'%');
            query.setParameter("paterno", '%'+usuario.getApellido_paterno().replace(" ","")+'%');
            query.setParameter("materno", '%'+usuario.getApellido_materno().replace(" ","")+'%');
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
    public Result Add(Usuario usuario) {
        Result result = new Result();

        try {
            entityManager.persist(usuario);
            result.correct = true;
            result.object=usuario;
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
            Usuario usuario = entityManager.find(Usuario.class, id);
            if (usuario != null) {
                entityManager.remove(usuario);
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
            result.object = entityManager.find(Usuario.class, id);
           if(result.object!=null){
                result.correct = true;
           }else{
               result.correct=false;
           }
        } catch (Exception e) {
            result.ErrorMessage = e.getLocalizedMessage();
            result.correct = false;
            result.ex = e;
        }

        return result;
    }

    @Override
    @Transactional
    public Result Update(Usuario usuario) {
        Result result = new Result();
        try {
            entityManager.merge(usuario);
            result.correct = true;
        } catch (Exception e) {
            result.ErrorMessage = e.getLocalizedMessage();
            result.correct = false;
            result.ex = e;
        }
        return result;
    }

    @Override
    public List<Usuario> GetByIdRol(int id) {
       TypedQuery<Usuario> query= entityManager.createQuery("FROM Usuario WHERE fk(idrol)=:id",Usuario.class);
       query.setParameter("id", id);
       List<Usuario> usuarios=query.getResultList();
       return usuarios;
    }

  

}
