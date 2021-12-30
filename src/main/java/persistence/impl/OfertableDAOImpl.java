package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Ofertable;
import model.Promotion;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
import persistence.commons.MissingDataException;
import utils.OrdenadorOfertable;

public class OfertableDAOImpl {
		
	public List<Ofertable> findAll() {
		List<Ofertable> ofertas = new ArrayList<>();
		ofertas.addAll(DAOFactory.getPromoDao().findAll());
		ofertas.addAll(DAOFactory.getAttractionDAO().findAll());
		ofertas.sort(new OrdenadorOfertable());
		return ofertas;
	}
	
	public Ofertable find(Integer id, boolean soyPromo) {
		Ofertable ofertable = null;
		if (soyPromo) {
			ofertable = DAOFactory.getPromoDao().find(id);
		} else {
			ofertable = DAOFactory.getAttractionDAO().find(id);
		}		
		return ofertable;
	}
	
	
	public List<Integer> getAtraccionesIDEnPromo(int ofertaID) {
		List<Integer> listaAtraccionesID = new ArrayList<Integer>();

		try {
			String sql = "select attraction_promotion.attraction_id\r\n" + "from attraction_promotion\r\n"
					+ "where promotion_id =" + ofertaID;
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				listaAtraccionesID.add(result.getInt(1));
			}
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return listaAtraccionesID;
	}
	
		
	public int updateCupo(Ofertable oferta) {
		int rows = 0;

		if (oferta.soyPromocion()) {
			for (int atraccionID : getAtraccionesIDEnPromo(oferta.getId())) {
				String sql = "UPDATE attractions SET capacity = capacity - ? WHERE id=?";

				try {
					Connection connection = ConnectionProvider.getConnection();
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setInt(1, 1);
					statement.setInt(2, atraccionID);
					rows = statement.executeUpdate();
				} catch (Exception e) {
					throw new MissingDataException(e);
				}
			}

		} else {
			String sql = "UPDATE attractions SET capacity = capacity - ? WHERE id=?";

			try {
				Connection connection = ConnectionProvider.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, 1);
				statement.setInt(2, oferta.getId());
				rows = statement.executeUpdate();

			} catch (Exception e) {
				throw new MissingDataException(e);
			}
		}
		return rows;
	}
	
	
	
	
	
	
		
/*
	public int update(Ofertable ofertable,  boolean soyPromo) {
		int rows = 0;
		if (soyPromo) {
			rows = DAOFactory.getPromoDao().updatePromoAbsoluta(ofertable);
	
			
		} else {
			rows = DAOFactory.getAttractionDAO().update(ofertable);
			
			
		}		
		return rows;
	}
*/
	/*
	public int delete(Ofertable ofertable,  boolean soyPromo) {
		int rows = 0;
		
		if (soyPromo) {
			rows = DAOFactory.getPromoDao().delete(ofertable);
		} else {
			rows = DAOFactory.getAttractionDAO().delete(ofertable);
		}		
		return rows;
	}

*/
}
