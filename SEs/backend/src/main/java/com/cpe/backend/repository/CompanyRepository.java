package com.cpe.backend.repository;

import com.cpe.backend.entity.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface CompanyRepository extends JpaRepository<Company, Long> {
	Company findById(long id);
}