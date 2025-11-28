package com.jsfcourse.movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    private String title;
    private String genre;
    private String director;

    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @EJB
    MovieDAO movieDAO;
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getGenre() {
	    return genre;
	}

	public void setGenre(String genre) {
	    this.genre = genre;
	}

	public String getDirector() {
	    return director;
	}

	public void setDirector(String director) {
	    this.director = director;
	}


    public List<Movie> getFullList() {
        return movieDAO.getFullList();
    }
    
    
    public List<Movie> getList(){
		List<Movie> list = null;
		
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (title != null && title.length() > 0){
			searchParams.put("title", title);
		}
		
		if (genre != null && genre.length() > 0) {
		    searchParams.put("genre", genre);
		}
		
		if (director != null && director.length() > 0) {
		    searchParams.put("director", director);
		}

		
		list = movieDAO.getList(searchParams);
		
		return list;
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
