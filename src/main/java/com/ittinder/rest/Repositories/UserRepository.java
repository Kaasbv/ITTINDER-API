package com.ittinder.rest.Repositories;

import com.ittinder.rest.Entities.User;
import com.ittinder.rest.Entities.preMatch;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import org.springframework.data.repository.query.Param;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Date;


public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findByEmailIgnoreCase(String email);

  User getUserById(Long id);

  List<User> findUserById(int id);


  @Query (value = "select * from `user`" +
        "left join pre_match on (pre_match.affected_user_id = :id or pre_match.initiated_user_id = :id) and (pre_match.affected_user_id = user.id or pre_match.initiated_user_id = user.id)" +
        "where" +
            "(pre_match.pre_match_id is null OR " +
            "(pre_match.changed_date is null or DATEDIFF(CURDATE(), pre_match.changed_date) > 3))" +
        "and user.id != :id", nativeQuery = true)
  List<User> findRandomUsers(@Param("id") Integer id, Pageable pageable);

}
