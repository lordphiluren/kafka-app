package ru.sushchenko.metrics_producer.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sushchenko.metrics_producer.service.KafkaMessagingService;
import ru.sushchenko.metrics_producer.service.MetricsService;

@RestController
@RequestMapping("/api/v1/metrics")
@RequiredArgsConstructor
@Tag(name = "Metrics", description = "Controller for sending metrics to Apache Kafka")
public class MetricsController {
    private final MetricsService metricsService;
    private final KafkaMessagingService kafkaMessagingService;
    @Operation(
            summary = "Send metrics",
            description = "Collects all Gauge metrics of this app and send them to Apache Kafka"
    )
    @SecurityRequirement(name = "JWT")
    @PostMapping()
    public ResponseEntity<?> sendMetrics() {
        return ResponseEntity.ok(kafkaMessagingService.sendMetrics(metricsService.getGaugeMetrics()));
    }
}
