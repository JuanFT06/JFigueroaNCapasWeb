/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.DAO;

import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Dependiente;

/**
 *
 * @author digis
 */
public interface IDependienteDAO {
    Result GetAll(String numeroEmpleado);
    Result Add(Dependiente dependiente);
    Result Update(Dependiente dependiente);
    Result Delete(int id);
    Result GetById(int id);
}
