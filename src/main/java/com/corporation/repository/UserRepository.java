package com.corporation.repository;

import com.corporation.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bleschunov Dmitry
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Modifying
    @Query("update User u set u.avatarUrl = :avatarUrl where u.id = :id")
    void updateUserAvatarById(long id, String avatarUrl);
}
