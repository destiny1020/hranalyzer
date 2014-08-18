package com.destiny1020.hranalyzer.rest.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.destiny1020.hranalyzer.domain.org.Team;

@RepositoryRestResource(collectionResourceRel = "data", path = "team")
public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {

}
