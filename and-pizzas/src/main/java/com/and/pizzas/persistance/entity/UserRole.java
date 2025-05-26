package com.and.pizzas.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user_role")
@NoArgsConstructor
public class UserRole {
    @EmbeddedId
    private UserRoleId id;

    @MapsId("username")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username", nullable = false)
    private UserEntity userEntity;

    @Column(name = "granted_date")
    private LocalDate grantedDate;

}