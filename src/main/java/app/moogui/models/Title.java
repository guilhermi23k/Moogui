package app.moogui.models;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name="tb_titles")
public class Title{
	
	@Id
	private String id;
	@Column(length=9000)
	private String json;
	@ManyToMany(mappedBy = "favTitles")
    private List<UserModel> user;
	
	public Title() {}

	public Title(String id, String json) {
		this.id = id;
		this.json = json;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	

	@Override
	public String toString() {
		return "Title [id=" + id + ", json=" + json + ", user=" + user + "]";
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
		Title other = (Title) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

	
	
}
