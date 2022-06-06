package pl.training.payments.adapters.persistence;

import lombok.Setter;
import pl.training.payments.ports.model.Page;
import pl.training.payments.ports.model.Payment;
import pl.training.payments.ports.model.PaymentStatus;
import pl.training.payments.ports.model.ResultPage;
import pl.training.payments.ports.output.PaymentsReader;
import pl.training.payments.ports.output.PaymentsWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryPaymentRepository implements PaymentsReader, PaymentsWriter {

    @Setter
    private Map<String, Payment> payments = new HashMap<>();

    @Override
    public Payment save(Payment payment) {
        payments.put(payment.getId(), payment);
        return payment;
    }

    @Override
    public Optional<Payment> getById(String id) {
        return Optional.ofNullable(payments.get(id));
    }

    @Override
    public ResultPage<Payment> getByStatus(PaymentStatus status, Page page) {
        var data = payments.values().stream()
                .filter(payment -> payment.getStatus().equals(status))
                .toList();
        var totalPages = (long) Math.ceil((double) data.size() / page.getSize());
        return new ResultPage<>(data, page.getNumber(), totalPages);
    }

}
