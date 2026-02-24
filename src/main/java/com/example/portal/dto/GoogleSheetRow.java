package com.example.portal.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class GoogleSheetRow {
    private LocalDateTime timestamp;
    private String fullName;
    private String mobileNumber;
    private String emailId;
    private String applyingForPosition;
    private String state;
    private String city;
    private String location;
    private LocalDate dateOfBirth;
    private Integer age;
    private String gender;
    private String totalExperience;
    private String relevantExperience;
    private String currentSalary;
    private String expectedSalary;
    private String qualification;
    private String noticePeriod;
    private String resumeLink;
}
