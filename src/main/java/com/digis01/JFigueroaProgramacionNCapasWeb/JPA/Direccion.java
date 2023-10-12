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
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 *
 * @author digis
 */
@Entity
public class Direccion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddireccion;
    
    @NotEmpty(message = "Ingresa una calle")
    private String calle;
     @NotEmpty(message = "Ingresa un numero interior")
    private String numero_interior;
      @NotEmpty(message = "Ingresa un numero exterior")
    private String numero_exterior;
    
    @ManyToOne
    @JoinColumn(name="idcolonia")
    private Colonia colonia;
    
    @OneToOne
    @JoinColumn(name="idusuario")
    private Usuario usuario;

    public Direccion() {
    }

    public Direccion(String calle, String numero_interior, String numero_exterior, Colonia colonia, Usuario usuario) {
        this.calle = calle;
        this.numero_interior = numero_interior;
        this.numero_exterior = numero_exterior;
        this.colonia = colonia;
        this.usuario = usuario;
    }

    public Direccion(int iddireccion, String calle, String numero_interior, String numero_exterior, Colonia colonia, Usuario usuario) {
        this.iddireccion = iddireccion;
        this.calle = calle;
        this.numero_interior = numero_interior;
        this.numero_exterior = numero_exterior;
        this.colonia = colonia;
        this.usuario = usuario;
    }

    public int getIddireccion() {
        return iddireccion;
    }

    public void setIddireccion(int iddireccion) {
        this.iddireccion = iddireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero_interior() {
        return numero_interior;
    }

    public void setNumero_interior(String numero_interior) {
        this.numero_interior = numero_interior;
    }

    public String getNumero_exterior() {
        return numero_exterior;
    }

    public void setNumero_exterior(String numero_exterior) {
        this.numero_exterior = numero_exterior;
    }

    public Colonia getColonia() {
        return colonia;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
