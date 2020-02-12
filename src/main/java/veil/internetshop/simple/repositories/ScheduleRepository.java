package veil.internetshop.simple.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import veil.internetshop.simple.models.Schedule;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, JpaSpecificationExecutor<Schedule>{


	@Override
	List<Schedule> findAll(Specification<Schedule> spec);
}
