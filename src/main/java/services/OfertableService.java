package services;

import java.util.List;

import model.Ofertable;
import model.User;
import persistence.AttractionDAO;
import persistence.commons.DAOFactory;
import persistence.impl.ItineraryDAOImpl;
import persistence.impl.PromocionDAOImpl;
public class OfertableService {
	
	public List<Ofertable> list(User user) {	
		return DAOFactory.getOfertableDAO().findAll();
	}
		
	public List<Ofertable> list2(User user) {
		ItineraryDAOImpl itineraryDAO = DAOFactory.getItinerarioDAO();
		PromocionDAOImpl promotionDAO = DAOFactory.getPromoDao();
		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		
		List<Ofertable> itinerario = itineraryDAO.findItinerario(user,attractionDAO.findAll(),promotionDAO.findAll());
		List<Ofertable> ofertas = DAOFactory.getOfertableDAO().findAll();
		
		for (Ofertable ofertaItinerario : itinerario) {
			for(Ofertable oferta : ofertas) {
				if(oferta.getName().equals(ofertaItinerario.getName())) {
					ofertas.remove(oferta);
				}
			}
		}
		
		return ofertas;
	}
		


	/*
	public Attraction create(String name, Double cost, Double duration, Integer capacity) {

		Attraction attraction = new Attraction(-1, name, cost, duration, capacity);

		if (attraction.isValid()) {
			AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
			attractionDAO.insert(attraction);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return attraction;
	}

	public Attraction update(Integer id, String name, Double cost, Double duration, Integer capacity) {

		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		Attraction attraction = attractionDAO.find(id);

		attraction.setName(name);
		attraction.setCost(cost);
		attraction.setDuration(duration);
		attraction.setCapacity(capacity);

		if (attraction.isValid()) {
			attractionDAO.update(attraction);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return attraction;
	}

	public void delete(Integer id) {
		Attraction attraction = new Attraction(id, null, null, null, null);

		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		attractionDAO.delete(attraction);
	}

	public Attraction find(Integer id) {
		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		return attractionDAO.find(id);
	}
*/

}
