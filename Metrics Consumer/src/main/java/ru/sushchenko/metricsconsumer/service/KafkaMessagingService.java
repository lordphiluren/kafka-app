package ru.sushchenko.metricsconsumer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.sushchenko.metricsconsumer.dto.MeterDto;

import java.util.List;

@Slf4j
@Service
@Configuration
@RequiredArgsConstructor
public class KafkaMessagingService {
    private final static String metricsTopic = "${topic.send-metrics}";
    private final static String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";

    private final MetricsService metricsService;

    @Transactional
    @KafkaListener(topics = metricsTopic, groupId = kafkaConsumerGroupId)
    public List<MeterDto> createOrder(List<MeterDto> meterDtos) {
        log.info("Message consumed with {} metrics", meterDtos.size());
        metricsService.addMetrics(meterDtos);
        return meterDtos;
    }
}
