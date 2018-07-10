package movierent.model;

import java.sql.Timestamp;

public class Movie {
	private long id;
	private String name;
	private int year;
	private double rentPrice;
	private double price;
	private Timestamp endDate;
	private String url;
	private String img;
	
	public Movie(String name, int year, double rentPrice, double price) {
		super();
		this.name = name;
		this.year = year;
		this.rentPrice = rentPrice;
		this.price = price;
		setImg();
	}
	public Movie(long id, String name, int year, double rentPrice, double price) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.rentPrice = rentPrice;
		this.price = price;
		setImg();
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
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImg() {
		return img;
	}
	public void setImg() {
		if(this.name.contains(":")) {
			this.name  = this.name.replaceAll(":", "-");
		}
		this.img = this.name.toLowerCase()+".jpg";
	}
	
}