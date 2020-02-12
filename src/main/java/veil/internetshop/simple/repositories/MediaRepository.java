package veil.internetshop.simple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import veil.internetshop.simple.models.Media;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long>{

	@Override
	<S extends Media> S save(S entity);

	@Override
	Optional<Media> findById(Long id);
}
