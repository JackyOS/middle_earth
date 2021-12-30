package model;

import java.util.List;

public class PromocionAbsoluta extends Promotion {

	private Integer id;
	private Double cost;
	private String description;
	private String image, type;

	public PromocionAbsoluta(Integer id, String name, String type, Double cost,
			List<Attraction> attractions, String description, String image) {
		super(name,type, attractions);
		this.id = id;
		this.name = name;
		this.type = type;
		this.cost = cost;
		this.description = description;
		this.image = image;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public Double getCost() {
		return cost;
	}
	
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public void setDescription(String description) {
		this.description = description;
		
	}
	
	@Override
	public String getImage() {
		return image;
	}
	
	@Override
	public void setImage(String image) {
		this.image = image;
	}
}
