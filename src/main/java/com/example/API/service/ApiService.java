package com.example.API.service;

import com.example.API.dto.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApiService {
    WebClient webClient;

    public Mono<ApiResponse<JobResponse>> getJobs(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/search")
                        .queryParam("query", query)
                        .build())
                .retrieve()
                .bodyToMono(JobResponse.class)
                .map(res -> (res.getData() == null || res.getData().isEmpty())
                        ? new ApiResponse<JobResponse>("Không tìm thấy công việc", null)
                        : new ApiResponse<>("Success", res))
                .onErrorResume(this::handleError);
    }

    public Mono<ApiResponse<Job>> getJobDetails(String jobId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/job-details").queryParam("job_id", jobId).build())
                .retrieve()
                .bodyToMono(JobResponse.class)
                .map(res -> (res.getData() == null || res.getData().isEmpty())
                        ? new ApiResponse<Job>("Không tìm thấy dữ liệu", null)
                        : new ApiResponse<>("Success", res.getData().get(0)))
                .onErrorResume(this::handleError);
    }

    public Mono<ApiResponse<List<Salary>>> getJobSalary(String jobTitle, String location) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/estimated-salary")
                        .queryParam("job_title", jobTitle)
                        .queryParam("location", location).build())
                .retrieve()
                .bodyToMono(SalaryResponse.class)
                .map(res -> (res.getData() == null || res.getData().isEmpty())
                        ? new ApiResponse<List<Salary>>("Không có dữ liệu lương", Collections.emptyList())
                        : new ApiResponse<>("Success", res.getData()))
                .onErrorResume(this::handleError);
    }

    public Mono<ApiResponse<List<CompanySalary>>> getCompanySalary(String companyName, String jobTitle) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/company-job-salary")
                        .queryParam("company", companyName)
                        .queryParam("job_title", jobTitle).build())
                .retrieve()
                .bodyToMono(CompanySalaryResponse.class)
                .map(res -> (res.getData() == null || res.getData().isEmpty())
                        ? new ApiResponse<List<CompanySalary>>("Không tìm thấy lương công ty " + companyName, Collections.emptyList())
                        : new ApiResponse<>("Success", res.getData()))
                .onErrorResume(this::handleError);
    }

    private <T> Mono<ApiResponse<T>> handleError(Throwable e) {
        log.error("API Error: {}", e.getMessage());
        return Mono.just(new ApiResponse<>("Hệ thống đang gặp sự cố", null));
    }
}