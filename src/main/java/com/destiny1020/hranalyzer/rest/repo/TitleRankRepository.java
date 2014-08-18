package com.destiny1020.hranalyzer.rest.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.destiny1020.hranalyzer.domain.rank.TitleRank;

@RepositoryRestResource(collectionResourceRel = "data", path = "titlerank")
public interface TitleRankRepository extends CrudRepository<TitleRank, Long> {

}
