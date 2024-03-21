package ru.sushchenko.metrics_producer.service;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sushchenko.metrics_producer.dto.MeterDto;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricsService {
    private final MeterRegistry meterRegistry;
    public List<MeterDto> getGaugeMetrics() {
        List<MeterDto> metrics = new ArrayList<>();
        meterRegistry.forEachMeter(meter -> {
            if (meter instanceof Gauge) {
                metrics.add(MeterDto.builder()
                        .name(meter.getId().getName())
                        .description(meter.getId().getDescription())
                        .baseUnit(meter.getId().getBaseUnit())
                        .value(((Gauge) meter).value())
                        .build());
            }
        });
        return metrics;
    }
}
