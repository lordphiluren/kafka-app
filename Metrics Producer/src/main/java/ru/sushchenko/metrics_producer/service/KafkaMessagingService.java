package ru.sushchenko.metrics_producer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.sushchenko.metrics_producer.dto.MeterDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaMessagingService {
    @Value("${topic.send-metrics}")
    private String metricsTopic;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    public List<MeterDto> sendMetrics(List<MeterDto> metrics) {
        kafkaTemplate.send(metricsTopic, metrics);
        return metrics;
    }
}
