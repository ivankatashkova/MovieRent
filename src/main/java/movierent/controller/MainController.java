package movierent.controller;

import java.sql.SQLException;
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
	
}
