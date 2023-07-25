package app.moogui.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_gender")
public class Gender implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	@ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
	
	
	
	public Gender() {
	}
	
	public Gender(Long id, String tipo) {
		this.id = id;
		this.type = tipo;
	}

	public Gender(String tipo) {
		this.type = tipo;
	}

	public String getType() {
		return type;
	}

	public void setType(String tipo) {
		this.type = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gender other = (Gender) obj;
		return Objects.equals(id, other.id);
	}

	
	
	

}
