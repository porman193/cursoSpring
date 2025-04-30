package com.example.and.market.persistence.mapper;

import com.example.and.market.domain.Purchase;
import com.example.and.market.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring",uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {
    @Mappings(
            {
                    @Mapping(source = "id", target = "id"),
                    @Mapping(source = "idCliente", target = "clientId"),
                    @Mapping(source = "fecha", target = "purchaseDate"),
                    @Mapping(source = "medioPago", target = "paymentMethod"),
                    @Mapping(source = "comentario", target = "comment"),
                    @Mapping(source = "estado", target = "state"),
                    @Mapping(source = "comprasProductos", target = "items")
            }
    )
    Purchase toPurchase(Compra compra);
    List<Purchase> toPurchases(List<Compra> compras);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    Compra toCompra(Purchase purchase);
}
