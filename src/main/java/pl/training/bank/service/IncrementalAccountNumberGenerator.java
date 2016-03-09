package pl.training.bank.service;

import java.util.concurrent.atomic.AtomicLong;

@Generator
public class IncrementalAccountNumberGenerator implements AccountNumberGenerator {

    private AtomicLong counter = new AtomicLong();

    @Override
    public String getNext() {
        return String.format("%026d", counter.incrementAndGet());
    }

}
