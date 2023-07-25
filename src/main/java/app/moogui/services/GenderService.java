package app.moogui.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.moogui.models.Gender;
import app.moogui.models.Title;
import app.moogui.repositories.GenderRepository;

@Service
public class GenderService {
	
	@Autowired
	GenderRepository repo;
	
	public List<Gender> findAll(){
		return repo.findAll();
	}
	
//	public Title findByName(String name) {
//		Optional<Title> obj = repo.findByName(name);
//		return obj.get();
//	}
	public Gender findById(Long id) {
		Optional<Gender> obj = repo.findById(id);
		return obj.get();
	}
	
	public Gender create(Gender gender) { 
		return repo.save(gender);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
