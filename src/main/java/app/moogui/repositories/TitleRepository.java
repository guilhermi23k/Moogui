package app.moogui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.moogui.models.Titles;

public interface TitleRepository extends JpaRepository<Titles, Long>{
	
}
