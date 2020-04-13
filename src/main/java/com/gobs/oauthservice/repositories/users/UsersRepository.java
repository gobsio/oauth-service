package com.gobs.oauthservice.repositories.users;

import java.util.Optional;
import java.util.Set;
import java.math.BigInteger;

import com.gobs.oauthservice.domain.entities.Authority;
import com.gobs.oauthservice.domain.entities.User;
import com.gobs.oauthservice.domain.entities.authorities.UserAuthority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// @formatter:off
@Repository
public interface UsersRepository extends JpaRepository<User, BigInteger> {

    @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    Optional<User> findByEmail(@Param("email") String email);

    @Query(value = "SELECT ua FROM UserAuthority ua WHERE ua.username = :username")
    Set<UserAuthority> getUserAuthoritiesByUsername(@Param("username") String username);

}
