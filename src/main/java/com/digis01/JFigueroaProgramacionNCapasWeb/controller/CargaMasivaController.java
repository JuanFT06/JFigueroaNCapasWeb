/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.controller;

import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Empleado;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Empresa;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("/carga")
public class CargaMasivaController {
    
    @GetMapping("/subir")
    public String carga(Model model) {
        return "cargaMasiva";
    }
    
    @PostMapping("/subir")
    public String guardar(@RequestParam("archivo") MultipartFile archivo) {
        if (archivo != null && !archivo.isEmpty()) {
            String extension = StringUtils.getFilenameExtension(archivo.getOriginalFilename());
            if (extension.equals("xlsx")) {
                leerArchivo(archivo);
            }
        }
        
        return "view";
    }
    
    public void leerArchivo(MultipartFile archivo) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(archivo.getInputStream());
            Sheet worksheet = workbook.getSheetAt(0);
            
            for (Row row : worksheet) {
                //
                Empleado empleado = new Empleado();
                empleado.setNumeroEmpleado(row.getCell(0).toString());
                empleado.setNombre(row.getCell(1).toString());
                empleado.setApellidoPaterno(row.getCell(2).toString());
                empleado.setApellidoMaterno(row.getCell(3).toString());
                empleado.setEmail(row.getCell(4).toString());
                empleado.setTelefono(row.getCell(5).toString());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Reemplaza el formato con el correcto
                Date fechaNacimiento = new Date();
                Date fechaIngreso = new Date();
                try {
                    fechaNacimiento = dateFormat.parse(row.getCell(6).toString());
                    fechaIngreso = dateFormat.parse(row.getCell(8).toString());
                } catch (ParseException ex) {
                    Logger.getLogger(CargaMasivaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                empleado.setFechaNacimiento(fechaNacimiento);
                empleado.setNss(row.getCell(7).toString());
                empleado.setFechaIngreso(fechaIngreso);
                Empresa empresa = new Empresa();
                empresa.setIdempresa(Integer.parseInt(row.getCell(9).toString()));
                empleado.setEmpresa(empresa);
                
            }
        } catch (IOException ex) {
            Logger.getLogger(CargaMasivaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
