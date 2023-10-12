/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author digis
 */
@Controller
public class LoginController {
    @GetMapping("/")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "Login";
	}
        
    @GetMapping("/suma")
	public String suma(@RequestParam(name="n1", required=true) int n1,@RequestParam(name="n2")int n2, Model model) {
            
                int resultado=n1+n2;
		model.addAttribute("resultado", resultado);
		return "suma";
	}
}
