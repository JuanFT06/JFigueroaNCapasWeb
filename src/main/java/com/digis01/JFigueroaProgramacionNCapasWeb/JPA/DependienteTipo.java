/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.JPA;

import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import org.hibernate.annotations.GeneratedColumn;
import org.springframework.boot.autoconfigure.web.WebProperties;

/**
 *
 * @author digis
 */
@Entity
@Table(name = "dependiente_tipo")
public class DependienteTipo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddependiente_tipo")
    private int idDependienteTipo;
    private String nombre;

    public DependienteTipo() {
    }

    public DependienteTipo(String nombre) {
        this.nombre = nombre;
    }

    public DependienteTipo(int idDependienteTipo, String nombre) {
        this.idDependienteTipo = idDependienteTipo;
        this.nombre = nombre;
    }

    public int getIdDependienteTipo() {
        return idDependienteTipo;
    }

    public void setIdDependienteTipo(int idDependienteTipo) {
        this.idDependienteTipo = idDependienteTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
