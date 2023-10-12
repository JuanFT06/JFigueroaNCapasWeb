/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.controller;

import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.EmpresaDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.Result;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Empresa;
import com.digis01.JFigueroaProgramacionNCapasWeb.util.Util;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("/empresas")
public class EmpresaController {

    private EmpresaDAOImplementation empresaDAOImplementation;

    @Autowired
    public EmpresaController(EmpresaDAOImplementation empresaDAOImplementation) {
        this.empresaDAOImplementation = empresaDAOImplementation;
    }

    @GetMapping("/lista")
    public String GetAll(Model model) {
        Empresa empresa = new Empresa();
        empresa.setNombre("");
        Result result = empresaDAOImplementation.GetAll(empresa);

        if (result.correct) {
            List<Empresa> empresas = new ArrayList<>();
            for (Object objeto : result.objects) {
                empresas.add((Empresa) objeto);
            }
            model.addAttribute("empresabusqueda", new Empresa());
            model.addAttribute("empresas", empresas);

            return "empresasList";
        }
        return "empresasList";
    }
    @PostMapping("/lista")
    public String GetAll(Model model,@ModelAttribute("empresabusqueda")Empresa empresaBusqueda) {
        
        Result result = empresaDAOImplementation.GetAll(empresaBusqueda);

        if (result.correct) {
            List<Empresa> empresas = new ArrayList<>();
            for (Object objeto : result.objects) {
                empresas.add((Empresa) objeto);
            }
            model.addAttribute("empresabusqueda", empresaBusqueda);
            model.addAttribute("empresas", empresas);

            return "empresasList";
        }
        return "empresasList";
    }

    @GetMapping("/form/{idempresa}")
    public String mostrarFormulario(@PathVariable("idempresa") int id, Model model) {
        if (id == 0) {//add
            model.addAttribute(new Empresa());
        } else {//update
            // 
            Result result = empresaDAOImplementation.GetById(id);
            if (result.correct) {
                Empresa empresa = (Empresa) result.object;
                model.addAttribute("empresa", empresa);
            }
        }
        return "empresaForm";
    }

    @PostMapping("/form")
    public String guardar(@Valid @ModelAttribute("empresa") Empresa empresa, BindingResult bindingResult, Model model,
            @RequestParam("imageFile") MultipartFile imageFile) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("empresa", empresa);
            return "empresaForm";
        }
        String imagen;
        
        if (!imageFile.isEmpty()) {
            imagen = Util.code64(imageFile);
        }else{
            imagen=empresa.getLogo();
        }

        

        if (empresa.getIdempresa() == 0) {//add
            empresa.setLogo(imagen);
            Result result = empresaDAOImplementation.Add(empresa);
            if (result.correct) {
                return "redirect:/empresas/lista";
            }
        } else {//update
             empresa.setLogo(imagen);
            Result result = empresaDAOImplementation.Update(empresa);
            if (result.correct) {
                return "redirect:/empresas/lista";
            }
        }
        return "empresaForm";
    }

    @PostMapping("/delete/{idempresa}")
    public String delete(@PathVariable("idempresa") int id) {
        Result result = empresaDAOImplementation.Delete(id);

        if (result.correct) {
            return "redirect:/empresas/lista";
        }
        return "empresaForm";

    }

}
