/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.controller;

import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.DependienteDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.EmpleadoDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.EmpresaDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.Result;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Dependiente;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Empleado;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Empresa;
import com.digis01.JFigueroaProgramacionNCapasWeb.util.Util;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("empleados")
public class EmpleadoController {

    private EmpleadoDAOImplementation empleadoDAOImplementation;
    private EmpresaDAOImplementation empresaDAOImplementation;
    private DependienteDAOImplementation dependienteDAOImplementation;
    @Autowired
    public EmpleadoController(EmpleadoDAOImplementation empleadoDAOImplementation,
            EmpresaDAOImplementation empresaDAOImplementation,DependienteDAOImplementation dependienteDAOImplementation) {
        this.empleadoDAOImplementation = empleadoDAOImplementation;
        this.empresaDAOImplementation = empresaDAOImplementation;
        this.dependienteDAOImplementation=dependienteDAOImplementation;
    }

    @GetMapping("/lista")
    public String getAll(Model model) {

        Empleado empleado = new Empleado();
        empleado.setNombre("");
        empleado.setApellidoPaterno("");
        empleado.setApellidoMaterno("");
        List<Empleado> empleados = new ArrayList<>();
        Result result = empleadoDAOImplementation.GetAll(empleado);
        for (Object obj : result.objects) {
            empleados.add((Empleado) obj);
        }
        model.addAttribute("empleadobusqueda", empleado);
        model.addAttribute("empleados", empleados);

        return "empleadosList";

    }

    @PostMapping("/lista")
    public String getAll(Model model, @ModelAttribute("empleadobusqueda") Empleado empleadoBusqueda) {

        List<Empleado> empleados = new ArrayList<>();
        Result result = empleadoDAOImplementation.GetAll(empleadoBusqueda);
        for (Object obj : result.objects) {
            empleados.add((Empleado) obj);
        }
        model.addAttribute("empleadobusqueda", empleadoBusqueda);
        model.addAttribute("empleados", empleados);

        return "empleadosList";

    }
    @GetMapping("/dependientes")
    public String getAllTodependiente(Model model) {

        Empleado empleado = new Empleado();
        empleado.setNombre("");
        empleado.setApellidoPaterno("");
        empleado.setApellidoMaterno("");
        List<Empleado> empleados = new ArrayList<>();
        Result result = empleadoDAOImplementation.GetAll(empleado);
        for (Object obj : result.objects) {
            empleados.add((Empleado) obj);
        }
        model.addAttribute("empleadobusqueda", empleado);
        model.addAttribute("empleados", empleados);

        return "empleadosDependientes";

    }

    @PostMapping("/dependientes")
    public String getAllTodependiente(Model model, @ModelAttribute("empleadobusqueda") Empleado empleadoBusqueda) {

        List<Empleado> empleados = new ArrayList<>();
        Result result = empleadoDAOImplementation.GetAll(empleadoBusqueda);
        for (Object obj : result.objects) {
            empleados.add((Empleado) obj);
        }
        model.addAttribute("empleadobusqueda", empleadoBusqueda);
        model.addAttribute("empleados", empleados);

        return "empleadosDependientes";

    }

    @GetMapping("/form/{numeroempleado}")
    public String mostrarFormulario(Model model, @PathVariable("numeroempleado") String numeroEmpleado) {
        Empresa empresa = new Empresa();
        empresa.setNombre("");

        Result result = empresaDAOImplementation.GetAll(empresa);

        if (result.correct) {
            List<Empresa> empresas = new ArrayList<>();
            for (Object objeto : result.objects) {
                empresas.add((Empresa) objeto);
            }
            model.addAttribute("empresas", empresas);
        }

        if (numeroEmpleado.equals("0")) {//add
            model.addAttribute("empleado", new Empleado());
        } else {
            Result empleado = empleadoDAOImplementation.GetById(numeroEmpleado);
            model.addAttribute("empleado", (Empleado) empleado.object);
        }
        return "empleadoForm";
    }

    @PostMapping("/form")
    public String guardar(@Valid @ModelAttribute("empleado") Empleado empleado, BindingResult bindingResult, Model model,
            @RequestParam("imageFile") MultipartFile imageFile) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("empleado", empleado);
            return "empleadoForm";
        }
        String imagen;

        if (!imageFile.isEmpty()) {
            imagen = Util.code64(imageFile);
        } else {
            imagen = empleado.getFoto();
        }

        if (empleado.getNumeroEmpleado().equals("")) {//add
            empleado.setNumeroEmpleado(GenerarNumeroEmpleado(empleado));
            empleado.setFoto(imagen);
            Result result = empleadoDAOImplementation.Add(empleado);
            if (result.correct) {
                return "redirect:/empleados/lista";
            }
        } else {//update
            empleado.setFoto(imagen);
            Result result = empleadoDAOImplementation.Update(empleado);
            if (result.correct) {
                return "redirect:/empleados/lista";
            }
        }
        return "empresaForm";

    }

    @PostMapping("/delete/{numeroempleado}")
    public String delete(@PathVariable("numeroempleado") String numeroEmpleado) {
       
        Result resultDependientes=dependienteDAOImplementation.GetAll(numeroEmpleado);
        if(resultDependientes.correct){
            List<Dependiente> dependientes= new ArrayList<>();
            for(Object object:resultDependientes.objects){
                dependientes.add((Dependiente) object);
            }
            for (Dependiente dependiente:dependientes) {
              dependienteDAOImplementation.Delete(dependiente.getIddependiente());
            }
        }
         Result result = empleadoDAOImplementation.Delete(numeroEmpleado);
        if (result.correct) {
            return "redirect:/empleados/lista";
        }
        return "empleadoForm";
    }

    public static String GenerarNumeroEmpleado(Empleado empleado) {
        Random rand = new Random();
        int numero = rand.nextInt(1000);
        String numeroEmpleado = (empleado.getApellidoPaterno().substring(0, 2).toUpperCase()) + (empleado.getNombre().substring(0, 2).toUpperCase()) + String.valueOf(numero);
        return numeroEmpleado;
    }

}
