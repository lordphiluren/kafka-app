package ru.sushchenko.metricsconsumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sushchenko.metricsconsumer.dto.MeterDto;
import ru.sushchenko.metricsconsumer.entity.Metric;
import ru.sushchenko.metricsconsumer.entity.MetricValues;
import ru.sushchenko.metricsconsumer.repo.MetricRepo;
import ru.sushchenko.metricsconsumer.repo.MetricValuesRepo;
import ru.sushchenko.metricsconsumer.utils.exception.EntityNotFoundException;
import ru.sushchenko.metricsconsumer.utils.mapper.MetricMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@RequiredArgsConstructor
public class MetricsService {
    private final MetricRepo metricRepo;
    private final MetricMapper metricMapper;
    private final MetricValuesRepo metricValuesRepo;

    @Transactional
    public List<Metric> getAllMetrics() {
        return metricRepo.findAll();
    }
    @Transactional
    public Metric getMetricById(Long id) {
        return metricRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Metric with id - " + id + " wasn't found"));
    }
    @Transactional
    public List<Metric> addMetrics(List<MeterDto> meterDtos) {
        List<Metric> metrics = new ArrayList<>();
        List<MetricValues> metricValues = new ArrayList<>();
        for (MeterDto meterDto : meterDtos) {
            Metric metric = createOrUpdateMetric(meterDto);
            metricValues.add(enrichMetricValues(metric, meterDto.getValue()));
            metrics.add(metric);
        }
        List<MetricValues> savedMetricValues = metricValuesRepo.saveAll(metricValues);
        log.info("Total metrics saved: {}, with total saved values: {}", metrics.size(), savedMetricValues.size());
        return metrics;
    }

    private Metric createOrUpdateMetric(MeterDto meterDto) {
        Optional<Metric> existingMetric = metricRepo.findByName(meterDto.getName());
        return existingMetric.orElseGet(() -> metricRepo.save(metricMapper.toEntity(meterDto)));
    }
    private MetricValues enrichMetricValues(Metric metric, double value) {
        MetricValues metricValue = new MetricValues();
        metricValue.setMetric(metric);
        metricValue.setValue(value);
        return metricValue;
    }
}
