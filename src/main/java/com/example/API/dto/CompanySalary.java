package com.example.API.dto;

import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CompanySalary {
    private String jobTitle;
    private Double minSalary;
    private Double maxSalary;
    private Double medianSalary;
    private String salaryPeriod;
    private String salaryCurrency;
}