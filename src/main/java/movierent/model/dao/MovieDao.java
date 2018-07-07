package movierent.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
import movierent.model.Movie;

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
}
	
