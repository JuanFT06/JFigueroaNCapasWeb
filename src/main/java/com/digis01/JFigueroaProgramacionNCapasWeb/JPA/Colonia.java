/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

/**
 *
 * @author digis
 */
@Entity
public class Colonia implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcolonia;
    
    private String nombre;
    private String codigo_postal;
    
    @ManyToOne
    @JoinColumn(name="idmunicipio")
    private Municipio municipio;

    public Colonia() {
    }

    public Colonia(String nombre, String codigo_postal, Municipio municipio) {
        this.nombre = nombre;
        this.codigo_postal = codigo_postal;
        this.municipio = municipio;
    }

    public Colonia(int idcolonia, String nombre, String codigo_postal, Municipio municipio) {
        this.idcolonia = idcolonia;
        this.nombre = nombre;
        this.codigo_postal = codigo_postal;
        this.municipio = municipio;
    }

    public int getIdcolonia() {
        return idcolonia;
    }

    public void setIdcolonia(int idcolonia) {
        this.idcolonia = idcolonia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    
    
    
    
    
}
