package com.iga.Concesionario.motos.controller;

import com.iga.Concesionario.motos.model.Usuario;
import com.iga.Concesionario.motos.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    // Esta ruta crea o actualiza el usuario admin automáticamente al abrirla con rol ADMIN
    @GetMapping("/crear-admin")
    @ResponseBody
    public String crearAdmin() {
        Usuario u = usuarioRepo.findByUsuario("admin").orElse(new Usuario());
        u.setUsuario("admin");
        u.setPassword("1234");
        u.setRol("ADMIN"); // Asignamos rol de Administrador
        usuarioRepo.save(u);
        return "¡Listo! El usuario 'admin' con rol 'ADMIN' ya fue creado.";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String validar(@RequestParam String usuario,
                          @RequestParam String password,
                          HttpSession session, // <--- Se inyecta la sesión aquí
                          Model model) {

        Optional<Usuario> optionalUser = usuarioRepo.findByUsuario(usuario);

        if (optionalUser.isPresent()) {
            Usuario u = optionalUser.get();
            if (u.getPassword().equals(password)) {

                // Guardamos el usuario completo en la sesión con la clave "usuarioLogueado"
                session.setAttribute("usuarioLogueado", u);

                // Validación de roles: ambos perfiles entran al menú principal
                if ("ADMIN".equals(u.getRol()) || "USUARIO".equals(u.getRol())) {
                    return "redirect:/"; // Ambos van al menú principal con las opciones
                }
            }
        }

        model.addAttribute("error", "Usuario o contraseña incorrectos");
        return "login";
    }

    // Ruta principal que muestra el menú visual
    @GetMapping("/")
    public String inicio() {
        return "index"; // Busca el archivo index.html en la carpeta templates
    }
}