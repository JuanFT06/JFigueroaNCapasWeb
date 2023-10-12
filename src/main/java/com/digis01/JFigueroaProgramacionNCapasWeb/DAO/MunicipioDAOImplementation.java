/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.DAO;

import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Municipio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author digis
 */
@Repository
public class MunicipioDAOImplementation implements IMunicipioDAO{

    private EntityManager entityManager;

    public MunicipioDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public List<Municipio> GetByIdEstado(int id) {
        TypedQuery<Municipio> query=entityManager.createQuery("SELECT m FROM Municipio m where m.estado.idestado=:id",Municipio.class);
        query.setParameter("id", id);
        List<Municipio> municipios= query.getResultList();
        return municipios;
    }
    
}
