package movierent.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import movierent.model.Movie;
import movierent.model.User;
import movierent.model.dao.MovieDao;

@Controller
public class MovieController {
	
	@Autowired
	MovieDao movieDao;
	
	@RequestMapping(value="/movie/{movieId}",method = RequestMethod.GET)
	public String getMovie(Model model,@PathVariable (value = "movieId") String movieId) throws NumberFormatException, SQLException {		
		Movie movie = null;
		movie = movieDao.getMovieById(Long.parseLong(movieId));
		model.addAttribute("movie", movie);
		return "movie";
	}
	
	@RequestMapping(value="/rent/{movieId}",method = RequestMethod.GET)
	public String rentMovie(Model model,@PathVariable (value = "movieId") String movieId, HttpSession session) throws NumberFormatException, SQLException {		
		Movie movie = null;
		movie = movieDao.getMovieById(Long.parseLong(movieId));
		User user = (User) session.getAttribute("user");
		if(movieDao.chechIfRented(user, movie)) {
			model.addAttribute("msg", "Movie is already rented!");
			return "movie";
		}
		//user.addToRented(movie);
		movieDao.rent(user, movie);
		ArrayList<Movie> rented = movieDao.rentedMovies(user);
		model.addAttribute("rented", rented);
		return "profile";
	}
	
	@RequestMapping(value="/buy/{movieId}",method = RequestMethod.GET)
	public String buyMovie(Model model,@PathVariable (value = "movieId") String movieId, HttpSession session) throws NumberFormatException, SQLException {		
		Movie movie = null;
		movie = movieDao.getMovieById(Long.parseLong(movieId));
		User user = (User) session.getAttribute("user");
		if(movieDao.chechIfBougth(user, movie)) {
			model.addAttribute("msg", "You already have that movie!");
			return "movie";
		}
		//user.addToBougth(movie);
		movieDao.buy(user, movie);
		ArrayList<Movie> bought = movieDao.rentedMovies(user);
		model.addAttribute("bougth", bought);
		return "profile";
	}
	
	@RequestMapping(value="/favorite/{movieId}",method = RequestMethod.GET)
	public String addToFavorite(Model model,@PathVariable (value = "movieId") String movieId, HttpSession session) throws NumberFormatException, SQLException {		
		Movie movie = null;
		movie = movieDao.getMovieById(Long.parseLong(movieId));
		User user = (User) session.getAttribute("user");
		ArrayList<Movie> favorites = movieDao.favorites(user);
		if(favorites.contains(movie)) {
			model.addAttribute("msg", "Movie is already in favorites!");
			return "movie";
		}
		movieDao.addToFavorite(user, movie);
		favorites.add(movie);
		model.addAttribute("favorites", favorites);
		ArrayList<Movie> rented = movieDao.rentedMovies(user);
		model.addAttribute("rented", rented);
		ArrayList<Movie> bought = movieDao.boughtMovies(user);
		model.addAttribute("bought", bought);
		return "profile";
	}
	
	@RequestMapping(value = {"/delete/{movieId}"},method = RequestMethod.GET)
	public String deleteMovie(Model model,HttpSession session,@PathVariable (value = "movieId") String movieId) throws SQLException {
		long id = Long.parseLong(movieId);
		movieDao.delete(id);
		return "admin";
	}
	
	@RequestMapping(value="/remove/{movieId}",method = RequestMethod.GET)
	public String removeFromFavorite(Model model,@PathVariable (value = "movieId") String movieId, HttpSession session) throws NumberFormatException, SQLException {		
		User user = (User) session.getAttribute("user");
		Movie movie = movieDao.getMovieById(Long.parseLong(movieId));
		movieDao.removeFromFavorite(user, movie);
		ArrayList<Movie> favorites =  movieDao.favorites(user);
		model.addAttribute("favorites", favorites);
		ArrayList<Movie> rented = movieDao.rentedMovies(user);
		model.addAttribute("rented", rented);
		ArrayList<Movie> bought = movieDao.boughtMovies(user);
		model.addAttribute("bought", bought);
		return "profile";
	}
	
	@RequestMapping(value = {"/addMovie"},method = RequestMethod.POST)
	public String addMovie(Model model,
			@RequestParam (value = "name") String name,
			@RequestParam (value = "year") String year,
			@RequestParam (value = "rentPrice") String rentPrice,
			@RequestParam (value = "price") String price) throws SQLException {
		String movieName =  name;
		int movieYear = Integer.parseInt(year);
		double movieRentPrice = Double.parseDouble(rentPrice);
		double moviePrice = Double.parseDouble(price);
		Movie movie = new Movie(movieName, movieYear, movieRentPrice, moviePrice);
		movieDao.addMovie(movie);
		model.addAttribute("msg", "New movie added!");
		ArrayList<Movie>movies = movieDao.movies();
		model.addAttribute("movies", movies);
		return "admin";
	}

}