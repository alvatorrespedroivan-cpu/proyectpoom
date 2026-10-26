package com.iga.Concesionario.motos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    private String motocicleta;

    @Column(name = "tipo_moto")
    private String tipoMoto;

    @Column(name = "precio_oferta")
    private BigDecimal precioOferta;

    private String garantia;
    private String disponibilidad;
    private Integer stock;

    // Getters y Setters
    public Long getIdProducto() { return idProducto; }
    public void setIdProducto(Long idProducto) { this.idProducto = idProducto; }

    public String getMotocicleta() { return motocicleta; }
    public void setMotocicleta(String motocicleta) { this.motocicleta = motocicleta; }

    public String getTipoMoto() { return tipoMoto; }
    public void setTipoMoto(String tipoMoto) { this.tipoMoto = tipoMoto; }

    public BigDecimal getPrecioOferta() { return precioOferta; }
    public void setPrecioOferta(BigDecimal precioOferta) { this.precioOferta = precioOferta; }

    public String getGarantia() { return garantia; }
    public void setGarantia(String garantia) { this.garantia = garantia; }

    public String getDisponibilidad() { return disponibilidad; }
    public void setDisponibilidad(String disponibilidad) { this.disponibilidad = disponibilidad; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}