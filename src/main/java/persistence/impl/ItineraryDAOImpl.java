package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Attraction;
import model.Itinerary;
import model.Ofertable;
import model.Promotion;
import model.User;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class ItineraryDAOImpl {

	private List<Integer> findAtraccionesItinerario(User user) {
		List<Integer> listaAtraccionesID = new ArrayList<Integer>();
		try {
			String sql = "SELECT DISTINCT itinerario.*\r\n" + "FROM itinerario\r\n" + "join users\r\n"
					+ "	on itinerario.usuario_id = ?";
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, user.getId());
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				if (result.getInt(4) != 0) {
					listaAtraccionesID.add(result.getInt(4));
				}
			}
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

		return listaAtraccionesID;
	}

	private List<Integer> findPromocionesItinerario(User user) {
		List<Integer> listaPromocionesID = new ArrayList<Integer>();
		try {
			String sql = "SELECT DISTINCT itinerario.*\r\n" + "FROM itinerario\r\n" + "join users\r\n"
					+ "	on itinerario.usuario_id = ?";
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, user.getId());
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				if (result.getInt("promocion_id") != 0) {
					listaPromocionesID.add(result.getInt("promocion_id"));
				}
			}
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return listaPromocionesID;
	}

	public List<Ofertable> findItinerario(User user, List<Attraction> attractions, List<Promotion> promotions) {
		List<Ofertable> itinerario = new ArrayList<Ofertable>();

		for (Integer attraction_id : findAtraccionesItinerario(user))
			for (Attraction anyAtraction : attractions) {
				if (attraction_id == anyAtraction.getId()) {
					itinerario.add(anyAtraction);
				}
			}

		for (Integer promo_id : findPromocionesItinerario(user))
			for (Promotion anyPromo : promotions) {
				if (promo_id == anyPromo.getId()) {
					itinerario.add(anyPromo);
				}
			}
		return itinerario;
	}

	public int insertarItinerario(User user, Ofertable oferta) {
		int rows = 0;
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "INSERT INTO itinerario (usuario_id, promocion_id, atraccion_id) VALUES(?,?,?)  ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, user.getId());

			if (oferta.soyPromocion()) {
				statement.setInt(2, oferta.getId());
			} else {
				statement.setInt(3, oferta.getId());
			}
			rows = statement.executeUpdate();

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return rows;
	}
	
	
	public int insertarItinerarioPromo(User user, Promotion promocion) {
		int rows = 0;
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "INSERT INTO itinerario (usuario_id, promocion_id) VALUES(?,?)  ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, user.getId());

			if (promocion.soyPromocion()) {
				statement.setInt(2, promocion.getId());
			} 
			
			rows = statement.executeUpdate();

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return rows;
	}
	
	
	
	public int insertarItinerarioAtraccion(User user, Attraction atraction) {
		int rows = 0;
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "INSERT INTO itinerario (usuario_id, atraccion_id) VALUES(?,?)  ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, user.getId());

			if (!atraction.soyPromocion()) {
				statement.setInt(2, atraction.getId());
			} 
			
			rows = statement.executeUpdate();

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return rows;
	}
	
	
	/*
	public int eliminarItinerario(Itinerary itinerary) {
		int rows = 0;
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "DELETE FROM itinerario WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, itinerary.getId());
			rows = statement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return rows;
	}
	*/	
}
