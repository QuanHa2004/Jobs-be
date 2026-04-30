package com.example.API.controller;

import com.example.API.dto.*;
import com.example.API.service.ApiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApiController {

    ApiService apiService;

    @GetMapping("/search")
    public Mono<ApiResponse<JobResponse>> getJobs(
            @RequestParam("query") String query) {
        return apiService.getJobs(query);
    }

    @GetMapping("/details")
    public Mono<ApiResponse<Job>> getDetails(
            @RequestParam("job_id") String jobId) {
        return apiService.getJobDetails(jobId);
    }

    @GetMapping("/salary")
    public Mono<ApiResponse<List<Salary>>> getSalary(
            @RequestParam("job_title") String jobTitle,
            @RequestParam("location") String location) {
        return apiService.getJobSalary(jobTitle, location);
    }

    @GetMapping("/company-salary")
    public Mono<ApiResponse<List<CompanySalary>>> getCompanySalary(
            @RequestParam("company") String companyName,
            @RequestParam("job_title") String jobTitle) {
        return apiService.getCompanySalary(companyName, jobTitle);
    }
}