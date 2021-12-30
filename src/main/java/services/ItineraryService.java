package services;

import java.util.List;

import model.Itinerary;
import model.Ofertable;
import model.User;
import persistence.AttractionDAO;
import persistence.commons.DAOFactory;
import persistence.impl.ItineraryDAOImpl;
import persistence.impl.PromocionDAOImpl;

public class ItineraryService {
	
	public List<Ofertable> list(User user) {
		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		PromocionDAOImpl promotionDAO = DAOFactory.getPromoDao();
		ItineraryDAOImpl itineraryDAO = DAOFactory.getItinerarioDAO();
		
		List <Ofertable> listOfertas = itineraryDAO.findItinerario(user, attractionDAO.findAll(), promotionDAO.findAll());
		
		return listOfertas;
	}

	/*
	public void delete(Integer id) {
		Itinerary itinerary = new Itinerary(id, null);
		ItineraryDAOImpl itineraryDAO = DAOFactory.getItinerarioDAO();
		
		itineraryDAO.eliminarItinerario(itinerary);
	}
	*/
}
