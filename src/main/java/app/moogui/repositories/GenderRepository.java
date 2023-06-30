package app.moogui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.moogui.models.Gender;

public interface GenderRepository extends JpaRepository<Gender, Long>{
	
}
