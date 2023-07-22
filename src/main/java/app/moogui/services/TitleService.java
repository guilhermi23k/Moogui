package app.moogui.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import app.moogui.models.Title;
import app.moogui.repositories.TitleRepository;

@Service
public class TitleService {
	
	@Autowired
	private TitleRepository repo;
	
	public List<Title> findAll(){
		return repo.findAll();
	}
	
//	public Title findByName(String name) {
//		Optional<Title> obj = repo.findByName(name);
//		return obj.get();
//	}
	public Title findById(String id) {
		Optional<Title> obj = repo.findById(id);
		return obj.get();
	}
	
	public Title create(Title title) { 
		return repo.save(title);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
//	public Title update(Long id, Title obj) {
//		Title entity = repo.getReferenceById(id);
//		updateData(entity, obj);
//		return repo.save(entity);
//	}
//	
//	private void updateData(Title entity, Title obj) {
//		entity.setName(obj.getName());
//		entity.setGender(obj.getGender());
//		entity.setDuration(obj.getDuration());
//		entity.setRating(obj.getRating());
//	}
	
}	
