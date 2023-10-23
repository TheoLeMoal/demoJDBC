package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.entites.Departement;
import fr.diginamic.jdbc.entites.Region;

/**
 * 
 */
public class RegionDaoJdbc implements RegionDao{
	private Connection connection;
	
	/**
	 * @param connection
	 */
	public RegionDaoJdbc(Connection connection) {
			this.connection = connection;
	}
	
	@Override
	public List<Region> extraire() {
        List<Region> regions = new ArrayList<>();
        String query = "SELECT * FROM region";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
            	Region region = new Region(result.getInt("id"), result.getString("nom"));
            	regions.add(region);
            }
        } catch (SQLException e) {
        	System.err.println("Erreur lors de l'extraction des regions");
		}
        return regions;
	}

	@Override
	public Region extraireParId(int id) {
        String query = "SELECT * FROM region WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                	Region region = new Region(result.getInt("id"), result.getString("nom"));
                    return region;
                }
            } catch (SQLException e) {
            	System.err.println("Erreur lors de l'extraction de la region");
			}
        } catch (SQLException e1) {
        	System.err.println("Erreur lors de l'extraction de la region");
		}
        return null;
	}

	@Override
	public Region extraireParNom(String nom) {
        String query = "SELECT * FROM region WHERE nom = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nom);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                	Region region = new Region(result.getInt("id"), result.getString("nom"));
                    return region;
                }
            } catch (SQLException e) {
            	System.err.println("Erreur lors de l'extraction de la region");
			}
        } catch (SQLException e1) {
        	System.err.println("Erreur lors de l'extraction de la region");
		}
        return null;
	}
	
	@Override
	public void insert(Region region) {
        String query = "INSERT INTO region (id, nom) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setInt(1, region.getId());
            statement.setString(2, region.getNom());
            statement.executeUpdate();
        } catch (SQLException e) {
			System.err.println("Erreur lors de l'insertion de la region");
		}
	}

	@Override
	public void update(Region region) {
        String query = "UPDATE region SET nom = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, region.getNom());
            statement.setInt(2, region.getId());
 
            statement.executeUpdate();
        } catch (SQLException e) {
        	System.err.println("Erreur lors de la modification de la region");
		}
	}

	@Override
	public void delete(int id) {
        String query = "DELETE FROM region WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
        	System.err.println("Erreur lors de la suppréssion de la region");
		}
	}
	
	@Override
    public boolean exists(String nom) {
        String query = "SELECT COUNT(*) FROM region WHERE nom = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nom);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
