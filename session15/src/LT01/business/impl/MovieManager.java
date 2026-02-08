package LT01.business.impl;

import LT01.business.IMovieService;
import LT01.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieManager implements IMovieService {
    private List<Movie> movies = new ArrayList<Movie>();

    @Override
    public void displayAllMovies() {
        if (movies.isEmpty()) {
            System.out.println("Chưa có phim nào !");
        }

        int i = 1;
        for (Movie m : movies) {
            System.out.print((i++) + ". ");
            m.display();
        }
    }

    @Override
    public List<Movie> findAllByTitle(String title) {
        List<Movie> matches=new  ArrayList<>();
        for (Movie m : movies) {
            if (m.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matches.add(m);
            }
        }
        return matches;
    }

    @Override
    public List<Movie> findAllByRating(double rating) {
        List<Movie> matches=new  ArrayList<>();
        for (Movie m : movies) {
            if (Double.compare(m.getRating(), rating) >= 0) {
                matches.add(m);
            }
        }
        return matches;
    }

    @Override
    public boolean isExistById(String id) {
        for (Movie movie : movies) {
            if (movie.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(Movie movie) {
        movies.add(movie);
    }

    @Override
    public void update(Movie movie, String id) {
        int index = movies.indexOf(findById(id));

        if (index != -1) {
            movies.set(index, movie);
        }
    }

    @Override
    public void delete(String id) {
        Movie movie = findById(id);

        if (movie != null) {
            movies.remove(movie);
        }
    }

    @Override
    public Movie findById(String id) {
        for (Movie movie : movies) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public List<Movie> findAll() {
        return movies;
    }

    @Override
    public void sort() {

    }
}
