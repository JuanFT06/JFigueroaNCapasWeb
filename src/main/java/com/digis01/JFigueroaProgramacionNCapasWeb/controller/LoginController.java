/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.controller;

import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.Result;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.UsuarioDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Usuario;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.UsuarioLogin;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("usuarioLogin", new UsuarioLogin());
        return "Login";
    }

    @PostMapping("/")
    public String Login(@Valid @ModelAttribute("usuarioLogin") UsuarioLogin usuarioLogin, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuarioLogin", usuarioLogin);
            return "Login";
        }
        Result resultUsuario = usuarioDAOImplementation.GetByEmail(usuarioLogin.getEmail());
        if (resultUsuario.correct) {
            Usuario usuario = (Usuario) resultUsuario.object;
            if (usuario.getPassword().equals(usuarioLogin.getPassword())) {
                //creando la sesion
                session.setAttribute("usuario", usuario);
                return "redirect:/usuarios/lista";
            } else {
                model.addAttribute("error", true);
                return "Login";
            }
        } else {
            model.addAttribute("error", true);
            return "Login";
        }   
    }

    @GetMapping("/logout")
    public String Logout(Model model, HttpSession session) {
        session.removeAttribute("usuario");
        session.removeAttribute("numeroEmpleado");
        return "redirect:/";
    }

    @GetMapping("/suma")
    public String suma(@RequestParam(name = "n1", required = true) int n1, @RequestParam(name = "n2") int n2, Model model) {

        int resultado = n1 + n2;
        model.addAttribute("resultado", resultado);
        return "suma";
    }
}
