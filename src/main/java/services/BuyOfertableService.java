package services;

import java.util.HashMap;
import java.util.Map;
import model.Ofertable;
import model.User;
import persistence.UserDAO;
import persistence.commons.DAOFactory;
import persistence.impl.ItineraryDAOImpl;
import persistence.impl.OfertableDAOImpl;

public class BuyOfertableService {

	OfertableDAOImpl ofertableDAO = DAOFactory.getOfertableDAO();
	ItineraryDAOImpl itineraryDAO = DAOFactory.getItinerarioDAO();
	UserDAO userDAO = DAOFactory.getUserDAO();

	public Map<String, String> buy(Integer userId, Integer ofertableId, boolean soyPromo) {
		Map<String, String> errors = new HashMap<String, String>();

		User user = userDAO.find(userId);

		Ofertable ofertable = ofertableDAO.find(ofertableId, soyPromo);

		/*
		if (user.canBuy(ofertable)) {
			errors.put("ofertable", "No podés volver a comprar la misma oferta");
		}
		*/
		if (!ofertable.canHost(1)) {
			errors.put("ofertable", "No hay cupo disponible");
		}
		if (!user.canAfford(ofertable)) {
			errors.put("user", "No tienes dinero suficiente");
		}
		if (!user.canAttend(ofertable)) {
			errors.put("user", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			user.addToItinerary(ofertable);
			ofertable.host(1);
			ofertableDAO.updateCupo(ofertable);
			itineraryDAO.insertarItinerario(user, ofertable);
			userDAO.update(user);
		}

		return errors;

	}

}
