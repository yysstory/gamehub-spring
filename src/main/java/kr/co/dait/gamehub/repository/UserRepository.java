package kr.co.dait.gamehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.dait.gamehub.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUserEmail(String userEmail);
}
