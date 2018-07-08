package movierent.model;

import java.util.ArrayList;
import java.util.Collections;

public class User {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean isAdmin;
	private ArrayList<Movie> favorites = new ArrayList<>();
	private ArrayList<Movie> rented =  new ArrayList<>();
	private ArrayList<Movie> bought =  new ArrayList<>();
	
	public User(String firstName, String lastName, String email, String password,boolean isAdmin) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public User(long id, String firstName, String lastName, String email, String password,boolean isAdmin) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
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
	public ArrayList<Movie> getFavorites() {
		return (ArrayList<Movie>) Collections.unmodifiableList(favorites);
	}
	
	public void addToRented(Movie movie) {
		this.rented.add(movie);
	}
	
	public void deleteFromRented(Movie movie) {
		if(this.rented.contains(movie)) {
			this.rented.remove(movie);
		}
	}
	public ArrayList<Movie> getRented(){
		return (ArrayList<Movie>) Collections.unmodifiableList(rented);
	}
	
	public void addToBought(Movie movie) {
		this.bought.add(movie);
	}
	
	public ArrayList<Movie> getBought(){
		return (ArrayList<Movie>) Collections.unmodifiableList(bought);
	}
}
