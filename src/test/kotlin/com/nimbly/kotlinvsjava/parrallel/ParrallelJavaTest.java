package com.nimbly.kotlinvsjava.parrallel;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class ParrallelJavaTest {

    @Test
    public void testJ() throws InterruptedException {

        BillingService billingService = new BillingService();

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new InvoiceProcessor(billingService, "Apple"));
        service.submit(new InvoiceProcessor(billingService, "Samsung"));

        service.shutdown();
        service.awaitTermination(1, TimeUnit.SECONDS);

        assertEquals(billingService.getLines().size(), 5);
        assertEquals(billingService.getTotal(), 2930.0);

    }

    public static class InvoiceProcessor implements Callable<Object> {

        private BillingService billingService;
        private String customer;

        public InvoiceProcessor(BillingService billingService, String customer) {
            this.billingService = billingService;
            this.customer = customer;
        }

        @Override
        public Object call() {
            InvoiceDB.INSTANCE.load(customer).forEach(invoice -> {
                billingService.plusAssign(invoice);
            });
            return null;
        }
    }
}
