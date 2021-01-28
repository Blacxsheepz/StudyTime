package com.cpe.backend;

import com.cpe.backend.entity.JobPost;
import com.cpe.backend.repository.JobPostRepository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.context.annotation.Import;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class JobPostTests {

    private Validator validator;

    @Autowired
    private JobPostRepository JobPostRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    void b6020156_testSalaryNotnull() {
        JobPost j1 = new JobPost();
        j1.setSalary(null);
        j1.setEducationlevel("PhD");
        j1.setOther("บุคลิกภาพดี");

        Set<ConstraintViolation<JobPost>> result = validator.validate(j1);
        assertEquals(1, result.size());

        ConstraintViolation<JobPost> v = result.iterator().next();

        assertEquals("must not be null", v.getMessage());
        assertEquals("salary", v.getPropertyPath().toString());
    }
    @Test
    void b6020156_testeducationlevelName() {
        JobPost j2 = new JobPost();
        j2.setEducationlevel("Ph/D");
        j2.setSalary("12000บาท");
        j2.setOther("บุคลิกภาพดี");
        Set<ConstraintViolation<JobPost>> result = validator.validate(j2);
        assertEquals(1, result.size());

        ConstraintViolation<JobPost> v = result.iterator().next();

        assertEquals("must match \"^[A-z0-9]*$\"", v.getMessage());
        assertEquals("educationlevel", v.getPropertyPath().toString());
    }
    @Test
    void b6020156_testSalaryMustLessEqual10() {
        JobPost j3 = new JobPost();
        j3.setSalary("100000000000000000000");
        j3.setEducationlevel("PhD");
        j3.setOther("บุคลิกภาพดี");
     Set<ConstraintViolation<JobPost>> result = validator.validate(j3);
        assertEquals(1, result.size());

        ConstraintViolation<JobPost> v = result.iterator().next();
        assertEquals("size must be between 1 and 10",  v.getMessage());
        assertEquals("salary", v.getPropertyPath().toString());
    }
    @Test
    void b6020156_testSuccess() {
        JobPost j4 = new JobPost();
        j4.setEducationlevel("PhD");
        j4.setSalary("160000บาท");
        j4.setOther("หล่อ");

        j4 = JobPostRepository.saveAndFlush(j4);
 
        Optional<JobPost> found = JobPostRepository.findById(j4.getId());
        assertEquals("PhD", found.get().getEducationlevel());
        assertEquals("160000บาท", found.get().getSalary());
        assertEquals("หล่อ", found.get().getOther_details());
    }

    }