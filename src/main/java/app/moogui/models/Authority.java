package app.moogui.models;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name="tb_authorities")
public class Authority {
	 	@Id
	    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	    @GenericGenerator(name = "native",strategy = "native")
	    private Long id;
	    private String name;
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private UserModel user;
	    
	    
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public UserModel getUser() {
	        return user;
	    }

	    public void setUser(UserModel user) {
	        this.user = user;
	    }
}
