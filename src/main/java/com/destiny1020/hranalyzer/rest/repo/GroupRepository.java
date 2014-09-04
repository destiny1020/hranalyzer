package com.destiny1020.hranalyzer.rest.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.destiny1020.hranalyzer.domain.org.Group;

@RepositoryRestResource(collectionResourceRel = "data", path = "group")
public interface GroupRepository extends
        PagingAndSortingRepository<Group, Long>,
        JpaSpecificationExecutor<Group> {

}
