package persistence.commons;

import persistence.AttractionDAO;
import persistence.UserDAO;
import persistence.impl.AttractionDAOImpl;
import persistence.impl.ItineraryDAOImpl;
import persistence.impl.OfertableDAOImpl;
import persistence.impl.PromocionDAOImpl;
import persistence.impl.UserDAOImpl;

public class DAOFactory {

	public static UserDAO getUserDAO() {
		return new UserDAOImpl();
	}
	
	public static AttractionDAO getAttractionDAO() {
		return new AttractionDAOImpl();
	}
	
	public static PromocionDAOImpl getPromoDao() {
		return new PromocionDAOImpl();
	}
	
	
	public static OfertableDAOImpl getOfertableDAO() {
		return new OfertableDAOImpl();
	}
	
	public static ItineraryDAOImpl getItinerarioDAO() {
		return new ItineraryDAOImpl();
	}
}
