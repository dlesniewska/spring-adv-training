package pl.training.orders.adapters.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.training.commons.money.Money;
import pl.training.orders.ports.model.Payment;
import pl.training.orders.ports.output.PaymentService;
import pl.training.payments.ports.input.ProcessPaymentUseCase;
import pl.training.payments.ports.model.PaymentRequest;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentServiceAdapter implements PaymentService {

    public final ProcessPaymentUseCase processPaymentUseCase;

    @Override
    public Optional<Payment> pay(Long requestId, BigDecimal value, String currency, Map<String, String> properties) {
        var paymentRequest = new PaymentRequest(requestId, value + Money.CURRENCY_SEPARATOR + currency);
        var payment = processPaymentUseCase.process(paymentRequest);
        return Optional.of(new Payment(payment.getId(), payment.getStatus().name()));
    }

}
