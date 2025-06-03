package edu.kata.spring_rest.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import edu.kata.spring_rest.model.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles")  // Загружаем роли сразу
    List<User> findAllWithRoles();
    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findByUsername(String username);
}