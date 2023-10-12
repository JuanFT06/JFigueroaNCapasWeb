/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.DAO;

import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Direccion;
import java.util.List;

/**
 *
 * @author digis
 */
public interface IDireccionDAO {
    Result GetAll();
    
    Result Add(Direccion direccion);
    Result Update(Direccion direccion);
    
    Result GetByUser(int id);
}
