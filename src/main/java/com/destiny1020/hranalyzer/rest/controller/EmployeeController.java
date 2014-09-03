package com.destiny1020.hranalyzer.rest.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.destiny1020.hranalyzer.domain.Employee;
import com.destiny1020.hranalyzer.rest.repo.EmployeeRepository;
import com.destiny1020.hranalyzer.rest.response.PagePackage;
import com.destiny1020.hranalyzer.search.EmployeeSpecs;
import com.destiny1020.hranalyzer.search.dto.EmployeeSearchDto;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOGGER = LogManager
            .getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepo;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody PagePackage<Employee> searchExecutionRecord(
            EmployeeSearchDto searchDto) {
        LOGGER.info(String.format("Search for employees by criteria: %s",
                searchDto.toString()));

        Page<Employee> page = employeeRepo.findAll(EmployeeSpecs
                .searchByDto(searchDto), new PageRequest(searchDto.getPage(),
                searchDto.getSize(), Direction.fromString("asc"), "eid"));

        return new PagePackage<Employee>(page);
    }

}
