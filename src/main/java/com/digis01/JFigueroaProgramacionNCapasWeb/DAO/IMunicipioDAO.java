/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.DAO;

import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Municipio;
import java.util.List;

/**
 *
 * @author digis
 */
public interface IMunicipioDAO {
    List<Municipio> GetByIdEstado(int id);
}
