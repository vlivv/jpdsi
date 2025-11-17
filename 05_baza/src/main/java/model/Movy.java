package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the movies database table.
 * 
 */
@Entity
@Table(name="movies")
@NamedQuery(name="Movy.findAll", query="SELECT m FROM Movy m")
public class Movy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="movie_id")
	private int movieId;

	@Column(name="age_rating")
	private String ageRating;

	@Lob
	private String description;

	private String director;

	@Column(name="duration_min")
	private int durationMin;

	private String genre;

	private String language;

	private String title;

	//bi-directional many-to-one association to Screening
	@OneToMany(mappedBy="movy")
	private List<Screening> screenings;

	public Movy() {
	}

	public int getMovieId() {
		return this.movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getAgeRating() {
		return this.ageRating;
	}

	public void setAgeRating(String ageRating) {
		this.ageRating = ageRating;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDirector() {
		return this.director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getDurationMin() {
		return this.durationMin;
	}

	public void setDurationMin(int durationMin) {
		this.durationMin = durationMin;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Screening> getScreenings() {
		return this.screenings;
	}

	public void setScreenings(List<Screening> screenings) {
		this.screenings = screenings;
	}

	public Screening addScreening(Screening screening) {
		getScreenings().add(screening);
		screening.setMovy(this);

		return screening;
	}

	public Screening removeScreening(Screening screening) {
		getScreenings().remove(screening);
		screening.setMovy(null);

		return screening;
	}

}