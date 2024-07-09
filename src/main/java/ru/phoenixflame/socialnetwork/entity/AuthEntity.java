package ru.phoenixflame.socialnetwork.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auth_table")
@NoArgsConstructor
@Data
public class AuthEntity {
    @Id
    private String id;
    @Column(name = "user_id")
    private String user_id;
    @Column(name = "password")
    private String password;
    @Column(name = "token")
    private String token;
}
