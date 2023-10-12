/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.service;

import com.digis01.JFigueroaProgramacionNCapasWeb.entity.Datos;
import com.digis01.JFigueroaProgramacionNCapasWeb.entity.Respuesta;

/**
 *
 * @author digis
 */
public interface IRespuesta {
    public Respuesta suma(Datos datos);
    public Respuesta resta(Datos datos);
    public Respuesta multiplicacion(Datos datos);
    public Respuesta division(Datos datos);
}
