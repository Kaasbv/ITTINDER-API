package com.ittinder.rest.Repositories;

import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Entities.preMatch;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserById(int id);
    User getUserById(int id);

    /*
    @Query("SELECT new User (u.firstName, u.middleName, u.surname, u.email )  FROM User u LEFT JOIN preMatch p order by function('RAND') ")
    List<User> findRandomUsers(PageRequest of);

     */

/*
    @Query("Select u from User u where u in ( select p.affectedUser from preMatch p ) or u in (select p.initiatedUser from preMatch p where p.affectedUser.id = :userId) ")
    List<User> findRandomUsers(PageRequest of);


 */
    @Query (value = "select * from `user`" +  
        "left join pre_match on (pre_match.affected_user_id = :id or pre_match.initiated_user_id = :id) and (pre_match.affected_user_id = user.id or pre_match.initiated_user_id = user.id)" +
        "where" +  
            "(pre_match.pre_match_id is null OR " + 
            "(pre_match.changed_date is null or DATEDIFF(CURDATE(), pre_match.changed_date) > 3))" +
        "and user.id != :id", nativeQuery = true)
    List<User> findRandomUsers(@Param("id") Integer id, Pageable pageable);

    /*
    @Query ("Select u, p from User u left join fetch u.preMatch p ")
    List<User> findRandomUsers(PageRequest of);
    */


}
