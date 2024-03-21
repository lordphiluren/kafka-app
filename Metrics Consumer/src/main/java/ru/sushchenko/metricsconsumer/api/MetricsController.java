package ru.sushchenko.metricsconsumer.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sushchenko.metricsconsumer.dto.MetricResponse;
import ru.sushchenko.metricsconsumer.service.MetricsService;
import ru.sushchenko.metricsconsumer.utils.mapper.MetricMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/metrics")
@RequiredArgsConstructor
@Tag(name = "Metrics", description = "Controller for manipulating metrics")
public class MetricsController {
    private final MetricsService metricsService;
    private final MetricMapper metricMapper;
    @Operation(
            summary = "Get all metrics"
    )
    @GetMapping()
    public List<MetricResponse> getAll() {
        return metricsService.getAllMetrics().stream()
                .map(metricMapper::toDto)
                .collect(Collectors.toList());
    }
    @Operation(
            summary = "Get metric by id"
    )
    @GetMapping("/{id}")
    public MetricResponse getById(@PathVariable Long id) {
        return metricMapper.toDto(metricsService.getMetricById(id));
    }
}
