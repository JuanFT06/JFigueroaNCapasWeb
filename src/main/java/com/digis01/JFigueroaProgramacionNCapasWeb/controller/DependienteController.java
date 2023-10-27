/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.controller;

import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.DependienteDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.DependienteTipoDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.EmpleadoDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.Result;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Dependiente;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.DependienteTipo;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Empleado;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("/dependientes")
public class DependienteController {

    private DependienteDAOImplementation dependienteDAOImplementation;
    private DependienteTipoDAOImplementation dependienteTipoDAOImplementation;
    private EmpleadoDAOImplementation empleadoDAOImplementation;

    public DependienteController(DependienteDAOImplementation dependienteDAOImplementation, DependienteTipoDAOImplementation dependienteTipoDAOImplementation,
            EmpleadoDAOImplementation empleadoDAOImplementation) {
        this.dependienteDAOImplementation = dependienteDAOImplementation;
        this.dependienteTipoDAOImplementation = dependienteTipoDAOImplementation;
        this.empleadoDAOImplementation = empleadoDAOImplementation;
    }

    @GetMapping("/lista/{numeroempleado}")
    public String GetAll(@PathVariable("numeroempleado") String numeroEmpleado, Model model,HttpSession session) {
        
        Result resultEmpleado = empleadoDAOImplementation.GetById(numeroEmpleado);
        if (resultEmpleado.correct) {
            Empleado empleado = (Empleado) resultEmpleado.object;
            model.addAttribute("empleado", empleado);
        }
        Result resultDependiente = dependienteDAOImplementation.GetAll(numeroEmpleado);
        if (resultDependiente.correct) {
            List<Dependiente> dependientes = new ArrayList<>();
            for (Object object : resultDependiente.objects) {
                dependientes.add((Dependiente) object);
            }
            model.addAttribute("dependientes", dependientes);

        } else {
            model.addAttribute("dependientes", null);
        }
        session.setAttribute(numeroEmpleado, model);
        return "dependientesList";
    }

    @GetMapping("/form/{numeroempleado}/{iddependiente}")
    public String mostrarForm(@PathVariable("numeroempleado") String numeroempleado, @PathVariable("iddependiente") int iddependiente, Model model) {
        Result resultTipos = dependienteTipoDAOImplementation.GetAll();
        if (resultTipos.correct) {
            List<DependienteTipo> tipos = new ArrayList<>();
            for (Object object : resultTipos.objects) {
                tipos.add((DependienteTipo) object);
            }
            model.addAttribute("tipos", tipos);
            if (iddependiente == 0) {//add

                model.addAttribute("dependiente", new Dependiente());

            } else {//update
                Result resultdependiente = dependienteDAOImplementation.GetById(iddependiente);
                if (resultdependiente.correct) {
                    Dependiente dependiente = (Dependiente) resultdependiente.object;
                    model.addAttribute("dependiente", dependiente);
                }
            }
        }

        return "dependienteForm";
    }

    @PostMapping("/form/{numeroempleado}")
    public String guardar(@PathVariable("numeroempleado") String numeroempleado, @Valid @ModelAttribute("dependiente") Dependiente dependiente,
            BindingResult bindingResult,Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("dependiente",dependiente);
            return "dependienteForm";
        }
        
        
        Result resultEmpleado = empleadoDAOImplementation.GetById(numeroempleado);
        if (resultEmpleado.correct) {
            Empleado empleado = (Empleado) resultEmpleado.object;
            dependiente.setEmpleado(empleado);

            if (dependiente.getIddependiente() == 0) {//add
                Result resultDependiente = dependienteDAOImplementation.Add(dependiente);
                if (resultDependiente.correct) {
                    return "redirect:/dependientes/lista/" + numeroempleado;
                }
            } else {//update
                Result resultDependiente = dependienteDAOImplementation.Update(dependiente);
                if (resultDependiente.correct) {
                    return "redirect:/dependientes/lista/" + numeroempleado;
                }
            }

        }
        return "dependienteForm";

    }

    @PostMapping("/delete/{iddependiente}")
    public String delete(@PathVariable("iddependiente") int iddependiente,HttpSession session) {
       String numeroEmpleado= session.getAttribute("numeroEmpleado").toString();
        Result result = dependienteDAOImplementation.Delete(iddependiente);

        if (result.correct) {
            return "redirect:/dependientes/lista/"+numeroEmpleado;
        }
        return "dependienteForm";
    }
}
