/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.DAO;

import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Empresa;

/**
 *
 * @author digis
 */
public interface IEmpresaDAO {
    
    Result GetAll(Empresa empresa);
    Result Add(Empresa empresa);
    Result Delete(int id);
    Result GetById(int id);
    Result Update(Empresa empresa);
}
