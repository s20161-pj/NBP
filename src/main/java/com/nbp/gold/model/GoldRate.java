package com.nbp.gold.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name="gold_rate")
public class GoldRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MetalType metalType;
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalDateTime createdAt;
    private double average;

    public GoldRate() {

    }

    public GoldRate(Long id, MetalType metalType, LocalDate fromDate, LocalDate toDate, LocalDateTime createdAt, double average) {
        this.id = id;
        this.metalType = metalType;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdAt = createdAt;
        this.average = average;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MetalType getMetalType() {
        return metalType;
    }

    public void setMetalType(MetalType metalType) {
        this.metalType = metalType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
