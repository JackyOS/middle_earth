package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Attraction;
import persistence.AttractionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class AttractionDAOImpl implements AttractionDAO {

	public List<Attraction> findAll() {
		try {
			String sql = "SELECT * FROM ATTRACTIONS where deleted = 0";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Attraction> attractions = new LinkedList<Attraction>();
			while (resultados.next()) {
				attractions.add(toAttraction(resultados));
			}

			return attractions;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Attraction find(Integer id) {
		try {
			String sql = "SELECT * FROM ATTRACTIONS WHERE id = ? and deleted = 0";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			
			ResultSet resultados = statement.executeQuery();

			Attraction attraction = null;
			if (resultados.next()) {
				attraction = toAttraction(resultados);
			}

			return attraction;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Attraction toAttraction(ResultSet attractionRegister) throws SQLException {
		return new Attraction(attractionRegister.getInt(1), attractionRegister.getString(2),
				attractionRegister.getDouble(3), attractionRegister.getDouble(4), attractionRegister.getInt(5), attractionRegister.getString(6), attractionRegister.getString(7));
	}

	@Override
	public int insert(Attraction attraction) {
		try {
			String sql = "INSERT INTO ATTRACTIONS (NAME, COST, DURATION, CAPACITY,DESCRIPTION,IMAGE) VALUES (?, ?, ?, ?,?,?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, attraction.getName());
			statement.setDouble(i++, attraction.getCost());
			statement.setDouble(i++, attraction.getDuration());
			statement.setInt(i++, attraction.getCapacity());
			statement.setString(i++, attraction.getDescription());
			statement.setString(i++, attraction.getImage());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Attraction attraction) {
		try {
			String sql = "UPDATE ATTRACTIONS SET NAME = ?, COST = ?, DURATION = ?, CAPACITY = ?, DESCRIPTION = ?, IMAGE = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, attraction.getName());
			statement.setDouble(i++, attraction.getCost());
			statement.setDouble(i++, attraction.getDuration());
			statement.setInt(i++, attraction.getCapacity());
			statement.setString(i++, attraction.getDescription());
			statement.setString(i++, attraction.getImage());
			statement.setInt(i++, attraction.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int deleteSOLOATRACCION(Attraction attraction) {
		try {
			String sql = "UPDATE ATTRACTIONS SET deleted = 1 WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, attraction.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	
	
	@Override
	public int delete(Attraction attraction) {
		try {
			String sql1 = "UPDATE ATTRACTIONS SET deleted = 1 WHERE ID = ?";
			String sql2 = "UPDATE promotions\r\n"
						+ "SET deleted = 1\r\n"
						+ "WHERE id IN (select promotions.id\r\n"
						+ "FROM promotions\r\n"
						+ "JOIN attraction_promotion\r\n"
						+ "ON attraction_promotion.promotion_id =  promotions.id\r\n"
						+ "JOIN attractions\r\n"
						+ "ON attractions.id = attraction_promotion.attraction_id\r\n"
						+ "WHERE attractions.deleted = 1)\r\n";
			//String sql3 = "UPDATE attraction_promotion SET deleted = 1 WHERE attraction_id = ? "; hay que borrar las relaciones entre atraccion y promocion??
			
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement1 = conn.prepareStatement(sql1);
			statement1.setInt(1, attraction.getId());
			int rows1 = statement1.executeUpdate();

			PreparedStatement statement2 = conn.prepareStatement(sql2);
			int rows2 = statement2.executeUpdate();
			
			//PreparedStatement statement3 = conn.prepareStatement(sql3);
			//int rows3 = statement3.executeUpdate();
			//statement3.setInt(1, attraction.getId());
			return rows1 + rows2; // + rows3;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	
	

	public int deleteTotal(Attraction attraction) {
		try {
			String sql = "DELETE FROM ATTRACTIONS WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, attraction.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	
	public static int findId(String attractionName) {
		try {
			String sql = "SELECT id FROM ATTRACTIONS WHERE name = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString (1, attractionName);
			
			ResultSet resultados = statement.executeQuery();
			int attractionId = resultados.getInt("id");
			
			return attractionId;
			
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	
	

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM ATTRACTIONS";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}



}
