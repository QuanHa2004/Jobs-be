package com.example.API.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Job {
    @JsonProperty("job_id")
    private String jobId;

    @JsonProperty("job_title")
    private String jobTitle;

    @JsonProperty("employer_name")
    private String employerName;

    @JsonProperty("job_description")
    private String jobDescription;

    @JsonProperty("job_apply_link")
    private String jobApplyLink;

    @JsonProperty("job_city")
    private String jobCity;

    @JsonProperty("job_country")
    private String jobCountry;

    @JsonProperty("job_posted_at_datetime_utc")
    private String jobPostedAt;
}
