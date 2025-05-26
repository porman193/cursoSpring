package com.and.pizzas.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "user_name", nullable = false, length = 80)
    private String userName;

    @Column(name = "account_disabled", nullable = false)
    private Boolean accountDisabled = false;

    @Column(name = "account_locked", nullable = false)
    private Boolean accountLocked = false;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER)
    private List<UserRole> roles;

}