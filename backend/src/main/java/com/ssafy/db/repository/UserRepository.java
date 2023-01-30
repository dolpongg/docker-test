package com.ssafy.db.repository;

import com.ssafy.api.dto.UserEmailPwDto;
import com.ssafy.db.entity.user.Auth;
import com.ssafy.db.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 아래와 같이, Query Method 인터페이스(반환값, 메소드명, 인자) 정의를 하면 자동으로 Query Method 구현됨.
    Optional<User> findByAuth(String userEmail);

    @Query("SELECT a FROM Auth a JOIN a.user u WHERE u.name = :name AND a.email = :email")
    Optional<Auth> findUserByEmailAndName(String email, String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Auth set password = :password where email = :email")
    void updatePassword(String email, String password);
}