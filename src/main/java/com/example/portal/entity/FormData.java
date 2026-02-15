package com.example.portal.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "form_data")
@Data
public class FormData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "applying_for_position")
    private String applyingForPosition;

    private String state;

    private String city;

    private String location;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private Integer age;

    private String gender;

    @Column(name = "total_experience")
    private String totalExperience;

    @Column(name = "relevant_experience")
    private String relevantExperience;

    @Column(name = "current_salary")
    private String currentSalary;

    @Column(name = "expected_salary")
    private String expectedSalary;

    private String qualification;

    @Column(name = "notice_period")
    private String noticePeriod;

    @Column(name = "resume_link")
    private String resumeLink;
}
