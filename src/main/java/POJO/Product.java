package POJO;

import java.util.List;

public class Product {

	private String richDescription;
	private String image;
	private String[] images;
	private String  brand;
	private Integer  price;
	private Integer rating;
	private Integer numReviews;
	private Boolean isFeatured;
	private String _id;
	private String name;
	private String description;
	private Category category;
	private List<Review> reviews;
	private Integer countInStock;
	private Integer _v;
	private String dateCreated;
	private String id;
	
	public String getRichDescription() {
		return richDescription;
	}
	public void setRichDescription(String richDescription) {
		this.richDescription = richDescription;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public Integer getNumReviews() {
		return numReviews;
	}
	public void setNumReviews(Integer numReviews) {
		this.numReviews = numReviews;
	}
	public Boolean getIsFeatured() {
		return isFeatured;
	}
	public void setIsFeatured(Boolean isFeatured) {
		this.isFeatured = isFeatured;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public Integer getCountInStock() {
		return countInStock;
	}
	public void setCountInStock(Integer countInStock) {
		this.countInStock = countInStock;
	}
	public Integer get_v() {
		return _v;
	}
	public void set_v(Integer _v) {
		this._v = _v;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	
}
