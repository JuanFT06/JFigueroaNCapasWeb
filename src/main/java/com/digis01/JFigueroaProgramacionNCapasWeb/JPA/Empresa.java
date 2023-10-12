/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;

/**
 *
 * @author digis
 */
@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idempresa;

    @NotEmpty(message = "ingresa un nombre")
    private String nombre;

    @NotEmpty(message = "ingresa un telefono")
    private String telefono;

    @NotEmpty(message = "ingresa un email")
    private String email;

    @NotEmpty(message = "ingresa una direccion web")
    private String direccion_web;
    
    @Lob
    private String logo;

    public Empresa() {
    }

    public Empresa(String nombre, String telefono, String email, String direccion_web, String logo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion_web = direccion_web;
        this.logo = logo;
    }

    public Empresa(int idempresa, String nombre, String telefono, String email, String direccion_web, String logo) {
        this.idempresa = idempresa;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion_web = direccion_web;
        this.logo = logo;
    }

    

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion_web() {
        return direccion_web;
    }

    public void setDireccion_web(String direccion_web) {
        this.direccion_web = direccion_web;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    
    
}
