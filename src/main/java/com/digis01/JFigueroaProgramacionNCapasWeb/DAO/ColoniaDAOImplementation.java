/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.DAO;

import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Colonia;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Estado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digis
 */
@Repository
public class ColoniaDAOImplementation implements IColoniaDAO {

    private EntityManager entityManager;

    @Autowired
    public ColoniaDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Colonia> GetByIdMunicipio(int id) {
        TypedQuery<Colonia> query = entityManager.createQuery("SELECT c FROM Colonia c WHERE c.municipio.idmunicipio=:id", Colonia.class);
        query.setParameter("id", id);
        List<Colonia> colonias = query.getResultList();
        return colonias;
    }

}
