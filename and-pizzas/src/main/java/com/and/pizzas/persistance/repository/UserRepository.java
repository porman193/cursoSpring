package com.and.pizzas.persistance.repository;

import com.and.pizzas.persistance.entity.UserEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<UserEntity,String> {
    Optional<UserEntity> findByUserName(String name);
}
