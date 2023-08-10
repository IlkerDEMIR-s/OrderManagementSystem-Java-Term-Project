package me.anant.OMS.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import me.anant.OMS.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	Users findByEmail(String email);
	boolean existsByEmail(String email);
}
