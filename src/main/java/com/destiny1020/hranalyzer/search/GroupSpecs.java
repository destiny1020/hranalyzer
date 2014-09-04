package com.destiny1020.hranalyzer.search;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.destiny1020.hranalyzer.domain.org.Group;
import com.destiny1020.hranalyzer.domain.org.Group_;
import com.destiny1020.hranalyzer.search.dto.GroupSearchDto;

public class GroupSpecs {

    public static Specification<Group> searchByDto(
            final GroupSearchDto searchDto) {
        return new Specification<Group>() {
            @Override
            public Predicate toPredicate(Root<Group> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {

                Predicate condition = cb.conjunction();

                // handle department
                Integer department = searchDto.getDepartment();
                if (department != null) {
                    condition = cb.and(condition,
                            cb.equal(root.get(Group_.department), department));
                }

                return condition;
            }
        };
    }

}
