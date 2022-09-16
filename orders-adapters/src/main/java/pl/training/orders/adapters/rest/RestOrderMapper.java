package pl.training.orders.adapters.rest;

import org.mapstruct.Mapper;
import pl.training.orders.ports.model.Order;

@Mapper(componentModel = "spring")
public interface RestOrderMapper {

    Order toPorts(OrderDto orderDto);

}
