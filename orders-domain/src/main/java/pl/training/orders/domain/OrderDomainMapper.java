package pl.training.orders.domain;

import lombok.Setter;
import org.javamoney.moneta.FastMoney;
import org.mapstruct.Mapper;
import pl.training.orders.ports.model.Order;
import pl.training.orders.ports.output.ProductsProvider;

import java.util.List;
import java.util.Map;

@Mapper
abstract class OrderDomainMapper {

    @Setter
    private ProductsProvider productsProvider;

    OrderDomain toDomain(Order order) {
        return new OrderDomain(order.getId(), toDomain(order.getEntries()));
    }


    private List<OrderEntryDomain> toDomain(Map<Long, Integer> entries) {
        return entries.entrySet().stream()
                .map(entry -> new OrderEntryDomain(entry.getKey(), getProductPrice(entry.getKey()), entry.getValue()))
                .toList();
    }

    private FastMoney getProductPrice(Long productId) {
        var price = productsProvider.getById(productId)
                .orElseThrow(IllegalArgumentException::new)
                .getPrice();
        return FastMoney.parse(price);
    }

}
