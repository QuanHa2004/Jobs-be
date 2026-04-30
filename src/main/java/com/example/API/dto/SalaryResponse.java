package com.example.API.dto;

import lombok.Data;

import java.util.List;

@Data
public class SalaryResponse {
    private List<Salary> data;
}