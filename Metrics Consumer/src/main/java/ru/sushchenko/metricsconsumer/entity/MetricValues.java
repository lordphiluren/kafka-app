package ru.sushchenko.metricsconsumer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "metric_values")
@NoArgsConstructor
@AllArgsConstructor
public class MetricValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "value")
    private double value;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metric_id", referencedColumnName = "id")
    private Metric metric;
}
