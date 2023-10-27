/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.controller;

import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.EmpleadoDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Empleado;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Empresa;
import com.digis01.JFigueroaProgramacionNCapasWeb.util.ResultExcel;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    private final EmpleadoDAOImplementation empleadoDAOImplementation;

    public CargaMasivaController(EmpleadoDAOImplementation empleadoDAOImplementation) {
        this.empleadoDAOImplementation = empleadoDAOImplementation;
    }

    @GetMapping("/subir")
    public String carga(Model model) {
        return "cargaMasiva";
    }

    @PostMapping("/subir-excel")
    public String procesar(@RequestParam("archivo") MultipartFile archivo, Model model, HttpSession session) throws IOException {
        if (archivo != null && !archivo.isEmpty()) {
            String extension = StringUtils.getFilenameExtension(archivo.getOriginalFilename());
            if (extension.equals("xlsx")) {
                List<Empleado> empleados = leerArchivo(archivo, "");
                String path = System.getProperty("user.dir") + "/src/main/resources/static/archivos/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + archivo.getOriginalFilename();
                archivo.transferTo(new File(path));
                if (empleados.size() > 0) {
                    ResultExcel resultExcel = Validar(empleados);
                    if (resultExcel.getErrores().size() > 0) {
                        model.addAttribute("errores", resultExcel.getErrores());
                        return "cargaMasiva";
                    } else {
                        session.setAttribute("path", path);
                        model.addAttribute("showGuardarExcel", true);
                    }
                }
            }
        }

        return "cargaMasiva";
    }

    @PostMapping("/subir-txt")
    public String procesartxt(@RequestParam("archivo") MultipartFile archivo, Model model, HttpSession session) throws IOException {
        if (archivo != null && !archivo.isEmpty()) {
            String extension = StringUtils.getFilenameExtension(archivo.getOriginalFilename());
            if (extension.equals("txt")) {
                List<Empleado> empleados = cargaMasivaTxt(archivo, "");

                String path = System.getProperty("user.dir") + "/src/main/resources/static/archivos/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + archivo.getOriginalFilename();
                archivo.transferTo(new File(path));
                if (empleados.size() > 0) {
                    ResultExcel resultExcel = Validar(empleados);
                    if (resultExcel.getErrores().size() > 0) {
                        model.addAttribute("errores", resultExcel.getErrores());
                        return "cargaMasiva";
                    } else {
                        session.setAttribute("path", path);
                        model.addAttribute("showGuardarTxt", true);
                    }
                }
            }
        }
        return "cargamasiva";
    }

    @PostMapping("/guardar-excel")
    public String guardar(HttpSession session) {

        String path = session.getAttribute("path").toString();
        String extension = StringUtils.getFilenameExtension(path);
        List<Empleado> empleados = new ArrayList<>();

        empleados = leerArchivo(null, path);

        for (Empleado empleado : empleados) {
            empleadoDAOImplementation.Add(empleado);
        }
        return "cargaMasiva";
    }

    @PostMapping("/guardar-txt")
    public String guardarTxt(HttpSession session) {

        String path = session.getAttribute("path").toString();
        List<Empleado> empleados = new ArrayList<>();

        empleados = cargaMasivaTxt(null, path);

        for (Empleado empleado : empleados) {
            empleadoDAOImplementation.Add(empleado);
        }
        return "cargaMasiva";
    }

    public ResultExcel Validar(List<Empleado> empleados) {

        ResultExcel resultPrincipal = new ResultExcel();

        resultPrincipal.setErrores(new ArrayList());
        int fila = 1;
        String errorMessage = "";

        for (Empleado empleado : empleados) {
            if (empleado.getNombre().equals("")) {
                //errorMessage = errorMessage + ""; lo mismo pero mas caro
                errorMessage += "Falto Nombre";
            }
            if (empleado.getApellidoPaterno().equals("")) {
                errorMessage += "Falto Apellido Paterno";
            }

            errorMessage += (empleado.getNumeroEmpleado().equals("")) ? "Falto Numero de empleado, " : "";
            errorMessage += (empleado.getApellidoMaterno().equals("")) ? "Falto Apellido Materno, " : "";
            errorMessage += (empleado.getEmail().equals("")) ? "Falto email, " : "";
            errorMessage += (empleado.getTelefono().equals("")) ? "Falto telefono, " : "";
            errorMessage += (empleado.getFechaNacimiento() == null) ? "Falto fecha de necimiento, " : "";
            errorMessage += (empleado.getNss().equals("")) ? "Falto nss, " : "";
            errorMessage += (empleado.getFechaIngreso() == null) ? "Falto fecha de ingreso, " : "";
            errorMessage += (empleado.getEmpresa().getIdempresa() == 0) ? "Falto id de empresa, " : "";
            errorMessage += (empleado.getRfc().equals("")) ? "Falto rfc, " : "";

            if (!errorMessage.equals("")) { //Hubo un error
                ResultExcel resultExcel = new ResultExcel();
                resultExcel.setRow(fila);
                resultExcel.setErrorMessage(errorMessage);

                resultPrincipal.getErrores().add(resultExcel);
                errorMessage = "";
            }

            fila++;
            //fila = fila + 1; lo mismo que arriba
        }
        return resultPrincipal;
    }

    public List<Empleado> leerArchivo(MultipartFile archivo, String path) {
        List<Empleado> empleados = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            if (path.equals("")) {
                workbook = new XSSFWorkbook(archivo.getInputStream());
            } else {
                workbook = new XSSFWorkbook(path);
            }
            Sheet worksheet = workbook.getSheetAt(0);

            for (Row row : worksheet) {
                //
                Empleado empleado = new Empleado();
                empleado.setNumeroEmpleado(row.getCell(0).toString());
                empleado.setNombre(row.getCell(1).toString());
                empleado.setApellidoPaterno(row.getCell(2).toString());
                empleado.setApellidoMaterno(row.getCell(3).toString());
                empleado.setEmail(row.getCell(4).toString());
                try {
                    String telefono = (row.getCell(5).getNumericCellValue()) > 0 ? telefono = String.valueOf(row.getCell(5).getNumericCellValue()) : "";
                    empleado.setTelefono(telefono);
                } catch (Exception e) {
                    empleado.setTelefono("");
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Reemplaza el formato con el correcto
                Date fechaNacimiento = new Date();
                Date fechaIngreso = new Date();
                try {
                    fechaNacimiento = dateFormat.parse(row.getCell(6).toString());
                    empleado.setFechaNacimiento(fechaNacimiento);
                } catch (ParseException ex) {
                    empleado.setFechaNacimiento(null);
                }
                try {
                    fechaIngreso = dateFormat.parse(row.getCell(8).toString());
                    empleado.setFechaIngreso(fechaIngreso);
                } catch (ParseException ex1) {
                    empleado.setFechaIngreso(null);
                }

                empleado.setNss(row.getCell(7).toString());

                Empresa empresa = new Empresa();
                try {
                    int idEmpresa = ((int) row.getCell(9).getNumericCellValue()) > 0 ? (int) row.getCell(9).getNumericCellValue() : 0;
                    empresa.setIdempresa(idEmpresa);
                } catch (Exception e) {
                    empresa.setIdempresa(0);
                }
                empleado.setEmpresa(empresa);
                empleado.setRfc(row.getCell(10).toString());
                empleados.add(empleado);

            }
        } catch (IOException ex) {
            Logger.getLogger(CargaMasivaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleados;
    }

    public List<Empleado> cargaMasivaTxt(MultipartFile archivo, String path) {
        List<Empleado> empleados = new ArrayList<>();
        InputStreamReader reader = null;
        if (!path.equals("")) {
            try {
                File file = new File(path);
                reader = new InputStreamReader(new FileInputStream(file));
            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            try {
                reader = new InputStreamReader(archivo.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();

            }
        }

        if (reader != null) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                String fila = bufferedReader.readLine();
                while ((fila = bufferedReader.readLine()) != null) {
                    String datos[] = fila.split("\\|");

                    Empleado empleado = new Empleado();
                    empleado.setNumeroEmpleado(datos[0]);
                    empleado.setNombre(datos[1]);
                    empleado.setApellidoPaterno(datos[2]);
                    empleado.setApellidoMaterno(datos[3]);
                    empleado.setEmail(datos[4]);
                    try {
                        long telefono = Long.parseLong(datos[5]);
                        empleado.setTelefono(String.valueOf(telefono));
                    } catch (Exception e) {
                        empleado.setTelefono("");
                    }
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Reemplaza el formato con el correcto
                    Date fechaNacimiento = new Date();
                    Date fechaIngreso = new Date();
                    try {
                        fechaNacimiento = dateFormat.parse(datos[6]);
                        empleado.setFechaNacimiento(fechaNacimiento);
                    } catch (ParseException ex) {
                        empleado.setFechaNacimiento(null);
                    }
                    try {
                        fechaIngreso = dateFormat.parse(datos[8]);
                        empleado.setFechaIngreso(fechaIngreso);
                    } catch (ParseException ex1) {
                        empleado.setFechaIngreso(null);
                    }

                    empleado.setFechaNacimiento(fechaNacimiento);
                    empleado.setNss(datos[7]);
                    empleado.setFechaIngreso(fechaIngreso);
                    Empresa empresa = new Empresa();
                    try {
                        empresa.setIdempresa(Integer.parseInt(datos[9]));
                    } catch (Exception e) {
                        empresa.setIdempresa(0);
                    }
                    empleado.setEmpresa(empresa);
                    empleado.setRfc(datos[10]);
                    empleados.add(empleado);
                }
            } catch (IOException e) {
                e.printStackTrace();
              
            }
        }
        return empleados;
    }

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        file.transferTo(convFile);
        return convFile;
    }
}
