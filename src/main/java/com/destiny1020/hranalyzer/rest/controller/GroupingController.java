package com.destiny1020.hranalyzer.rest.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.destiny1020.hranalyzer.domain.org.Group;
import com.destiny1020.hranalyzer.rest.repo.GroupRepository;
import com.destiny1020.hranalyzer.rest.response.PagePackage;
import com.destiny1020.hranalyzer.search.GroupSpecs;
import com.destiny1020.hranalyzer.search.dto.GroupSearchDto;

@Controller
@RequestMapping("/grouping")
public class GroupingController {

    private static final Logger LOGGER = LogManager
            .getLogger(GroupingController.class);

    @Autowired
    private GroupRepository groupRepo;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody PagePackage<Group> searchExecutionRecord(
            GroupSearchDto searchDto) {
        LOGGER.info(String.format("Search for groupings by criteria: %s",
                searchDto.toString()));

        PagePackage<Group> result = null;
        if (searchDto.isPaging()) {
        } else {
            List<Group> allMatchedGroups = groupRepo.findAll(
                    GroupSpecs.searchByDto(searchDto), new Sort("id"));
            result = new PagePackage<Group>(allMatchedGroups);
        }

        return result;
    }
}
