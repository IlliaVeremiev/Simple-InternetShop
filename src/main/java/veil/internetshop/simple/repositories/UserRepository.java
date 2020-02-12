package veil.internetshop.simple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import veil.internetshop.simple.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
}