package ru.phoenixflame.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.phoenixflame.socialnetwork.entity.AuthEntity;


@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, String> {
    @Query(value = """ 
                   SELECT * FROM auth_table WHERE user_id=?1 AND token=?2 LIMIT 1
                   """, nativeQuery = true)
    AuthEntity findByUserIdAndToken(String userId, String token);

    @Query(value = """ 
                   SELECT * FROM auth_table WHERE user_id=?1 AND password=?2 LIMIT 1
                   """, nativeQuery = true)
    AuthEntity findByUserIdAndPassword(String userId, String password);

    @Query(value = """ 
                   INSERT INTO auth_table (user_id, password, token)
                   VALUES (?1, ?2, ?3) RETURNING *
                   """, nativeQuery = true)
    AuthEntity addToken(String userId, String password, String token);
}
