package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Attraction;
import model.Ofertable;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;
import model.Promotion;
import persistence.AttractionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
import persistence.commons.MissingDataException;

public class PromocionDAOImpl {

	public Promotion find(Integer id) {
		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		try {
			String sql = "SELECT promotions.*, group_concat(attractions.id) AS 'attractions_ids'"
					+ "from attractions\r\n" + "join Attraction_Promotion\r\n"
					+ "on Attraction_Promotion.attraction_id = attractions.id\r\n" + "join promotions\r\n"
					+ "on  promotions.id = Attraction_Promotion.promotion_id\r\n" + "GROUP BY promotions.id\r\n"
					+ "HAVING promotions.id = ? and promotions.deleted = 0 "; // and promotions.deleted = 0
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();
			Promotion promotion = null;
			if (resultados.next()) {
				promotion = toPromotion(resultados, attractionDAO.findAll());
			}
			return promotion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public List<Promotion> findAll() {
		AttractionDAO attractionDAO = DAOFactory.getAttractionDAO();
		List<Promotion> listapromotions = new ArrayList<Promotion>();
		try {
			String sql = "SELECT promotions.*, group_concat(attractions.id) AS 'attractions_ids'"
					+ "from attractions\r\n" + "join Attraction_Promotion\r\n"
					+ "on Attraction_Promotion.attraction_id = attractions.id\r\n" + "join promotions\r\n"
					+ "on  promotions.id = Attraction_Promotion.promotion_id\r\n" + "GROUP BY promotions.id\r\n"
					+ "HAVING promotions.deleted = 0";
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				listapromotions.add(toPromotion(result, attractionDAO.findAll()));
			}
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return listapromotions;
	}

	private Promotion toPromotion(ResultSet result, List<Attraction> attractions) {
		List<Attraction> attractionsDePromotion = new ArrayList<Attraction>();
		try {
			String[] stringListaAttractions = result.getString("attractions_ids").split(",");
			for (int i = 0; i < stringListaAttractions.length; i++)
				attractionsDePromotion.add(buscarAttraction(stringListaAttractions[i], attractions));
			if (result.getString("type").equals("AxB"))
				return new PromocionAxB(result.getInt("id"), result.getString("name"), result.getString("type"),
						attractionsDePromotion, findattractionsGratis(result.getInt("id"), attractions),
						result.getString("description"), result.getString("image"));
			if (result.getString("type").equals("Porcentual"))
				return new PromocionPorcentual(result.getInt("id"), result.getString("name"), result.getString("type"),
						result.getDouble("discount"), attractionsDePromotion, result.getString("description"),
						result.getString("image"));
			if (result.getString("type").equals("Absoluta"))
				return new PromocionAbsoluta(result.getInt("id"), result.getString("name"), result.getString("type"),
						result.getDouble("cost"), attractionsDePromotion, result.getString("description"),
						result.getString("image"));
			else
				return null;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private static Attraction buscarAttraction(String cadena, List<Attraction> attractions) {
		Attraction a = null;
		for (Attraction cadaAttraction : attractions) {
			if (String.valueOf(cadaAttraction.getId()).equals(cadena)) {
				a = cadaAttraction;
			}
		}
		return a;
	}

	private List<Attraction> findattractionsGratis(Integer promoId, List<Attraction> attractions) {
		List<Attraction> listaattractionsGratis = new ArrayList<Attraction>();
		try {
			String sql = "SELECT group_concat(gratuita_promoaxb.atraccion_id) AS 'atracciones_gratis'\r\n"
					+ "FROM gratuita_promoaxb\r\n" + "JOIN promotions\r\n"
					+ "ON gratuita_promoaxb.promocion_id = promotions.id\r\n" + "GROUP BY promotions.id \r\n"
					+ "HAVING promotions.id = ? ";
			// + "and promotions.deleted = 0";
			Connection connection = ConnectionProvider.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, promoId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				listaattractionsGratis.addAll(getattractionsGratis(result, attractions));
			}
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return listaattractionsGratis;
	}

	private List<Attraction> getattractionsGratis(ResultSet result, List<Attraction> attractions) {
		List<Attraction> attractionsDePromotionAXB = new ArrayList<Attraction>();
		try {
			String[] stringListaattractionsGratis = result.getString("atracciones_gratis").split(",");
			for (int i = 0; i < stringListaattractionsGratis.length; i++)
				attractionsDePromotionAXB.add(buscarAttraction(stringListaattractionsGratis[i], attractions));
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return attractionsDePromotionAXB;
	}

	public int insertPromoAbsoluta(PromocionAbsoluta PromotionAbsoluta) {
		int rows = 0;
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "INSERT INTO promotions (name, type, cost, description, image) VALUES(?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, PromotionAbsoluta.getName());
			statement.setString(2, "Absoluta");
			statement.setDouble(3, PromotionAbsoluta.getCost());
			statement.setString(4, PromotionAbsoluta.getDescription());
			statement.setString(5, PromotionAbsoluta.getImage());
			rows = statement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return rows;
	}

	public int updatePromoAbsoluta(PromocionAbsoluta PromotionAbsoluta) {
		int rows = 0;
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "UPDATE promotions SET name = ?, cost = ?, description = ?, image = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, PromotionAbsoluta.getName());
			statement.setDouble(2, PromotionAbsoluta.getCost());
			statement.setString(3, PromotionAbsoluta.getDescription());
			statement.setString(4, PromotionAbsoluta.getImage());
			statement.setInt(5, PromotionAbsoluta.getId());
			rows = statement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return rows;
	}

	public int updatePromoPorcentual(PromocionPorcentual PromocionPorcentual) {
		int rows = 0;
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "UPDATE promotions SET name = ?, discount = ?, description = ?, image = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, PromocionPorcentual.getName());
			statement.setDouble(2, PromocionPorcentual.getDiscount());
			statement.setString(3, PromocionPorcentual.getDescription());
			statement.setString(4, PromocionPorcentual.getImage());
			statement.setInt(5, PromocionPorcentual.getId());
			rows = statement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return rows;
	}

	public int updatePromoAxB(PromocionAxB PromocionAxB) {
		int rows = 0;
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "UPDATE promotions SET name = ?, description = ?, image=? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, PromocionAxB.getName());
			statement.setString(2, PromocionAxB.getDescription());
			statement.setString(3, PromocionAxB.getImage());
			statement.setInt(4, PromocionAxB.getId());
			rows = statement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return rows;
	}

	public int insertPromoPorcentual(PromocionPorcentual PromotionPorcentual) {
		int rows = 0;
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "INSERT INTO promotions (name, type, discount, description, image) VALUES(?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, PromotionPorcentual.getName());
			statement.setString(2, "Porcentual");
			statement.setDouble(3, PromotionPorcentual.getDiscount());
			statement.setString(4, PromotionPorcentual.getDescription());
			statement.setString(5, PromotionPorcentual.getImage());
			rows = statement.executeUpdate();

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return rows;
	}

	public int insertPromoAXB(PromocionAxB PromotionAxB) {
		int rows = 0;
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "INSERT INTO promotions (name, type, description, image) VALUES(?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, PromotionAxB.getName());
			statement.setString(2, "AxB");
			statement.setString(3, PromotionAxB.getDescription());
			statement.setString(4, PromotionAxB.getImage());
			rows = statement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return rows;
	}

	public int insertarAttractionsGratisAxB(Integer idAttraction, Integer idPromo) {
		int rows = 0;
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "INSERT INTO gratuita_promoaxb (atraccion_id, promocion_id) VALUES(?,?)  ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idAttraction);
			statement.setInt(2, idPromo);
			rows = statement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return rows;
	}

	public Integer findByName(String name) {
		Integer id = 0;
		try {
			String sql = "SELECT promotions.id from promotions WHERE promotions.name=?"; // and promotions.deleted = 0
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet resultados = statement.executeQuery();
			if (resultados.next()) {
				id = resultados.getInt("id");
			}
			return id;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public String findTypeById(Integer idPromo) {
		String type = "";
		try {
			String sql = "SELECT promotions.type from promotions WHERE promotions.id = ?"; // and promotions.deleted = 0
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idPromo);
			ResultSet resultados = statement.executeQuery();
			if (resultados.next()) {
				type = resultados.getString("type");
			}
			return type;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int insertAttractionById(Integer idAttraction, Integer idPromo) {
		int rows = 0;
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "INSERT INTO Attraction_Promotion (attraction_id, promotion_id) VALUES(?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idAttraction);
			statement.setInt(2, idPromo);
			rows = statement.executeUpdate();
			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int deleteAttractionById(Integer idPromo) {
		int rows = 0;
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "DELETE FROM Attraction_Promotion WHERE promotion_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idPromo);
			rows = statement.executeUpdate();
			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	public int deleteAttractionsGratisAxB(Integer idPromo) {
		int rows = 0;
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "DELETE FROM gratuita_promoaxb WHERE promocion_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idPromo);
			rows = statement.executeUpdate();
			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int UpdateAttractionById(Integer idAttraction, Integer idPromo) {
		int rows = 0;
		System.out.println(idAttraction+ " id promo " + idPromo);
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "UPDATE Attraction_Promotion SET attraction_id = ? WHERE promotion_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idAttraction);
			statement.setInt(2, idPromo);
			rows = statement.executeUpdate();
			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int UpdateAttractionsGratisAxB(Integer idAttraction, Integer idPromo) {
		int rows = 0;
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "UPDATE gratuita_promoaxb SET atraccion_id = ? WHERE promocion_id = ? ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, idAttraction);
			statement.setInt(2, idPromo);
			rows = statement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return rows;
	}

	public int delete(Promotion Promotion) {
		int rows = 0;
		try {
			Connection connection = ConnectionProvider.getConnection();
			String sql = "UPDATE promotions SET deleted = 1 WHERE ID = ? ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, Promotion.getId());
			rows = statement.executeUpdate();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		return rows;
	}

	public List<Integer> getAtraccionesIDEnPromo(int promoID) {
		List<Integer> listaAtraccionesID = new ArrayList<Integer>();

		try {
			String sql = "select attraction_promotion.attraction_id\r\n" + "from attraction_promotion\r\n"
					+ "where promotion_id =" + promoID;
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
		

	public int updateCupo(Promotion promotion) {
		int rows = 0;
		for (int atraccionID : getAtraccionesIDEnPromo(promotion.getId())) {
			String sql = "UPDATE attractions SET capacity = capacity - ? WHERE id = ?";
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
		return rows;
	}


}
