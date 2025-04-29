package com.example.and.market.persistence.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "compras")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compras_id_gen")
    @SequenceGenerator(name = "compras_id_gen", sequenceName = "compras_id_compra_seq", allocationSize = 1)
    @Column(name = "id_compra", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false ,updatable = false,insertable = false)
    private Cliente idCliente;

    @Column(name = "fecha")
    private Instant fecha;

    @Column(name = "medio_pago", length = Integer.MAX_VALUE)
    private String medioPago;

    @Column(name = "comentario", length = 300)
    private String comentario;

    @Column(name = "estado", length = Integer.MAX_VALUE)
    private String estado;

    @OneToMany(mappedBy = "idCompra")
    private List<ComprasProducto> comprasProductos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}