package model;

import java.util.List;

public class PromocionAxB extends Promotion {

	private Integer id;
	private List<Attraction> atraccionesGratis;
	private String description, name, type, image;

	public PromocionAxB(Integer id, String name, String type, List<Attraction> attractions,
			List<Attraction> atraccionesGratis, String description, String image) {
		super(name,type, attractions);
		this.id = id;
		this.name = name;
		this.type = type;
		this.atraccionesGratis = atraccionesGratis;
		this.description = description;
		this.image = image;
	}

	public List<Attraction> getAtraccionesGratis() {
		return atraccionesGratis;
	}

	public void setAtraccionesGratis(List<Attraction> atraccionesGratis) {
		this.atraccionesGratis = atraccionesGratis;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double discount() {
		double valor = 0;

		for (Attraction cadaAtraccionGratis : atraccionesGratis) {
			valor += cadaAtraccionGratis.getCost();
		}
		return valor;
	}

	@Override
	public Double getCost() {
		double valor = 0;
		for (int i = 0; i < attractions.size(); i++) {
			valor += attractions.get(i).getCost();
		}

		return valor - discount();
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
	
	
	public boolean includedFree(Ofertable oferta) { // se fija si la oferta ya fue comprada o no.
		int i = 0;
		boolean a = false;
		while (!a && i < atraccionesGratis.size()) {
			a = oferta.included(atraccionesGratis.get(i));
			i++;
		}
		return a;
	}

}
