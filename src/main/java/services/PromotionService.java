package services;

import java.util.List;

import model.Attraction;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;
import model.Promotion;
import persistence.commons.DAOFactory;
import persistence.impl.PromocionDAOImpl;

public class PromotionService {

	public List<Promotion> list() {
		return DAOFactory.getPromoDao().findAll();
	}
	
	public PromocionAbsoluta createPromoAbsoluta(String name,String type, Double cost, List<Attraction> attractions,
			String description, String image) {
		
		PromocionAbsoluta promotionAbsoluta = new PromocionAbsoluta(-1, name,type, cost, attractions, description, image);
		PromocionDAOImpl promotionDAO = DAOFactory.getPromoDao();

		if (promotionAbsoluta.isValid()) {
			promotionDAO.insertPromoAbsoluta(promotionAbsoluta);
		}

		Integer idPromo = promotionDAO.findByName(name); // traer la promo absoluta con el id actualizado

		for (Attraction attraction : attractions) {
			promotionDAO.insertAttractionById(attraction.getId(), idPromo);
		}
		return promotionAbsoluta;
	}

	
	public PromocionPorcentual createPromoPorcentual(String name,String type, Double discount, List<Attraction> attractions,
			String description, String image) {
		
		PromocionPorcentual promocionPorcentual = new PromocionPorcentual(-1, name,type, discount, attractions, description, image);
		PromocionDAOImpl promotionDAO = DAOFactory.getPromoDao();

		if (promocionPorcentual.isValid()) {
			promotionDAO.insertPromoPorcentual(promocionPorcentual);
		}

		Integer idPromo = promotionDAO.findByName(name); // traer la promo absoluta con el id actualizado

		for (Attraction attraction : attractions) {
			promotionDAO.insertAttractionById(attraction.getId(), idPromo);
		}
		return promocionPorcentual;
	}
	
	public PromocionAxB createPromocionAxB(String name, String type, List<Attraction> attractions, List<Attraction> freeAttractions,
			String description, String image) {
		
		PromocionAxB promocionAxB = new PromocionAxB(-1, name, type, attractions, freeAttractions, description, image);
		PromocionDAOImpl promotionDAO = DAOFactory.getPromoDao();

		if (promocionAxB.isValid()) {
			promotionDAO.insertPromoAXB(promocionAxB);
		}

		Integer idPromo = promotionDAO.findByName(name); // traer la promo absoluta con el id actualizado

		for (Attraction attraction : attractions) {
			promotionDAO.insertAttractionById(attraction.getId(), idPromo);
		}
		
		for (Attraction attraction : freeAttractions) {
			promotionDAO.insertarAttractionsGratisAxB(attraction.getId(), idPromo);
		}
		
		
		return promocionAxB;
	}
	
	public PromocionAbsoluta updatePromoAbsoluta(Integer id, String name, Double cost, List<Attraction> attractions, String description,
			String image) {
		PromocionDAOImpl promotionDAO = DAOFactory.getPromoDao();
		PromocionAbsoluta promoAbsoluta = (PromocionAbsoluta) promotionDAO.find(id);
		
		promotionDAO.deleteAttractionById(id);
		
		promoAbsoluta.setName(name);
		promoAbsoluta.setCost(cost);
		promoAbsoluta.setAttractions(attractions);
		promoAbsoluta.setDescription(description);
		promoAbsoluta.setImage(image);
		
		
		for (Attraction attraction : attractions) {
			promotionDAO.insertAttractionById(attraction.getId(), id);
		}

		if (promoAbsoluta.isValid()) {
			promotionDAO.updatePromoAbsoluta(promoAbsoluta);
		}
		
		return promoAbsoluta;
	}
	
	
	public PromocionPorcentual updatePromoPorcentual(Integer id, String name, Double discount, List<Attraction> attractions, String description,
			String image) {
		PromocionDAOImpl promotionDAO = DAOFactory.getPromoDao();
		PromocionPorcentual promoPorcentual = (PromocionPorcentual) promotionDAO.find(id);
		
		promotionDAO.deleteAttractionById(id);
		
		promoPorcentual.setName(name);
		promoPorcentual.setDiscount(discount);
		promoPorcentual.setAttractions(attractions);
		promoPorcentual.setDescription(description);
		promoPorcentual.setImage(image);
		
		for (Attraction attraction : attractions) {
			promotionDAO.insertAttractionById(attraction.getId(), id);
		}		
	
		if (promoPorcentual.isValid()) {
			promotionDAO.updatePromoPorcentual(promoPorcentual);
		}
		return promoPorcentual;
	}
	
	
	public PromocionAxB updatePromoAxB(Integer id, String name, List<Attraction> attractions, List<Attraction> freeAttractions, String description,
			String image) {

		
		PromocionDAOImpl promotionDAO = DAOFactory.getPromoDao();
		PromocionAxB promocionAxB = (PromocionAxB) promotionDAO.find(id);
						
		promotionDAO.deleteAttractionById(id);
		promotionDAO.deleteAttractionsGratisAxB(id);
	
		promocionAxB.setName(name);
		promocionAxB.setAttractions(attractions);
		promocionAxB.setAtraccionesGratis(freeAttractions);
		promocionAxB.setDescription(description);
		promocionAxB.setImage(image);
		 
		for (Attraction attraction : attractions) {
			promotionDAO.insertAttractionById(attraction.getId(), id);
		}
		
		for (Attraction attraction : freeAttractions) {
			promotionDAO.insertarAttractionsGratisAxB(attraction.getId(), id);
		}

		if (promocionAxB.isValid()) {
			promotionDAO.updatePromoAxB(promocionAxB);
		}
		
		return promocionAxB;
	}
	
	

	public String findTypePromo(Integer idPromo) {
		String type = "";
		PromocionDAOImpl promotionDAO = DAOFactory.getPromoDao();
		type = promotionDAO.findTypeById(idPromo);
		return type;
	}
	

	public void delete(Integer id) {
		Promotion promotion = DAOFactory.getPromoDao().find(id);
		DAOFactory.getPromoDao().delete(promotion);
	}

	public Promotion find(Integer id) {
		Promotion promo = DAOFactory.getPromoDao().find(id);
		return promo;
	}



}
