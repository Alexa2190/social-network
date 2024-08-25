package ru.phoenixflame.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.phoenixflame.socialnetwork.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    @Query(value = """ 
                   SELECT * FROM user_info WHERE id=UUID(?1) LIMIT 1
                   """, nativeQuery = true)
    Optional<UserEntity> findUserById(String id);

    @Query(value = """ 
                   INSERT INTO user_info (first_name, second_name, biography, gender, birth_date, city)
                   VALUES (?1, ?2, ?3, ?4, ?5, ?6) RETURNING *
                   """, nativeQuery = true)
    UserEntity addUser(String firstName, String secondName, String biography, String gender, LocalDate birthDate, String city);


    @Query(value = """ 
                   SELECT * FROM user_info WHERE LOWER(first_name) LIKE CONCAT(LOWER(?1), '%') and LOWER(second_name) LIKE CONCAT(LOWER(?2), '%') ORDER BY id DESC
                   """, nativeQuery = true)
    List<UserEntity> findUsersByFistNameAndSecondName(String firstName, String secondName);

}
