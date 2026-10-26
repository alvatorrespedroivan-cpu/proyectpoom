package com.iga.Concesionario.motos.controller;

import com.iga.Concesionario.motos.model.Usuario;
import com.iga.Concesionario.motos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    // 1. Listar usuarios y preparar formulario de registro
    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> listaUsuarios = usuarioRepo.findAll();
        model.addAttribute("usuarios", listaUsuarios);
        model.addAttribute("usuarioObj", new Usuario());
        return "usuarios";
    }


    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        return "redirect:/usuarios";
    }

    // Guardar o actualizar usuario
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuarioObj") Usuario usuario) {
        // Por defecto si se registra solo, le asignamos rol USUARIO si no trae nada
        if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
            usuario.setRol("USUARIO");
        }
        usuarioRepo.save(usuario);
        return "redirect:/usuarios";
    }

    //  Eliminar usuario
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id) {
        usuarioRepo.deleteById(id);
        return "redirect:/usuarios";
    }
}