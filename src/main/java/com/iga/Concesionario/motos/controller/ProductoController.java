package com.iga.Concesionario.motos.controller;

import com.iga.Concesionario.motos.model.Producto;
import com.iga.Concesionario.motos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepo;

    // 1. Listar todos los productos y preparar objeto vacío para registrar (Administrador)
    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> listaProductos = productoRepo.findAll();
        model.addAttribute("productos", listaProductos);
        model.addAttribute("producto", new Producto());
        return "productos";
    }

    // 2. Vista exclusiva para clientes (Solo lectura y compra, sin opciones de admin)
    @GetMapping("/catalogo")
    public String verCatalogoCliente(Model model) {
        List<Producto> listaProductos = productoRepo.findAll();
        model.addAttribute("productos", listaProductos);
        return "catalogo-cliente";
    }

    // 3. Guardar (sirve tanto para crear uno nuevo como para actualizar uno existente)
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoRepo.save(producto);
        return "redirect:/productos";
    }

    // 4. Editar: Cargar los datos de la moto seleccionada en el formulario para modificarla
    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable("id") Long id, Model model) {
        Optional<Producto> productoOpt = productoRepo.findById(id);
        if (productoOpt.isPresent()) {
            model.addAttribute("producto", productoOpt.get());
            model.addAttribute("productos", productoRepo.findAll());
            return "productos";
        }
        return "redirect:/productos";
    }

    // 5. Eliminar una motocicleta de la base de datos
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long id) {
        productoRepo.deleteById(id);
        return "redirect:/productos";
    }
}