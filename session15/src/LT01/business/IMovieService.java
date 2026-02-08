package LT01.business;

import LT01.model.Movie;

import java.util.List;

public interface IMovieService extends IBaseService<Movie, String>{
    void displayAllMovies();
    List<Movie> findAllByTitle(String title);
    List<Movie> findAllByRating(double rating);
    boolean isExistById(String id);
}
