package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Attraction implements Ofertable {

	private Integer id;
	private String name;
	private Double cost;
	private Double duration;
	private Integer capacity;
	private String description;
	private String image;

	private Map<String, String> errors;

	public Attraction(Integer id, String name, Double cost, Double duration, Integer capacity, String description, String image) {
		super();
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.duration = duration;
		this.capacity = capacity;
		this.description = description;
		this.image = image;
	}

	@Override
	public boolean isValid() { // se fija si hay errores, si no hay errores, esta vacio = true
		validate();
		return errors.isEmpty();
	}

	@Override
	public void validate() { // validaciones campo por campo
		errors = new HashMap<String, String>(); // guardo los errores

		if (cost <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (duration <= 0) {
			errors.put("duration", "Debe ser positivo");
		}
		if (capacity <= 0) {
			errors.put("capacity", "Debe ser positivo");
		}
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
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
	
	
	
	
	

	@Override
	public String toString() {
		return "Attraction [id=" + id + ", name=" + name + ", cost=" + cost + ", duration=" + duration + ", capacity="
				+ capacity + "]";
	}

	@Override
	public boolean canHost(int i) {
		return capacity >= i;
	}

	@Override
	public void host(int i) {
		this.capacity -= i;
	}

	@Override
	public boolean soyPromocion() {
		return false;
	}

	@Override
	public boolean included(Ofertable oferta) {
		if (oferta.soyPromocion()) {
			return oferta.included(this);
		}
		return this.getName().equals(oferta.getName());
	}

	@Override
	public List<String> getNames() {
		// TODO Auto-generated method stub
		return null;
	}
}
