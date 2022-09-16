package pl.training.orders.ports.output;

import pl.training.orders.ports.model.Product;

import java.util.Optional;

public interface ProductsProvider {

    Optional<Product> getById(Long id);

}
