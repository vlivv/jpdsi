package com.jsfcourse.movie;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsf.dao.MovieDAO;
import com.jsf.entities.Movie;

@Named
@ViewScoped
public class MovieEditBB implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String PAGE_MOVIE_LIST = "movieList?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private Movie movie = new Movie();
    private Movie loaded = null;

    @EJB
    private MovieDAO movieDAO;

    @Inject
    private FacesContext context;

    @Inject
    private Flash flash;

    public Movie getMovie() {
        return movie;
    }

    public void onLoad() throws IOException {
        loaded = (Movie) flash.get("movie");

        if (loaded != null) {
            movie = loaded;
        } else {
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Invalid system usage",
                    null));
        }
    }

    public String saveData() {
        if (loaded == null) {
            return PAGE_STAY_AT_THE_SAME;
        }

        try {
            if (movie.getMovieId() == null) {
                movieDAO.create(movie);
            } else {
                movieDAO.merge(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "An error occurred while saving the movie.",
                    null));
            return PAGE_STAY_AT_THE_SAME;
        }

        return PAGE_MOVIE_LIST;
    }
}
