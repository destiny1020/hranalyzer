package com.destiny1020.hranalyzer.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
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
                if (division != null && division > 0) {
                    condition = cb.and(condition, cb.equal(
                            recordJoined.get(Record_.division), division));
                }

                // handle department
                Integer department = searchDto.getDepartment();
                if (department != null && department > 0) {
                    condition = cb.and(condition, cb.equal(
                            recordJoined.get(Record_.department), department));
                }

                // handle group
                Integer group = searchDto.getGroup();
                if (group != null && group > 0) {
                    condition = cb.and(condition,
                            cb.equal(recordJoined.get(Record_.group), group));
                }

                // handle team
                Integer team = searchDto.getTeam();
                if (team != null && team > 0) {
                    condition = cb.and(condition,
                            cb.equal(recordJoined.get(Record_.team), team));
                }

                // handle employee id
                String employeeId = searchDto.getEmployeeId();
                if (StringUtils.isNotEmpty(employeeId)) {
                    Predicate likeEmployeeId = cb.like(root.get(Employee_.eid),
                            assembleLikePattern(employeeId));

                    condition = cb.and(condition, likeEmployeeId);
                }

                // handle employee name
                String employeeName = searchDto.getName();
                if (StringUtils.isNotEmpty(employeeName)) {
                    Predicate likeLastName = cb.like(
                            root.get(Employee_.lastName),
                            assembleLikePattern(employeeName));

                    Predicate likeLastName2 = cb.like(
                            root.get(Employee_.lastName2),
                            assembleLikePattern(employeeName));

                    Predicate likeFirstName = cb.like(
                            root.get(Employee_.firstName),
                            assembleLikePattern(employeeName));

                    Predicate likeFirstName2 = cb.like(
                            root.get(Employee_.firstName2),
                            assembleLikePattern(employeeName));

                    condition = cb.and(condition, cb.or(likeFirstName,
                            likeFirstName2, likeLastName, likeLastName2));
                }

                // handle employee title class
                Integer titleClass = searchDto.getTitleClass();
                if (titleClass != null && titleClass > 0) {
                    condition = cb.and(condition, cb.equal(
                            recordJoined.get(Record_.titleClass), titleClass));
                }

                // handle employee title rank
                Integer titleRank = searchDto.getTitleRank();
                if (titleRank != null && titleRank > 0) {
                    condition = cb.and(condition, cb.equal(
                            recordJoined.get(Record_.titleRank), titleRank));
                }

                return condition;
            }
        };
    }

    private static String assembleLikePattern(String key) {
        return String.format("%%%s%%", key);
    }

}
