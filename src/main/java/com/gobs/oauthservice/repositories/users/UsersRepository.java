package com.gobs.oauthservice.repositories.users;

import java.util.Optional;
import java.util.Set;
import java.math.BigInteger;

import com.gobs.oauthservice.domain.entities.Authority;
import com.gobs.oauthservice.domain.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// @formatter:off
@Repository
public interface UsersRepository extends JpaRepository<User, BigInteger> {

    @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)")
    Optional<User> findByUsername(@Param("username") String username);

    @Query(value = "SELECT a.name FROM users_authorities ua INNER JOIN authorities a ON (a.name = ua.fk_authorities_id) WHERE ua.fk_users_id = :id", nativeQuery = true)
    Set<Authority> fetchAuthoritiesById(@Param("id") BigInteger id);

}
