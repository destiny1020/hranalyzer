package com.destiny1020.hranalyzer.rest.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.destiny1020.hranalyzer.domain.org.Division;

@RepositoryRestResource(collectionResourceRel = "data", path = "division")
public interface DivisionRepository extends CrudRepository<Division, Long> {

}
