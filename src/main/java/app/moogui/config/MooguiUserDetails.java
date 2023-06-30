package app.moogui.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.moogui.models.UserModel;
import app.moogui.repositories.UserRepository;

@Service
public class MooguiUserDetails implements UserDetailsService{
	
	@Autowired
    private UserRepository userRepo;

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password = null;
        List<GrantedAuthority> authorities = null;
        Optional<UserModel> userO = userRepo.findByEmail(username);
        
        //convert from optional to List to use List methods
        List<UserModel> user = userO.map(Collections::singletonList)
                .orElse(Collections.emptyList());
        
        if (user.size() == 0) {
            throw new UsernameNotFoundException("UserModel details not found for the user : " + username);
        } else{
            userName = user.get(0).getEmail();
            password = user.get(0).getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.get(0).getRole()));
        }
        return new User(userName,password,authorities);
    }
	
}
