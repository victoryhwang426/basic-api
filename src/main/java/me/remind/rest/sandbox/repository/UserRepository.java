package me.remind.rest.sandbox.repository;

import me.remind.rest.sandbox.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
