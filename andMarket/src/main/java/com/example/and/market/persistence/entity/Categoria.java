package com.example.and.market.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorias_id_gen")
    @SequenceGenerator(name = "categorias_id_gen", sequenceName = "categorias_id_categoria_seq", allocationSize = 1)
    @Column(name = "id_categoria", nullable = false)
    private Integer id;

    @Column(name = "descripcion", nullable = false, length = 45)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private Boolean estado = false;

    @OneToMany(mappedBy = "idCategoria")
    private List<Producto> productos;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}