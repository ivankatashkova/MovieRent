package movierent.model;

public class Movie {
	private long id;
	private String name;
	private int year;
	private double rentPrice;
	private double price;
	public Movie(String name, int year, double rentPrice, double price) {
		super();
		this.name = name;
		this.year = year;
		this.rentPrice = rentPrice;
		this.price = price;
	}
	public Movie(long id, String name, int year, double rentPrice, double price) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.rentPrice = rentPrice;
		this.price = price;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(double rentPrice) {
		this.rentPrice = rentPrice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}