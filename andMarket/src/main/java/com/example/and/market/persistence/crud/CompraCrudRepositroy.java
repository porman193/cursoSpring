package com.example.and.market.persistence.crud;
import com.example.and.market.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepositroy extends CrudRepository<Compra, Integer> {
    Optional<List<Compra>> findByIdClienteOrderByFechaAsc(String idCliente);
}
