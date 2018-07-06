package movierent.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import movierent.model.Genre;
import movierent.model.Movie;

public class MovieDao {
	private Connection connection;
	
	public ArrayList<Movie> movies() throws SQLException {
		String sqlSelectMovies = "SELECT id,name,year FROM movies";
		ArrayList<Movie> movies = new ArrayList<>();
		try {
			try(PreparedStatement ps = connection.prepareStatement(sqlSelectMovies,Statement.RETURN_GENERATED_KEYS)){
				ResultSet set = ps.executeQuery();
				while (set.next()) {
					long id =  set.getLong("id");
					String name = set.getString("name");
					int year = set.getInt("year");
					int genreId = set.getInt("genre_id");
					double rentPrice = set.getDouble("rent_price");
					double price = set.getDouble("price");
					Genre genre = new Genre();
					Movie movie = new Movie(id, name, year, genre, rentPrice, price);
					movies.add(movie);
				}
			}
		}
		catch (SQLException e) {
			connection.rollback();
			throw e;
		}
		finally {
			connection.setAutoCommit(true);
		}
		
		return movies;
	}
}
	
