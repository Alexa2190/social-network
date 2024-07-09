package ru.phoenixflame.socialnetwork.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.phoenixflame.socialnetwork.api.dto.Gender;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_info")
@NoArgsConstructor
@Data
public class UserEntity {
    @Id
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "city")
    private String city;
    @Column(name = "biography")
    private String biography;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
