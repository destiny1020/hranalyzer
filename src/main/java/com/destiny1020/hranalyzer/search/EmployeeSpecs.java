package com.destiny1020.hranalyzer.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.destiny1020.hranalyzer.domain.Employee;
import com.destiny1020.hranalyzer.domain.Employee_;
import com.destiny1020.hranalyzer.domain.Record;
import com.destiny1020.hranalyzer.domain.Record_;
import com.destiny1020.hranalyzer.search.dto.EmployeeSearchDto;

public class EmployeeSpecs {

    public static Specification<Employee> searchByDto(
            final EmployeeSearchDto searchDto) {
        return new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {

                Predicate condition = cb.conjunction();

                Join<Employee, Record> recordJoined = root
                        .join(Employee_.currentRecord);

                // handle division
                Integer division = searchDto.getDivision();
                if (division != null) {
                    condition = cb.and(condition, cb.equal(
                            recordJoined.get(Record_.division), division));
                }

                // handle department
                Integer department = searchDto.getDepartment();
                if (department != null) {
                    condition = cb.and(condition, cb.equal(
                            recordJoined.get(Record_.department), department));
                }

                // handle group
                Integer group = searchDto.getGroup();
                if (department != null) {
                    condition = cb.and(condition,
                            cb.equal(recordJoined.get(Record_.group), group));
                }

                // handle department
                Integer team = searchDto.getTeam();
                if (department != null) {
                    condition = cb.and(condition,
                            cb.equal(recordJoined.get(Record_.team), team));
                }

                return condition;
            }
        };
    }

}
