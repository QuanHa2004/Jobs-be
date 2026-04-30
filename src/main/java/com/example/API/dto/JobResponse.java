package com.example.API.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class JobResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("request_id")
    private String request_id;

    private List<Job> data;
}
