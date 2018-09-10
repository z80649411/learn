package com.dr.jpacase.repository;


import com.dr.jpacase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 邓仁波
 * Date: 2018-05-02
 * Time: 16:56
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>
        , JpaSpecificationExecutor<User> {
    User findByUserId(long userId);
}