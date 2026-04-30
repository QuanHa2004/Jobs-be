package com.example.API.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompanySalaryResponse {
    private List<CompanySalary> data;
}
