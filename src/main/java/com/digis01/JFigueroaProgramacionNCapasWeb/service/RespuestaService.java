/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.service;

import com.digis01.JFigueroaProgramacionNCapasWeb.entity.Datos;
import com.digis01.JFigueroaProgramacionNCapasWeb.entity.Respuesta;
import org.springframework.stereotype.Service;

/**
 *
 * @author digis
 */
@Service
public class RespuestaService implements IRespuesta {

    @Override
    public Respuesta suma(Datos datos) {
        Respuesta respuesta = new Respuesta();
        respuesta.setRespuesta(datos.getN1() + datos.getN2());
        return respuesta;
    }

    @Override
    public Respuesta resta(Datos datos) {
        Respuesta respuesta = new Respuesta();
        respuesta.setRespuesta(datos.getN1() - datos.getN2());
        return respuesta;
    }

    @Override
    public Respuesta multiplicacion(Datos datos) {
        Respuesta respuesta = new Respuesta();
        respuesta.setRespuesta(datos.getN1() * datos.getN2());
        return respuesta;
    }

    @Override
    public Respuesta division(Datos datos) {
        Respuesta respuesta = new Respuesta();
        respuesta.setRespuesta(datos.getN1() / datos.getN2());
        return respuesta;
    }

}
