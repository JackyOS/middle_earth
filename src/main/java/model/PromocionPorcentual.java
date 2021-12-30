package model;

import java.util.List;

public class PromocionPorcentual extends Promotion {

	private Integer id;
	private Double discount;
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	private String description;
	private String image;

	public PromocionPorcentual(Integer id, String name, String type, Double discount,
			List<Attraction> attractions, String description, String image) {
		super(name,type, attractions);
		this.id = id ;
		this.name = name;
		this.type = type;
		this.discount = discount;
		this.description = description;
		this.image = image;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Double getDiscount() {
		return discount;
	}

	@Override
	public Double getCost() {
		return discount * super.getOriginalCost();
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
