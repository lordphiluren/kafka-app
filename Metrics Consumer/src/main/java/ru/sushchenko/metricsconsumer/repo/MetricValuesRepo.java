package ru.sushchenko.metricsconsumer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sushchenko.metricsconsumer.entity.MetricValues;

@Repository
public interface MetricValuesRepo extends JpaRepository<MetricValues, Long> {
}
