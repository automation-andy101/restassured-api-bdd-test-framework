package resources;

public enum  APIResources {
	
	// Category Resources
	GetCategoriesAPI("categories/"),
	GetCategoryAPI("categories/"),
	AddCategoryAPI("categories/"),
	UpdateCategoryAPI("categories/"),
	DeleteCategoryAPI("categories/"),
	
	// Product APIs
	GetProductsAPI("products/productsAdmin"),
	GetProductAPI("products/"),
	AddProductAPI("products/"),
	UpdateProductAPI("products/gallery-images/"),
	DeleteProductAPI("products/"),
	GetProductByFeatureAPI("get/featured"),
	ProductCountAPI("products/get/count");
	
	private String resourceName;
	
	// Constructor
	APIResources(String resource) {
		this.resourceName = resource;
	}
	
	// Get resource name method
	public String getResource()
	{
		return resourceName;
	}
	
}
