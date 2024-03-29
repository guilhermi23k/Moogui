package app.moogui.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.moogui.models.Gender;
import app.moogui.models.Title;
import app.moogui.models.UserModel;
import app.moogui.repositories.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	
	public List<UserModel> findAll() {
		return repository.findAll();
	}
	
	public UserModel findById(long id) {
		Optional<UserModel> obj = repository.findById(id);
		return obj.get();
	}
	
	public UserModel findByEmail(String email) {
		Optional<UserModel> obj = repository.findByEmail(email);
		return obj.get();
	}
	
	public UserModel create(UserModel newUser) {
		return repository.save(newUser);	
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public UserModel update(Long id, UserModel obj) {
		UserModel entity = repository.getReferenceById(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(UserModel entity, UserModel obj) {
		entity.setUsername(obj.getUsername());
		entity.setEmail(obj.getEmail());
		entity.setPassword(obj.getPassword());
	}
	public void addFavTitles(Title t, String email) {
		Optional<UserModel> obj = repository.findByEmail(email);
		obj.get().addFavTitle(t);
	}
	
	public void addFavGenders(List<Gender> gen, String email) {
		Optional<UserModel> obj = repository.findByEmail(email);
	    gen.forEach(g -> {
	    	obj.get().addFavGender(g);
		});
	}
	
	
}

