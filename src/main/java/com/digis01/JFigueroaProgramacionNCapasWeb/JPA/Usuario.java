/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.JPA;


import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author digis
 */
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idusuario;

    @NotEmpty(message = "Ingresa un nombre")
    private String nombre;
    @NotEmpty(message = "Ingresa un apellido")
    private String apellido_paterno;
    private String apellido_materno;
    @NotEmpty(message = "Ingresa tu email")
    private String email;
    @NotEmpty(message = "Ingresa tu telefono")
    @Size( max = 10, message = "ingresa un numero de 10 digitos")
    private  String telefono;
    private String celular;
    private String curp;
    @NotEmpty(message = "Ingresa una contraseña")
    @Size( min = 8, message = "ingresa una contraseña de minimo 8 caracteres")
    private String password;
    @NotEmpty(message = "Ingresa un username")
    private String username;
    @NotEmpty(message = "Escoge un genero")
    private String sexo;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fecha_nacimiento;
    
    @ManyToOne()
    @JoinColumn(name="idrol")
    private Rol rol;
    
    @Lob
    private String imagen;
    
  
    private int status;
    
    public Usuario() {

    }

    public Usuario(String nombre, String apellido_paterno, String apellido_materno) {
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
    }
    

    public Usuario(String nombre, String apellido_paterno, String apellido_materno, String email, String telefono, String celular, String curp, String password, String username, String sexo, Date fecha_nacimiento, Rol rol, String imagen, int status) {
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.email = email;
        this.telefono = telefono;
        this.celular = celular;
        this.curp = curp;
        this.password = password;
        this.username = username;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.rol = rol;
        this.imagen = imagen;
        this.status = status;
    }

    public Usuario(int idusuario, String nombre, String apellido_paterno, String apellido_materno, String email, String telefono, String celular, String curp, String password, String username, String sexo, Date fecha_nacimiento, Rol rol, String imagen, int status) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.email = email;
        this.telefono = telefono;
        this.celular = celular;
        this.curp = curp;
        this.password = password;
        this.username = username;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.rol = rol;
        this.imagen = imagen;
        this.status = status;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

  
    
    
    
}
