package pl.training.orders.domain;

import lombok.RequiredArgsConstructor;
import pl.training.orders.ports.input.PlaceOrderUseCase;
import pl.training.orders.ports.model.Order;

import static lombok.AccessLevel.PACKAGE;

@RequiredArgsConstructor(access = PACKAGE)
class PlaceOrderUseCaseAdapter implements PlaceOrderUseCase {

    private final PlaceOrderService placeOrderService;
    private final OrderDomainMapper mapper;

    @Override
    public void place(Order order) {
        var orderDomain = mapper.toDomain(order);
        placeOrderService.process(orderDomain);
    }

}
