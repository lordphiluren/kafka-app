package ru.sushchenko.metricsconsumer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "metrics")
@AllArgsConstructor
@NoArgsConstructor
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "base_unit")
    private String baseUnit;
    @OneToMany(mappedBy = "metric", fetch = FetchType.LAZY)
    private Set<MetricValues> metricValues;
}
