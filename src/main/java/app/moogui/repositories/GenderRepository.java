package app.moogui.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.moogui.models.Gender;
import app.moogui.models.Title;

public interface GenderRepository extends JpaRepository<Gender, Long>{
	Optional<Gender>findById(Long id);
}
