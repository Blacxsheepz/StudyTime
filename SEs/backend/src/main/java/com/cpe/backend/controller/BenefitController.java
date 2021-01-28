package com.cpe.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.cpe.backend.entity.Benefit;
import com.cpe.backend.repository.BenefitRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BenefitController {

    @Autowired
    private final BenefitRepository benefitRepository;

    public BenefitController(BenefitRepository benefitRepository) {
        this.benefitRepository = benefitRepository;
    }

    @GetMapping("/benefit")
    public Collection<Benefit> Benefits() {
        return benefitRepository.findAll().stream().collect(Collectors.toList());
    }

}
