package Products;

import java.io.Serializable;

/**
 * This Class uses for hands the Product Type
 *
 */
public class ProductType implements Serializable
{
	private int id;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ProductType(int id, String description) {
	
		this.id = id;
		this.description = description;
	}
	@Override
	public String toString() {
		return description;
	}
	
	
}
