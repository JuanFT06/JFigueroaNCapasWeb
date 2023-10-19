/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.controller;

import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.Result;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.UsuarioDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author digis
 */
@Controller
public class LoginController {

    private UsuarioDAOImplementation usuarioDAOImplementation;
    
    public LoginController(UsuarioDAOImplementation usuarioDAOImplementation) {
        this.usuarioDAOImplementation = usuarioDAOImplementation;
    }
    
    @GetMapping("/")
    public String Login(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "Login";
    }

    @PostMapping("/")
    public String Login(@ModelAttribute("usuario") Usuario usuario, Model model,HttpSession session) {
        Result resultUsuario = usuarioDAOImplementation.GetByEmail(usuario.getEmail());
        if (resultUsuario.correct) {
            Usuario usuarioLogin=(Usuario) resultUsuario.object;
            if (usuario.getPassword().equals(usuarioLogin.getPassword())) {
                //creando la sesion
                session.setAttribute("usuario", usuarioLogin);
                return  "redirect:/usuarios/lista";
            }
        }else{
            
        }
        return "Login";
    }
    
    @GetMapping("/suma")
    public String suma(@RequestParam(name = "n1", required = true) int n1, @RequestParam(name = "n2") int n2, Model model) {
        
        int resultado = n1 + n2;
        model.addAttribute("resultado", resultado);
        return "suma";
    }
}
