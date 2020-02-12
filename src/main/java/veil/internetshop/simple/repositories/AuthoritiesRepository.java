package veil.internetshop.simple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import veil.internetshop.simple.models.Authority;

import java.util.Optional;

public interface AuthoritiesRepository extends JpaRepository<Authority, Long>{

	Optional<Authority> findById(Long id);
}

