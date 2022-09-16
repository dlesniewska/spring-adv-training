package pl.training.orders.ports.input;

import pl.training.orders.ports.model.Order;

public interface PlaceOrderUseCase {

    void place(Order order);

}
