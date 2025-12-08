package com.jsfcourse.movie;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import com.jsf.dao.MovieDAO;
import com.jsf.entities.Movie;

@Named
@ViewScoped
public class MovieEditBB implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String PAGE_MOVIE_LIST = "/pages/admin/movieEdit?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private Movie movie = new Movie();
    private Movie loaded = null;

    @EJB
    private MovieDAO movieDAO;

    public Movie getMovie() {
        return movie;
    }

    private FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    private Flash getFlash() {
        return getFacesContext().getExternalContext().getFlash();
    }

    public void onLoad() throws IOException {
        loaded = (Movie) getFlash().get("movie");

        if (loaded != null) {
            movie = loaded;
        } else {
            getFacesContext().addMessage(null, new FacesMessage(
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
            getFacesContext().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "An error occurred while saving the movie.",
                    null));
            return PAGE_STAY_AT_THE_SAME;
        }

        return PAGE_MOVIE_LIST;
    }
}
