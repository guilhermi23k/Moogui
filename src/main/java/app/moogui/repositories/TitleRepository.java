package app.moogui.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.moogui.models.Title;

public interface TitleRepository extends JpaRepository<Title, Long>{
//	Optional<Title>findByName(String name);
	Optional<Title>findById(String id);
	
}
