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
	
}