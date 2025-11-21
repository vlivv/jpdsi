package com.jsfcourse.movie;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;

import com.jsf.dao.MovieDAO;
import com.jsf.entities.Movie;

@Named
@RequestScoped
public class MovieListBB {

    private static final String PAGE_MOVIE_EDIT = "movieEdit?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @EJB
    MovieDAO movieDAO;

    public List<Movie> getFullList() {
        return movieDAO.getFullList();
    }

    public String newMovie() {
        Movie movie = new Movie();
        flash.put("movie", movie);
        return PAGE_MOVIE_EDIT;
    }

    public String editMovie(Movie movie) {
        flash.put("movie", movie);
        return PAGE_MOVIE_EDIT;
    }

   
    public String deleteMovie(Movie movie) {
        movieDAO.remove(movie);
        return PAGE_STAY_AT_THE_SAME;
    }
}
