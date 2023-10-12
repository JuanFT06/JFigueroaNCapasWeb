/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.restcontroller;

import com.digis01.JFigueroaProgramacionNCapasWeb.entity.Datos;
import com.digis01.JFigueroaProgramacionNCapasWeb.entity.Respuesta;
import com.digis01.JFigueroaProgramacionNCapasWeb.service.RespuestaService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digis
 */
@RestController
@RequestMapping("/api")
public class DemoController {
    
    @GetMapping("/hola")
    public Map<String,Object> saludo(@RequestParam("nombre")String nombre){
        Map<String,Object> result=new HashMap<>();
        result.put("Hola", nombre);
        return result;
    }
    
    @PostMapping("/suma")
    public ResponseEntity<Respuesta> suma(@RequestBody Datos datos){
        RespuestaService service= new RespuestaService();
        return  ResponseEntity.ok(service.suma(datos));
        
    }
    @PostMapping("/resta")
    public ResponseEntity<Respuesta> resta(@RequestBody Datos datos){
        RespuestaService service= new RespuestaService();
        return  ResponseEntity.ok(service.resta(datos));
        
    }
    @PostMapping("/multiplicacion")
    public ResponseEntity<Respuesta> multiplicacion(@RequestBody Datos datos){
        RespuestaService service= new RespuestaService();
        return  ResponseEntity.ok(service.multiplicacion(datos));
        
    }
    @PostMapping("/division")
    public ResponseEntity<Respuesta> division(@RequestBody Datos datos){
        RespuestaService service= new RespuestaService();
        return  ResponseEntity.ok(service.division(datos));
        
    }
}
