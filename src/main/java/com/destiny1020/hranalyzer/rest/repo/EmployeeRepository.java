package com.destiny1020.hranalyzer.rest.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.destiny1020.hranalyzer.domain.Employee;

@RepositoryRestResource(collectionResourceRel = "data", path = "employee")
public interface EmployeeRepository extends
        PagingAndSortingRepository<Employee, Long>,
        JpaSpecificationExecutor<Employee> {

}
