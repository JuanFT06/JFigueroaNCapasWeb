/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.restcontroller;

import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.Result;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.UsuarioDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author digis
 */
@RestController
@RequestMapping("api/usuarios")
public class UsuarioRestController {

    private UsuarioDAOImplementation usuarioDAOImplementation;

    public UsuarioRestController(UsuarioDAOImplementation usuarioDAOImplementation) {
        this.usuarioDAOImplementation = usuarioDAOImplementation;
    }

    @GetMapping("all")
    public ResponseEntity<List<Usuario>> GetAll(@RequestBody Usuario usuario) {

        Result resultUsuarios = usuarioDAOImplementation.GetAll(usuario);

        if (resultUsuarios.correct) {
            List<Usuario> usuarios = new ArrayList<>();
            for (Object object : resultUsuarios.objects) {
                usuarios.add((Usuario) object);
            }
            return ResponseEntity.ok(usuarios);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocurrio un error al encontrar los usuarios\n");

    }

    @GetMapping("usuario/{idusuario}")
    public ResponseEntity<Usuario> GetById(@PathVariable int idusuario) {
        Result responeUsuario = usuarioDAOImplementation.GetById(idusuario);
        if (responeUsuario.correct) {
            Usuario usuario = (Usuario) responeUsuario.object;
            return ResponseEntity.ok(usuario);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro al usuario\n");
        }

    }

    @PostMapping("/crear")
    public ResponseEntity<String> Add(@RequestBody Usuario usuario) {
        Result resultUsuario = usuarioDAOImplementation.Add(usuario);
        if (resultUsuario.correct) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el usuario");
        }

    }

    @PutMapping("/actualizar/{idusuario}")
    public ResponseEntity<String> Update(@RequestBody Usuario usuario, @PathVariable int idusuario) {
        usuario.setIdusuario(idusuario);
        Result resultUsuario = usuarioDAOImplementation.Update(usuario);
        if (resultUsuario.correct) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario actualizado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el usuario");
        }

    }

    @DeleteMapping("/eliminar/{idusuario}")
    public ResponseEntity<String> Delete(@PathVariable int idusuario) {
        Result resultUsuario = usuarioDAOImplementation.Delete(idusuario);
        if (resultUsuario.correct) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar el usuario");
        }
    }
}
