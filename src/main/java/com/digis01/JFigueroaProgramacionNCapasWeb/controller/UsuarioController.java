/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.JFigueroaProgramacionNCapasWeb.controller;

import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.ColoniaDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.DireccionDAOImpplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.EstadoDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.MunicipioDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.PaisDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.Result;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.RolDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.DAO.UsuarioDAOImplementation;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Colonia;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Direccion;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Estado;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Municipio;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Pais;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Rol;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.Usuario;
import com.digis01.JFigueroaProgramacionNCapasWeb.JPA.UsuarioDireccion;
import com.digis01.JFigueroaProgramacionNCapasWeb.util.Util;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author digis
 */
@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioDAOImplementation usuarioDAOImplementation;
    private RolDAOImplementation rolDAOImplementation;
    private DireccionDAOImpplementation direccionDAOImpplementation;
    private PaisDAOImplementation paisDAOImplementation;
    private EstadoDAOImplementation estadoDAOImplementation;
    private MunicipioDAOImplementation municipioDAOImplementation;
    private ColoniaDAOImplementation coloniaDAOImplementation;

    @Autowired
    public UsuarioController(UsuarioDAOImplementation usuarioDAOImplementation,
            RolDAOImplementation rolDAOImplementation,
            DireccionDAOImpplementation direccionDAOImpplementation,
            PaisDAOImplementation paisDAOImplementation,
            EstadoDAOImplementation estadoDAOImplementation,
            MunicipioDAOImplementation municipioDAOImplementation,
            ColoniaDAOImplementation coloniaDAOImplementation) {
        this.usuarioDAOImplementation = usuarioDAOImplementation;
        this.rolDAOImplementation = rolDAOImplementation;
        this.direccionDAOImpplementation = direccionDAOImpplementation;
        this.paisDAOImplementation = paisDAOImplementation;
        this.estadoDAOImplementation = estadoDAOImplementation;
        this.municipioDAOImplementation = municipioDAOImplementation;
        this.coloniaDAOImplementation = coloniaDAOImplementation;

    }

    @GetMapping("/lista")
    public String GetAll(Model model, HttpSession session) {

        if (session.getAttribute("usuario") != null) {
            Usuario usuario = new Usuario();
            usuario.setNombre("");
            usuario.setApellido_paterno("");
            usuario.setApellido_materno("");
            Result resultd = direccionDAOImpplementation.GetAll();
            List<Direccion> direcciones = new ArrayList<>();
            for (Object objeto : resultd.objects) {
                direcciones.add((Direccion) objeto);
            }
            Result result = usuarioDAOImplementation.GetAll(usuario);

            if (result.correct) {
                List<Usuario> usuarios = new ArrayList<>();
                for (Object objeto : result.objects) {
                    usuarios.add((Usuario) objeto);
                }
                model.addAttribute("direcciones", direcciones);
                model.addAttribute("usuarios", usuarios);
                model.addAttribute("usuariobusqueda", new Usuario());

                return "listaUsuarios";
            }
            return "listaUsuarios";
        }else{
            return "redirect:/";
        }

    }

    @PostMapping("/lista")
    public String GetAll(Model model, @ModelAttribute("usuariobusqueda") Usuario usuarioBusqueda) {

        Result resultd = direccionDAOImpplementation.GetAll();
        List<Direccion> direcciones = new ArrayList<>();
        for (Object objeto : resultd.objects) {
            direcciones.add((Direccion) objeto);
        }
        Result result = usuarioDAOImplementation.GetAll(usuarioBusqueda);

        if (result.correct) {
            List<Usuario> usuarios = new ArrayList<>();
            for (Object objeto : result.objects) {
                usuarios.add((Usuario) objeto);
            }
            model.addAttribute("direcciones", direcciones);
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("usuariobusqueda", usuarioBusqueda);

            return "listaUsuarios";
        }
        return "listaUsuarios";

    }

    @GetMapping("/form/{idusuario}")
    public String mostrarFormulario(@PathVariable("idusuario") int id, Model model) {
        Result result = rolDAOImplementation.GetAll(); // Obtiene todos los roles disponibles
        if (result.correct) {
            List<Rol> roles = new ArrayList<>();
            for (Object objeto : result.objects) {
                roles.add((Rol) objeto);
            }
            //Paises
            Result resultPaises = paisDAOImplementation.GetAll();
            List<Pais> paises = new ArrayList<>();
            for (Object objeto : resultPaises.objects) {
                paises.add((Pais) objeto);
            }
            if (id == 0) {//Add
                model.addAttribute("roles", roles);
                model.addAttribute("paises", paises);
                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                model.addAttribute(usuarioDireccion);
                return "usuarioForm";
            } else {//Update
                //mandamos todos los roles
                model.addAttribute("roles", roles);
                //mandamos todos los paises
                model.addAttribute("paises", paises);
                //recuperamos el usuario y lo asignamos a usuarioDireccion
                Result result2 = usuarioDAOImplementation.GetById(id);
                UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
                usuarioDireccion.setUsuario((Usuario) result2.object);
                //obtenemos la direccion y la agregamos a usuarioDireccion

                Result direccion = direccionDAOImpplementation.GetByUser(id);
                if (direccion.correct) {
                    usuarioDireccion.setDireccion((Direccion) direccion.object);
                    //agregar estados
                    model.addAttribute("estados", estadoDAOImplementation.GetByIdPais(usuarioDireccion.getDireccion().getColonia().getMunicipio().getEstado().getPais().getIdpais()));
                    model.addAttribute("municipios", municipioDAOImplementation.GetByIdEstado(usuarioDireccion.getDireccion().getColonia().getMunicipio().getEstado().getIdestado()));
                    model.addAttribute("colonias", coloniaDAOImplementation.GetByIdMunicipio(usuarioDireccion.getDireccion().getColonia().getMunicipio().getIdmunicipio()));
                } else {
                    usuarioDireccion.setDireccion(new Direccion());
                }

                model.addAttribute("usuarioDireccion", usuarioDireccion);
                return "usuarioForm";
            }
        }
        return "usuarioForm";
    }

    @PostMapping("/form")
    public String guardar(@Valid @ModelAttribute("usuarioDireccion") UsuarioDireccion usuarioDireccion,
            BindingResult bindingResult, Model model, @RequestParam("imageFile") MultipartFile imageFile) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("usuarioDireccion", usuarioDireccion);
            return "usuarioForm";
        }
        String imagen;

        if (!imageFile.isEmpty()) {
            imagen = Util.code64(imageFile);
        } else {
            imagen = usuarioDireccion.getUsuario().getImagen();
        }

        if (usuarioDireccion.getUsuario().getIdusuario() > 0) {//update
            usuarioDireccion.getUsuario().setImagen(imagen);
            //actualiza datos del usuario
            Result result = usuarioDAOImplementation.Update(usuarioDireccion.getUsuario());
            usuarioDireccion.getDireccion().setUsuario(usuarioDireccion.getUsuario());
            Result result2 = direccionDAOImpplementation.Update(usuarioDireccion.getDireccion());
            if (result.correct && result2.correct) {
                return "redirect:/usuarios/lista";
            }
        } else {//add
            //ingresar la imagen y estatus del usuario
            usuarioDireccion.getUsuario().setImagen(imagen);
            usuarioDireccion.getUsuario().setStatus(1);
            //agregar usuario
            Result result = usuarioDAOImplementation.Add(usuarioDireccion.getUsuario());
            usuarioDireccion.getDireccion().setUsuario((Usuario) result.object);
            //agregar direccion
            Result result2 = direccionDAOImpplementation.Add(usuarioDireccion.getDireccion());
            if (result.correct && result2.correct) {
                return "redirect:/usuarios/lista";
            }
        }

        return "usuarioForm";
    }

    @PostMapping("/delete/{idusuario}")
    public String deleteUsuerio(@PathVariable("idusuario") int idusuario) {
        Result result = usuarioDAOImplementation.Delete(idusuario);

        if (result.correct) {
            return "redirect:/usuarios/lista";
        }
        return "usuarioForm";
    }

    @GetMapping("/getEstadoByIdPais")
    @ResponseBody
    public List<Estado> getEstados(@RequestParam("idpais") int idpais) {
        //Paises
        List<Estado> estados = estadoDAOImplementation.GetByIdPais(idpais);
        return estados;

    }

    @GetMapping("/getMunicipioByIdEstado")
    @ResponseBody
    public List<Municipio> getMunicipios(@RequestParam("idestado") int idestado) {
        //Paises
        List<Municipio> municipios = municipioDAOImplementation.GetByIdEstado(idestado);
        return municipios;

    }

    @GetMapping("/getColoniaByIdMunicipio")
    @ResponseBody
    public List<Colonia> getColonias(@RequestParam("idmunicipio") int idmunicipio) {
        //Paises
        List<Colonia> colonias = coloniaDAOImplementation.GetByIdMunicipio(idmunicipio);
        return colonias;

    }

    @PostMapping("/cambiarEstadoUsuario")
    @ResponseBody
    public void cambiarEstado(@RequestParam("idusuario") int idUsuario) {
        Result result = usuarioDAOImplementation.GetById(idUsuario);
        Usuario usuario = (Usuario) result.object;
        int status = usuario.getStatus();
        usuario.setStatus(status > 0 ? 0 : 1);
        usuarioDAOImplementation.Update(usuario);

    }

}
