package movierent.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
		String sqlSelectMovieById = "SELECT id,name,year,rent_price,price,url FROM movies WHERE id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sqlSelectMovieById)){
			ps.setLong(1, id);
			ResultSet rs =  ps.executeQuery();
			while(rs.next()) {
				long movieId = rs.getLong("id");
				String name = rs.getString("name");
				int year =  rs.getInt("year");
				double rentPrice = rs.getDouble("rent_price");
				double price = rs.getDouble("price");
				String url = rs.getString("url");
				movie = new Movie(movieId, name, year, rentPrice, price);
				movie.setUrl(url);
			}
		}
		
		return movie;
		
	}
	
	public void rent(User user, Movie movie) throws SQLException {
		String sqlInsertRented = "INSERT INTO users_has_rented_movies (users_id,movies_id,end_date) VALUES (?,?,?)";
		try(PreparedStatement ps =  connection.prepareStatement(sqlInsertRented)){
			ps.setLong(1, user.getId());
			ps.setLong(2,movie.getId());
			LocalDateTime date = LocalDateTime.now().plusDays(7);
			Timestamp endDate =  Timestamp.valueOf(date);
			ps.setTimestamp(3, endDate);
			ps.executeUpdate();
		}
		
	}
	
	public ArrayList<Movie> rentedMovies (User user) throws SQLException{
		connection.setAutoCommit(false);
		ArrayList<Movie> rented = new ArrayList<>();
		int day = LocalDateTime.now().getDayOfMonth();
		int month = LocalDateTime.now().getMonthValue();
		int year = LocalDateTime.now().getYear();
		String deleteExpiredRentMovies = "DELETE FROM users_has_rented_movies WHERE DAY(end_date) = ? AND MONTH(end_date) = ? AND YEAR(end_date) = ?";
		String sqlSelectAllRentedByUser = "SELECT movies_id,end_date FROM users_has_rented_movies WHERE users_id = ?";
		try{
			try(PreparedStatement ps = connection.prepareStatement(deleteExpiredRentMovies)){
				ps.setInt(1, day);
				ps.setInt(2, month);
				ps.setInt(3, year);
				ps.executeUpdate();
			}
			try(PreparedStatement ps = connection.prepareStatement(sqlSelectAllRentedByUser)){
				ps.setLong(1, user.getId());
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					long id = rs.getLong("movies_id");
					Timestamp endDate = rs.getTimestamp("end_date");
					Movie movie = getMovieById(id);
					movie.setEndDate(endDate);
					rented.add(movie);
				}
			}
		}
		catch(SQLException e) {
			connection.rollback();
			throw e;
		}
		finally {
			connection.setAutoCommit(true);
		}
			
			return rented;		
	}
	
	public boolean checkIfRented(User user, Movie movie) throws SQLException {
		String sqlCheckIfRented = "SELECT users_id,movies_id FROM users_has_rented_movies WHERE users_id = ? AND movies_id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sqlCheckIfRented)){
			ps.setLong(1, user.getId());
			ps.setLong(2, movie.getId());
			ResultSet rs = ps.executeQuery();
			int counter = 0;
			while(rs.next()) {
				counter++;
			}
			return counter != 0;
		}
	}
	
	public boolean checkIfBougth(User user, Movie movie) throws SQLException {
		String sqlCheckIfBougth = "SELECT users_id,movies_id FROM users_has_bought_movies WHERE users_id = ? AND movies_id = ?";
		try(PreparedStatement ps = connection.prepareStatement(sqlCheckIfBougth)){
			ps.setLong(1, user.getId());
			ps.setLong(2, movie.getId());
			ResultSet rs = ps.executeQuery();
			int counter = 0;
			while(rs.next()) {
				counter++;
			}
			return counter != 0;
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
	
	public void removeFromFavorite(User user, Movie movie) throws SQLException {
		String sqlInsertBought = "DELETE FROM users_has_favorite_movies WHERE users_id = ? AND movies_id = ?";
		try(PreparedStatement ps =  connection.prepareStatement(sqlInsertBought)){
			ps.setLong(1, user.getId());
			ps.setLong(2,movie.getId());
			ps.executeUpdate();
		}
		
	}
	
	public void addMovie(Movie movie) throws SQLException {
		String sqlAddMovie = "INSERT INTO movies (name,year,rent_price,price,url) VALUES (?,?,?,?,?)";
		try(PreparedStatement ps =  connection.prepareStatement(sqlAddMovie)){
			ps.setString(1, movie.getName());
			ps.setInt(2, movie.getYear());
			ps.setDouble(3, movie.getRentPrice());
			ps.setDouble(4, movie.getPrice());
			ps.setString(5, movie.getUrl());
			ps.executeUpdate();
		}
		
	}
	
}
	
