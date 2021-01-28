package com.cpe.backend.repository;

import com.cpe.backend.entity.JobPost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface JobPostRepository extends JpaRepository<JobPost, Long> {
    JobPost findById(long id);
}
