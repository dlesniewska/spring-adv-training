package pl.training.orders.domain;

import lombok.Value;
import org.javamoney.moneta.FastMoney;

@Value
class OrderEntryDomain {

    Long productId;
    FastMoney price;
    int quantity;

    FastMoney getTotalValue() {
        return price.multiply(quantity);
    }

}
