package pl.training.shop.payments.adapters.persistence.jpa;

import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;
import pl.training.shop.payments.domain.Payment;
import pl.training.shop.payments.ports.PaymentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Transactional
public class JpaPaymentRepository implements PaymentRepository {

    @PersistenceContext
    @Setter
    private EntityManager entityManager;

    @Override
    public Payment save(Payment payment) {
        entityManager.persist(payment);
        return payment;
    }

    @Override
    public Optional<Payment> getById(String id) {
        return Optional.ofNullable(entityManager.find(Payment.class, id));
    }

}
