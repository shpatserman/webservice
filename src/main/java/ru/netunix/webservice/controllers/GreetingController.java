package ru.netunix.webservice.controllers;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.prometheus.client.CollectorRegistry;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netunix.webservice.model.Greeting;

@RestController
@Log4j2
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final Counter requests_counter_with_name;
    private final Counter requests_counter_without_name;
    private MeterRegistry meterRegistry;
    private final Timer histogram_timer;

    public GreetingController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        requests_counter_with_name = Counter.builder("greeting.requests.counter").tag("name", "exists").register(meterRegistry);
        requests_counter_without_name = Counter.builder("greeting.requests.counter").tag("name", "not_exists").register(meterRegistry);
        Duration[] durations = {Duration.ofMillis(200), Duration.ofMillis(500), Duration.ofMillis(600), Duration.ofMillis(800), Duration.ofMillis(1000), Duration.ofMillis(3000)};
        histogram_timer = Timer.builder("greeting.histogram.timer")
                .publishPercentiles(0.5, 0.95) // median and 95th percentile (1)
                .publishPercentileHistogram() // (2)
                .minimumExpectedValue(Duration.ofMillis(100)) // (4)
                .maximumExpectedValue(Duration.ofSeconds(5)).register(meterRegistry);
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return histogram_timer.record(() -> {
            if (name.equals("World")) {
                requests_counter_without_name.increment();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                requests_counter_with_name.increment();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.info("HTTP /greeting request name {}",name);
            log.info("my.param = ["+System.getProperty("my.param")+"]");
            return new Greeting(counter.incrementAndGet(), String.format(template, name));
        });


    }
}