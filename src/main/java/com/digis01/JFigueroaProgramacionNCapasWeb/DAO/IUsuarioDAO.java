/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.DAO;

import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Rol;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Usuario;
import java.util.List;

/**
 *
 * @author digis
 */
public interface IUsuarioDAO {

    Result GetAll(Usuario usuario);

    Result GetById(int id);

    Result Add(Usuario usuario);

    Result Update(Usuario usuario);

    Result Delete(int id);
    
    Result GetByEmail(String email);

}
