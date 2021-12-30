package model;

import java.util.ArrayList;
import java.util.List;

public class Itinerary {

	//private Integer id;

	private Double spending;
	private Double timeUsed;
	private List<Ofertable> purchasedOffers;
	private List<String> namesList;
	private User user;

	public Itinerary(User user) { //Integer id
		//this.id = id;
		this.user = user;
		this.namesList = new ArrayList<String>();
		this.purchasedOffers = new ArrayList<Ofertable>();
	}
	
	/*
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	*/

	public void AddOfertas(Ofertable cadaOferta) {
		purchasedOffers.add(cadaOferta);
	}

	public List<Ofertable> getPurchasedOffers() {
		return purchasedOffers;
	}

	public Double getSpending() {
		for (Ofertable cadaOferta : purchasedOffers) {
			spending += cadaOferta.getCost();
		}
		return spending;
	}

	public Double getTimeUsed() {
		for (Ofertable cadaOferta : purchasedOffers) {
			timeUsed += cadaOferta.getDuration();
		}
		return timeUsed;
	}

	public List<String> getNamesList() {
		for (Ofertable cadaOferta : purchasedOffers) {
			namesList.add(cadaOferta.getName());
		}
		return namesList;
	}
	
	
	public boolean existInItinerary(Ofertable cadaOferta) {
		boolean puede = true;
		int i = 0;
		while (puede && i < purchasedOffers.size()) {
			puede = !cadaOferta.included(purchasedOffers.get(i));
			i++;
		}
		return puede;
	}
	
	@Override
	public String toString() {
		return "Itinerario [gastoTotal=" + spending + ", horasConsumidas=" + timeUsed + ", ofertasCompradas="
				+ purchasedOffers + ", listaNombres=" + namesList + ", user=" + user + "]";
	}

}
