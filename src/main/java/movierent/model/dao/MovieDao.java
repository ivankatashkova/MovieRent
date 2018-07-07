package movierent.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
import movierent.model.Movie;
import movierent.model.User;

@Component
public class MovieDao {
	private Connection connection;
	
	public MovieDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	public ArrayList<Movie> movies() throws SQLException {
		String sqlSelectMovies = "SELECT id,name,year,rent_price,price FROM movies";
		ArrayList<Movie> movies = new ArrayList<>();
		try(PreparedStatement ps = connection.prepareStatement(sqlSelectMovies,Statement.RETURN_GENERATED_KEYS)){
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				long id =  set.getLong("id");
				String name = set.getString("name");
				int year = set.getInt("year");
				double rentPrice = set.getDouble("rent_price");
				double price = set.getDouble("price");
				Movie movie = new Movie(id, name, year, rentPrice, price);
				movies.add(movie);
			}
		}
		return movies;
	}
	
	public Movie getMovieById(long id) throws SQLException {
		Movie movie = null;
		String sqlSelectMovieById = "SELECT id,name,year,rent_price,price FROM movies WHERE id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sqlSelectMovieById)){
			ps.setLong(1, id);
			ResultSet rs =  ps.executeQuery();
			while(rs.next()) {
				long movieId = rs.getLong("id");
				String name = rs.getString("name");
				int year =  rs.getInt("year");
				double rentPrice = rs.getDouble("rent_price");
				double price = rs.getDouble("price");
				movie = new Movie(movieId, name, year, rentPrice, price);
			}
		}
		
		return movie;
		
	}
	
	public void rent(User user, Movie movie) throws SQLException {
		String sqlInsertRented = "INSERT INTO users_has_rented_movies (users_id,movies_id) VALUES (?,?)";
		try(PreparedStatement ps =  connection.prepareStatement(sqlInsertRented)){
			ps.setLong(1, user.getId());
			ps.setLong(2,movie.getId());
			ps.executeUpdate();
		}
		
	}
	
	public ArrayList<Movie> rentedMovies (User user) throws SQLException{
		ArrayList<Movie> rented = new ArrayList<>();
		ArrayList<Long> moviesIds =  new ArrayList<>();
		String sqlSelectAllRentedByUser = "SELECT movies_id FROM users_has_rented_movies WHERE users_id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sqlSelectAllRentedByUser)){
			ps.setLong(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				long movieId =  rs.getLong("movies_id");
				moviesIds.add(movieId);
			}
			
			for(int i = 0; i < moviesIds.size();i++) {
				Movie movie = getMovieById(moviesIds.get(i));
				rented.add(movie);
			}
			return rented;
		}
		
	}
	
	public boolean chechIfRented(User user, Movie movie) throws SQLException {
		String sqlCheckIfRented = "SELECT users_id,movies_id FROM users_has_rented_movies WHERE users_id = ? AND movies_id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sqlCheckIfRented)){
			ps.setLong(1, user.getId());
			ps.setLong(2, movie.getId());
			ResultSet rs = ps.executeQuery();
			int counter = 0;
			while(rs.next()) {
				counter++;
			}
			if(counter != 0) {
				return true;
			}
			return false;
		}
	}
	
	public boolean chechIfBougth(User user, Movie movie) throws SQLException {
		String sqlCheckIfBougth = "SELECT users_id,movies_id FROM users_has_bought_movies WHERE users_id = ? AND movies_id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sqlCheckIfBougth)){
			ps.setLong(1, user.getId());
			ps.setLong(2, movie.getId());
			ResultSet rs = ps.executeQuery();
			int counter = 0;
			while(rs.next()) {
				counter++;
			}
			if(counter != 0) {
				return true;
			}
			return false;
		}
	}
	
	public void buy(User user, Movie movie) throws SQLException {
		String sqlInsertBought = "INSERT INTO users_has_bought_movies (users_id,movies_id) VALUES (?,?)";
		try(PreparedStatement ps =  connection.prepareStatement(sqlInsertBought)){
			ps.setLong(1, user.getId());
			ps.setLong(2,movie.getId());
			ps.executeUpdate();
		}
		
	}
	
	public ArrayList<Movie> boughtMovies (User user) throws SQLException{
		ArrayList<Movie> bought = new ArrayList<>();
		ArrayList<Long> moviesIds =  new ArrayList<>();
		String sqlSelectAllBoughtByUser = "SELECT movies_id FROM users_has_bought_movies WHERE users_id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sqlSelectAllBoughtByUser)){
			ps.setLong(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				long movieId =  rs.getLong("movies_id");
				moviesIds.add(movieId);
			}
			
			for(int i = 0; i < moviesIds.size();i++) {
				Movie movie = getMovieById(moviesIds.get(i));
				bought.add(movie);
			}
			return bought;
		}
		
	}
	
	public ArrayList<Movie> favorites (User user) throws SQLException{
		ArrayList<Movie> favorites = new ArrayList<>();
		ArrayList<Long> moviesIds =  new ArrayList<>();
		String sqlSelectFavorites = "SELECT movies_id FROM users_has_favorite_movies WHERE users_id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sqlSelectFavorites)){
			ps.setLong(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				long movieId =  rs.getLong("movies_id");
				moviesIds.add(movieId);
			}
			
			for(int i = 0; i < moviesIds.size();i++) {
				Movie movie = getMovieById(moviesIds.get(i));
				favorites.add(movie);
			}
			return favorites;
		}
		
	}
	
	public void addToFavorite(User user, Movie movie) throws SQLException {
		String sqlInsertBought = "INSERT INTO users_has_favorite_movies (users_id,movies_id) VALUES (?,?)";
		try(PreparedStatement ps =  connection.prepareStatement(sqlInsertBought)){
			ps.setLong(1, user.getId());
			ps.setLong(2,movie.getId());
			ps.executeUpdate();
		}
		
	}
}
	
