package app.moogui.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.moogui.models.Title;
import app.moogui.models.UserModel;

public interface UserRepository  extends JpaRepository<UserModel, Long>{
	Optional<UserModel>findByEmail(String email);
	
}
