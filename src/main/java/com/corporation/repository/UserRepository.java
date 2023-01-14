package com.corporation.repository;

import com.corporation.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Modifying
    @Query("update User u set u.avatarUrl = :avatarUrl where u.id = :id")
    void updateUserAvatarById(long id, String avatarUrl);

    Optional<User> findByNickname(String nickname);
    Page<User> findByNicknameContainingIgnoreCase(String query, Pageable pageable);
    Optional<User> findByNicknameOrEmail(String nickname, String email);
    @Query(value = "SELECT u.id FROM User u WHERE u.id IN (:followerId, :followeeId)")
    List<Long> findFollowerAndFolloweeByIds(long followerId, long followeeId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "INSERT INTO followers (follower_id, followee_id)\n" +
            "VALUES (:followerId, :followeeId)")
    void followUser(long followerId, long followeeId);
}
