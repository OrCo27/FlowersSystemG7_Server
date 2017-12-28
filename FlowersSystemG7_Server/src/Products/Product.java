package Products;

import java.io.Serializable;
import java.util.ArrayList;

import Commons.ProductInOrder;

public class Product implements Serializable {
	private int id;
	private ProductType productType;
	private double price;
	private ArrayList<FlowerInProduct> flowerInProductList;
	private ArrayList<ProductInOrder> productInOrderList;
	public int getId() {
		return id;
	}
	public Product(int id, ProductType productType, double price) {
		super();
		this.id = id;
		this.productType = productType;
		this.price = price;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public ArrayList<FlowerInProduct> getFlowerInProductList() {
		return flowerInProductList;
	}
	public void setFlowerInProductList(ArrayList<FlowerInProduct> flowerInProductList) {
		this.flowerInProductList = flowerInProductList;
	}
	public ArrayList<ProductInOrder> getProductInOrderList() {
		return productInOrderList;
	}
	public void setProductInOrderList(ArrayList<ProductInOrder> productInOrderList) {
		this.productInOrderList = productInOrderList;
	}
	public Product(int id, ProductType productType, double price, ArrayList<FlowerInProduct> flowerInProductList,
			ArrayList<ProductInOrder> productInOrderList) {

		this.id = id;
		this.productType = productType;
		this.price = price;
		this.flowerInProductList = flowerInProductList;
		this.productInOrderList = productInOrderList;
	}
	
	
}
