package movierent.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import movierent.model.Movie;
import movierent.model.User;
import movierent.model.dao.MovieDao;

@Controller
public class MainController {
	
	@Autowired
	MovieDao movieDao;
	
	@RequestMapping(value = {"/index"},method = RequestMethod.GET)
	public String index() {
	
		return "index";
	}
	
	@RequestMapping(value = {"/home","/admin"},method = RequestMethod.GET)
	public String getHome(Model model,HttpSession session) throws SQLException {
		User user = (User) session.getAttribute("user");
		ArrayList<Movie> movies = movieDao.movies();
		model.addAttribute("movies", movies);
		if(user.isAdmin()) {
			return "admin";
		}
		return "home";
	}
	
	/*
		int day = LocalDateTime.now().getDayOfMonth();
		int month =  LocalDateTime.now().getMonthValue();
		int year = LocalDateTime.now().getYear();
		
		method(day,month,year)
		
		method(int day,int month,int year)
		String query = "SELECT user_id,movie_id FROM users_has_rented_movies WHERE DAY(end_date) = ? AND MONTH(end_date)= ? AND YEAR(end_date) = ?";
		day(end_date) = day;
		month(end_date) = month;
		year(end_date) = year;
		delete result row/s
		*/
		
	
	
}
