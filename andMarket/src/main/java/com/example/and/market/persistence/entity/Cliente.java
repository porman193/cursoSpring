package com.example.and.market.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @SequenceGenerator(name = "clientes_id_gen", sequenceName = "categorias_id_categoria_seq", allocationSize = 1)
    @Column(name = "id", nullable = false, length = 20)
    private String id;

    @Column(name = "nombre", length = 40)
    private String nombre;

    @Column(name = "apellidos", length = 100)
    private String apellidos;

    @Column(name = "celular")
    private BigDecimal celular;

    @Column(name = "direccion", length = 80)
    private String direccion;

    @Column(name = "correo_electronico", length = 70)
    private String correoElectronico;

    @OneToMany(mappedBy = "idCliente")
    List<Compra> compras;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public BigDecimal getCelular() {
        return celular;
    }

    public void setCelular(BigDecimal celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

}