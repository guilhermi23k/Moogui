package app.moogui.models;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.security.core.SpringSecurityCoreVersion;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_titles")
public class Title{
	
	@Id
	private String id;
	private String poster;
	private String name;
	private String gender;
	private String year;
	private String plot;
	private String duration;
	private String rating;
	
	

	public Title(String id, String poster, String name, String gender, String year, String plot, String duration,
			String rating) {
		this.id = id;
		this.poster = poster;
		this.name = name;
		this.gender = gender;
		this.year = year;
		this.plot = plot;
		this.duration = duration;
		this.rating = rating;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
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
