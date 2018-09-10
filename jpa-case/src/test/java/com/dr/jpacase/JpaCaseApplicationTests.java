package com.dr.jpacase;

import com.dr.jpacase.entity.User;
import com.dr.jpacase.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JpaCaseApplicationTests {
    @Autowired
    private UserRepository userRepository;

    /**
     * 动态sql
     */
    @Test
    public void dynamicSql() {
        Specification<User> specification = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("userId"), 1));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Sort sort = new Sort(Sort.Direction.ASC, "userId");
        List<User> list = userRepository.findAll(specification, sort);
        list.forEach(u -> log.info(u.toString()));
    }

}
