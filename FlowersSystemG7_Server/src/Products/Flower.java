package Products;

import java.io.Serializable;
import java.util.ArrayList;

public class Flower implements Serializable {
	
	private String name;
	private double price;
	private int color;
	private ArrayList<FlowerInProduct> flowerInProduct;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public ArrayList<FlowerInProduct> getFlowerInProduct() {
		return flowerInProduct;
	}
	public void setFlowerInProduct(ArrayList<FlowerInProduct> flowerInProduct) {
		this.flowerInProduct = flowerInProduct;
	}
	
	public Flower(String name, double price, int color) {

		this.name = name;
		this.price = price;
		this.color = color;
		this.flowerInProduct = new ArrayList<>();
	}

}
