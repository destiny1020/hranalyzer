package com.destiny1020.hranalyzer.rest.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.destiny1020.hranalyzer.domain.Employee;

@RepositoryRestResource(path = "employee")
public interface EmployeeRepository extends
        PagingAndSortingRepository<Employee, Long> {

}
