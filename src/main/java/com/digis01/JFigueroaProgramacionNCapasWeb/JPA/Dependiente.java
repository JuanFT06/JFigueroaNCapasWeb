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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.annotations.GeneratedColumn;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author digis
 */
@Entity
public class Dependiente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iddependiente;
    @NotEmpty(message = "Ingresa un nombre")
    private String nombre;
    @NotEmpty(message = "Ingresa un apellido")
    private String apellido_paterno;
    private String apellido_materno;
    @NotEmpty(message = "Ingresa tu telefono")
    @Size(max = 10, message = "ingresa un numero de 10 digitos")
    private String telefono;
    private String estado_civil;
    @NotEmpty(message = "Selecciona un genero")
    private String genero; 
    @NotEmpty(message = "Ingresa tu RFC")
    private String rfc;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
 
    private Date fecha_nacimiento;
    @ManyToOne
    @JoinColumn(name = "numero_empleado")
    private Empleado empleado;
    @ManyToOne
    @JoinColumn(name = "iddependiente_tipo")
    private DependienteTipo dependienteTipo;

    public Dependiente() {
    }

    public Dependiente(String nombre, String apellido_paterno, String apellido_materno, String telefono, String estado_civil, String genero, String rfc, Date fecha_nacimiento, Empleado empleado, DependienteTipo dependienteTipo) {
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.telefono = telefono;
        this.estado_civil = estado_civil;
        this.genero = genero;
        this.rfc = rfc;
        this.fecha_nacimiento = fecha_nacimiento;
        this.empleado = empleado;
        this.dependienteTipo = dependienteTipo;
    }

    public Dependiente(int iddependiente, String nombre, String apellido_paterno, String apellido_materno, String telefono, String estado_civil, String genero, String rfc, Date fecha_nacimiento, Empleado empleado, DependienteTipo dependienteTipo) {
        this.iddependiente = iddependiente;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.telefono = telefono;
        this.estado_civil = estado_civil;
        this.genero = genero;
        this.rfc = rfc;
        this.fecha_nacimiento = fecha_nacimiento;
        this.empleado = empleado;
        this.dependienteTipo = dependienteTipo;
    }

    
  

    

    public int getIddependiente() {
        return iddependiente;
    }

    public void setIddependiente(int iddependiente) {
        this.iddependiente = iddependiente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public DependienteTipo getDependienteTipo() {
        return dependienteTipo;
    }

    public void setDependienteTipo(DependienteTipo dependienteTipo) {
        this.dependienteTipo = dependienteTipo;
    }

}
