package com.jsfcourse.movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.inject.Named;

import com.jsf.dao.MovieDAO;
import com.jsf.entities.Movie;

@Named
@RequestScoped
public class MovieListBB {

	private static final String PAGE_MAIN = "/pages/user/movieList?faces-redirect=true";
	private static final String PAGE_MOVIE_EDIT = "/pages/admin/movieEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
    

    private String title;
    private String genre;
    private String director;

    @EJB
    private MovieDAO movieDAO;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public List<Movie> getFullList() {
        return movieDAO.getFullList();
    }

    public List<Movie> getList() {
        Map<String, Object> searchParams = new HashMap<>();

        if (title != null && !title.isBlank()) {
            searchParams.put("title", title);
        }
        if (genre != null && !genre.isBlank()) {
            searchParams.put("genre", genre);
        }
        if (director != null && !director.isBlank()) {
            searchParams.put("director", director);
        }

        return movieDAO.getList(searchParams);
    }

    public String newMovie() {
        Movie movie = new Movie();
        getFlash().put("movie", movie);
        return PAGE_MOVIE_EDIT;
    }

    public String editMovie(Movie movie) {
        getFlash().put("movie", movie);
        return PAGE_MOVIE_EDIT;
    }

    public String deleteMovie(Movie movie) {
        movieDAO.remove(movie);
        return PAGE_STAY_AT_THE_SAME;
    }

    private Flash getFlash() {
        return FacesContext.getCurrentInstance().getExternalContext().getFlash();
    }
}
