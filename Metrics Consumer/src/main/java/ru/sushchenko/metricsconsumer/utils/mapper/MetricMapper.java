package ru.sushchenko.metricsconsumer.utils.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.sushchenko.metricsconsumer.dto.MeterDto;
import ru.sushchenko.metricsconsumer.dto.MetricResponse;
import ru.sushchenko.metricsconsumer.entity.Metric;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MetricMapper {
    private final ModelMapper mapper;
    public MetricResponse toDto(Metric metric) {
        List<Double> metricValues = new ArrayList<>();
        metric.getMetricValues().forEach(m -> metricValues.add(m.getValue()));
        MetricResponse metricDto = mapper.map(metric, MetricResponse.class);
        metricDto.setValues(metricValues);
        return metricDto;
    }
    public Metric toEntity(MeterDto meterDto) {
        return mapper.map(meterDto, Metric.class);
    }
}
