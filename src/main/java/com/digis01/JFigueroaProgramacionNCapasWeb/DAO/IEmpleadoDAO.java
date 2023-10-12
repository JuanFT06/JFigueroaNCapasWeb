/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.DAO;

import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Empleado;

/**
 *
 * @author digis
 */
public interface IEmpleadoDAO {
    Result GetAll(Empleado empleado);
    Result GetById(String numero);
    Result Add(Empleado empleado);
    Result Update(Empleado empleado);
    Result Delete(String numero);
    
}
