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
    @Query ("Select u from User u left join u.preMatchAsAffected pa left join u.preMatchAsInitiated pi " +
            "where ((pa.initiatedUser.id <> :id or pi.affectedUser.id <> :id) or (pa.preMatchId is null or pi.preMatchId is null)) ")
             //"where ((pa.initiatedUser.id = :id and pa.preMatchId is null) or (pi.affectedUser.id = :id and pi.preMatchId is null)) ")
            //"where pa.initiatedUser.id <> :id or pi.affectedUser.id <> :id")
            //"or (((FUNCTION('DATEDIFF', current_date, pa.ChangedDate ) > 3 and FUNCTION('DATEDIFF',current_date ,pi.ChangedDate ) is null)" +
            //"or (FUNCTION('DATEDIFF', current_date, pi.ChangedDate ) > 3 and FUNCTION('DATEDIFF',current_date ,pa.ChangedDate ) is null))" +
            //"and (pa.initiatedUser.id = :id or pi.affectedUser.id = :id) "  +
            //"and u.id <> :id)")
    List<User> findRandomUsers(@Param("id") Integer id, Pageable pageable);

    /*
    @Query ("Select u, p from User u left join fetch u.preMatch p ")
    List<User> findRandomUsers(PageRequest of);
    */


}
