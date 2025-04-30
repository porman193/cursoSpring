package com.example.and.market.persistence;

import com.example.and.market.domain.Purchase;
import com.example.and.market.domain.repository.PurchaseRepository;
import com.example.and.market.persistence.crud.CompraCrudRepositroy;
import com.example.and.market.persistence.entity.Compra;
import com.example.and.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepositroy compraCrudRepositroy;
    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAll() {
        return purchaseMapper.toPurchases((List<Compra>) compraCrudRepositroy.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepositroy.findByIdClienteOrderByFechaAsc(clientId)
                .map(compras -> purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);
        compra.getComprasProductos()
                .forEach(comprasProducto -> comprasProducto.setCompra(compra));

        return purchaseMapper.toPurchase(compraCrudRepositroy.save(compra));
    }
}
