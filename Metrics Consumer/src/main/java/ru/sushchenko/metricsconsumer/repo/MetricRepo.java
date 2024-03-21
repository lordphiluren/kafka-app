package ru.sushchenko.metricsconsumer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sushchenko.metricsconsumer.entity.Metric;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetricRepo extends JpaRepository<Metric, Long> {
    Optional<Metric> findByName(String name);

    @Query("SELECT m FROM Metric m LEFT JOIN FETCH m.metricValues m_v")
    List<Metric> findAll();
    @Query("SELECT m FROM Metric m LEFT JOIN FETCH m.metricValues m_v")
    Optional<Metric> findById(Long id);
}
