package com.destiny1020.hranalyzer.rest.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.destiny1020.hranalyzer.domain.rank.TitleClass;

@RepositoryRestResource(collectionResourceRel = "data", path = "titleclass")
public interface TitleClassRepository extends CrudRepository<TitleClass, Long> {

}
