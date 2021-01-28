package com.cpe.backend.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import com.cpe.backend.entity.Company;
import com.cpe.backend.entity.Position;
import com.cpe.backend.entity.JobPost;
import com.cpe.backend.entity.Benefit;

import com.cpe.backend.repository.CompanyRepository;
import com.cpe.backend.repository.JobPostRepository;
import com.cpe.backend.repository.PositionRepository;
import com.cpe.backend.repository.BenefitRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class JobPostController {
    @Autowired
    private final JobPostRepository jobPostRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private BenefitRepository benefitRepository;

    JobPostController(JobPostRepository jobPostRepository) {
        this.jobPostRepository = jobPostRepository;
    }

    @GetMapping("/jobPost")
    public Collection<JobPost> JobPosts() {
        return jobPostRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/jobPost/{company_id}/{position_id}/{benefit_id}/{educationlevel}/{salary}/{Other_details}")
    public JobPost newJobPost(JobPost newJobPost,
    @PathVariable String Other_details,
    @PathVariable String educationlevel,
    @PathVariable String salary,
    @PathVariable long company_id,
    @PathVariable long position_id,
    @PathVariable long benefit_id){


    Position position = positionRepository.findById(position_id);
    Company company = companyRepository.findById(company_id);
    Benefit benefit = benefitRepository.findById(benefit_id);
  
    newJobPost.setOther(Other_details);
    newJobPost.setEducationlevel(educationlevel);
    newJobPost.setSalary(salary);
    newJobPost.setCompany(company);
    newJobPost.setBenefit(benefit);
    newJobPost.setPosition(position);

    return jobPostRepository.save(newJobPost);

    }
}
