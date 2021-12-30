package services;

import java.util.HashMap;
import java.util.Map;

import model.Promotion;
import model.User;
import persistence.UserDAO;
import persistence.commons.DAOFactory;
import persistence.impl.ItineraryDAOImpl;
import persistence.impl.PromocionDAOImpl;

public class BuyPromotionService {

	ItineraryDAOImpl itineraryDAO = DAOFactory.getItinerarioDAO();
	PromocionDAOImpl promotionDAO = DAOFactory.getPromoDao();
	UserDAO userDAO = DAOFactory.getUserDAO();

	public Map<String, String> buy(Integer userId, Integer promotionId) {
		Map<String, String> errors = new HashMap<String, String>();

		User user = userDAO.find(userId);
		Promotion promotion = promotionDAO.find(promotionId);
		
		if (!promotion.canHost(1)) {
			errors.put("promotion", "No hay cupo disponible");
		}
		if (!user.canAfford(promotion)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.canAttend(promotion)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			user.addToItinerary(promotion);
			promotion.host(1);
		
			promotionDAO.updateCupo(promotion);
			itineraryDAO.insertarItinerarioPromo(user, promotion);
			userDAO.update(user);
			

			
		}
		return errors;

	}
	

}
