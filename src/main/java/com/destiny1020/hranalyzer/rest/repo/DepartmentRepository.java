package com.destiny1020.hranalyzer.rest.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.destiny1020.hranalyzer.domain.org.Department;

@RepositoryRestResource(collectionResourceRel = "data", path = "department")
public interface DepartmentRepository extends
        PagingAndSortingRepository<Department, Long> {

}
