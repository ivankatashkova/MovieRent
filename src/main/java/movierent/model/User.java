package movierent.model;

import java.util.Collections;
import java.util.HashSet;

public class User {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean isAdmin;
	private HashSet<Movie> favorites;
	private HashSet<Movie> rented;
	private HashSet<Movie> bougth;
	
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public User(long id, String firstName, String lastName, String email, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public void addToFavorite(Movie movie) {
		this.favorites.add(movie);
	}
	
	public void removeFromFavorites(Movie movie) {
		if(this.favorites.contains(movie)) {
			this.favorites.remove(movie);
		}
	}
	public HashSet<Movie> getFavorites() {
		return (HashSet<Movie>) Collections.unmodifiableSet(favorites);
	}
	
	public void addToRented(Movie movie) {
		this.rented.add(movie);
	}
	
	public void deleteFromRented(Movie movie) {
		if(this.rented.contains(movie)) {
			this.rented.remove(movie);
		}
	}
	public HashSet<Movie> getRented(){
		return (HashSet<Movie>) Collections.unmodifiableSet(rented);
	}
	
	public void addToBougth(Movie movie) {
		this.bougth.add(movie);
	}
	
	public HashSet<Movie> getBougth(){
		return (HashSet<Movie>) Collections.unmodifiableSet(bougth);
	}
}
