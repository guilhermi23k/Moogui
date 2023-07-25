package app.moogui.models;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.SpringSecurityCoreVersion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name="tb_user")
public class UserModel implements Serializable{
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	
//	Limit of movies/series user can use in the gpt request
	private static final int titleLimit = 10;
	
//	limit of genders user can use in the gpt request
	private static final int genderLimit = 20;
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
	private Long user_id;
	private String username;
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String role;
	
	@JsonIgnore
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private Set<Authority> authorities;
	
//	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonIgnore
//	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	@ManyToMany
	@JoinTable(
			name = "tb_user_title",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "title_id")
			)
	private List<Title> favTitles;
	
//	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonIgnore
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Gender> favGenders;
	
	
	public UserModel(){
		
	}
	
	public UserModel(Long user_id, String username, String email, String password, String role) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
 

	public Long getId() {
		return user_id;
	}

	public void setId(Long user_id) {
		this.user_id = user_id;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	
	public Set<Authority> getAuthorities(){
		return authorities;
	}
	
	public List<Title> getFavTitles() {
		return favTitles;
	}
	
	public List<String> getFavTitlesName() {
		List<String> titles = new ArrayList();
		favTitles.forEach(t -> {
			titles.add(t.getName());
		});
		return titles;
	}
	
	public void setFavTitles(List<Title> favTitles) {
		this.favTitles = favTitles;
	}

	public void addFavTitle(Title favTitle) {
		ArrayDeque<Title> org = new ArrayDeque<>();	
		
		org.addAll(favTitles);
		if(org.size() + 1 <= titleLimit) {
			org.add(favTitle);			
		}else {
			org.removeFirst();
			org.add(favTitle);
		}
		setFavTitles((List<Title>) org);
	}

	public List<Gender> getFavGenders() {
		return favGenders;
	}
	public List<String> getFavGendersName() {
		List<String> genders = new ArrayList<String>();
		favGenders.forEach(t -> {
			genders.add(t.toString());
		});
		return genders;
	}
	
	public void setFavGenders(List<Gender> favGenders) {
		this.favGenders = favGenders;
	}

	public void addFavGender(Gender favGender) {
		ArrayDeque<Gender> org = new ArrayDeque<>();
		org.addAll(this.favGenders);
		if(org.size() + 1 <= genderLimit) {
			org.add(favGender);			
		}else {
			org.removeFirst();	 
			org.add(favGender);		
		}
		
		
		setFavGenders((List<Gender>) org);
	}
	
	



	@Override
	public int hashCode() {
		return Objects.hash(user_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel other = (UserModel) obj;
		return Objects.equals(user_id, other.user_id);
	}
	
	

}
