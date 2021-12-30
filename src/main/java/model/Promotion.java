package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Promotion implements Ofertable {

	protected String name, type;
	

	protected List<Attraction> attractions;
	List<String> names;

	private Map<String, String> errors;

	public Promotion(String name, String type, List<Attraction> attractions) {
		this.attractions = attractions;
		this.name = name;
		this.names =  new ArrayList<String>();
	}
	
	@Override
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}

	@Override
	public void validate() { // validaciones campo por campo
		errors = new HashMap<String, String>(); // guardo los errores

		if (getCost() <= 0) {
			errors.put("cost", "Debe ser positivo");
		}
		if (getDuration() <= 0) {
			errors.put("duration", "Debe ser positivo");
		}
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	@Override
	public String getName() { // nombre de la promocion
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public List<Attraction> getAttractions() {
		return attractions;
	}
	
	public void setAttractions(List<Attraction> attractions) {
		this.attractions = attractions;
	}
	
	public List<String> getNames() { // nombre de cada atraccion
		//List<String> names = new ArrayList<String>();
		for (Attraction anyAttraction : attractions) {
			names.add(anyAttraction.getName());
		}
		return names;
	}

	public List<Integer> getAttrationsID() { // nombre de cada atraccion
		List<Integer> listaID = new ArrayList<>();
		for (Attraction anyAttraction : attractions) {
			listaID.add(anyAttraction.getId());
		}
		return listaID;
	}

	@Override
	public Double getCost() {
		return 0.0;
	}
	
	public Double getDiscount() {
		return 0.0;
	}

	public Double getOriginalCost() {
		Double value = 0.0;
		for (Attraction anyAttraction : attractions) {
			value += anyAttraction.getCost();
		}
		return value;
	}

	@Override
	public Double getDuration() { // duracion total, sumando todas las atracciones de la promo
		double duracionTotal = 0;
		for (Attraction anyAttraction : attractions) {
			duracionTotal += anyAttraction.getDuration();
		}
		return duracionTotal;
	}
	
	@Override
	public boolean canHost(int b) {
		boolean verCupo = true;
		int i = 0;
		while (verCupo && i < attractions.size()) {
			verCupo = attractions.get(i).canHost(b);
			i++;
		}
		return verCupo;
	}

	@Override
	public void host(int i) { // resta cupo en cada atraccion
		for (Attraction anyAttraction : attractions) {
			anyAttraction.host(i);
			;
		}
	}

	@Override
	public boolean soyPromocion() {
		return true;
	}

	@Override
	public boolean included(Ofertable oferta) { // se fija si la oferta ya fue comprada o no.
		int i = 0;
		boolean a = false;
		while (!a && i < attractions.size()) {
			a = oferta.included(attractions.get(i));
			i++;
		}
		return a;
	}

	@Override
	public String toString() {
		return "Promotion [name=" + name + ", attractions=" + attractions + ", errors=" + errors + "]";
	}

}
