package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistence.AttractionDAO;
import persistence.commons.DAOFactory;
import persistence.impl.ItineraryDAOImpl;
import persistence.impl.PromocionDAOImpl;

import utils.Crypt;

public class User {

	ItineraryDAOImpl itineraryDAO = DAOFactory.getItinerarioDAO();
	PromocionDAOImpl promotionDAO = DAOFactory.getPromoDao();
	AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();

	private Integer id;
	private String username, password;
	private Boolean admin;
	private Double coins;
	private Double time;
	private HashMap<String, String> errors;
	private List<Ofertable> itinerary;

	public User(Integer id, String username, String password, Double coins, Double time, Boolean admin) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.coins = coins;
		this.time = time;
		this.admin = admin;
		this.itinerary = itineraryDAO.findItinerario(this, attractionDAO.findAll(), promotionDAO.findAll());
	}

	public void addToItinerary(Ofertable ofertable) {
		this.coins -= ofertable.getCost();
		this.time -= ofertable.getDuration();
		this.itinerary.add(ofertable);
	}

	public List<Ofertable> getItinerary() {
		return itinerary;
	}

	public void setItinerary(List<Ofertable> itinerary) {
		this.itinerary = itinerary;
	}

	public boolean canAfford(Ofertable ofertable) {
		return ofertable.getCost() <= this.coins;
	}

	public boolean canAttend(Ofertable ofertable) {
		return ofertable.getDuration() <= this.time;
	}

	public boolean checkPassword(String password) {
		// this.password en realidad es el hash del password
		return Crypt.match(password, this.password);
	}

	public Boolean getAdmin() {
		return admin;
	}

	public Double getCoins() {
		return coins;
	}

	public Integer getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public Double getTime() {
		return time;
	}

	public String getUsername() {
		return username;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public boolean isNull() {
		return false;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public void setCoins(Double coins) {
		this.coins = coins;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = Crypt.hash(password);
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}

	public void validate() {
		errors = new HashMap<String, String>();

		if (coins < 0) {
			errors.put("coins", "No debe ser negativo");
		}
		if (time < 0) {
			errors.put("time", "No debe ser negativo");
		}
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", admin=" + admin + "]";
	}

	public boolean canBuy(Ofertable cadaOferta) {
		boolean can = true;
		int i = 0;
		while (can && i < itinerary.size()) {
			can = !cadaOferta.included(itinerary.get(i));
			i++;
		}
		return can;
	}
	

}
