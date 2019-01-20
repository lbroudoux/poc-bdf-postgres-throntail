package fr.bdf.exa.poc.service;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.opentracing.Traced;

@Traced
@ApplicationScoped
public class ServiceBean {

    public String method() {
        return ServiceBean.class.getSimpleName() + ".method";
    }

    @Timeout(1000)
    @Retry(maxRetries = 2)
    @Fallback(fallbackMethod = "doSomethingElse")
    public String doFallback() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        return "Success!";
    }

    public String doSomethingElse() {
        return "Fallback";
    }

    AtomicBoolean flag = new AtomicBoolean(true);

    @Retry(retryOn = IOException.class)
    public String retry() throws IOException {
        if (flag.compareAndSet(true, false)) {
            throw new IOException();
        }

        return "Success!";

    }

    private AtomicInteger counter = new AtomicInteger(0);

    @CircuitBreaker(requestVolumeThreshold = 2, failureRatio = 0.5,
            successThreshold = 2)
    public String circuitBreaker() throws IOException {
        if (2 == counter.incrementAndGet()) {
            throw new IOException();
        }

        return "circuit is closed!";

    }

    @Bulkhead(2)
    public String bulkhead() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
        }
        return "Success!";

    }
}
