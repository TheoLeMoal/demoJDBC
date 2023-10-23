package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.entites.Ville;

public class VilleDaoJdbc implements VilleDao{
	private Connection connection;
	
	/**
	 * @param connection
	 */
	public VilleDaoJdbc(Connection connection) {
			this.connection = connection;
	}
	
	@Override
	public List<Ville> extraire() {
        List<Ville> villes = new ArrayList<>();
        String query = "SELECT * FROM ville";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
            	Ville ville = new Ville(result.getInt("id"), result.getString("nom"), result.getInt("population"), result.getInt("idDept"), result.getInt("idRegion"));
                villes.add(ville);
            }
        } catch (SQLException e) {
        	System.err.println("Erreur lors de l'extraction des villes");
		}
        return villes;
	}
	
	@Override
	public Ville extraireParId(int id) {
        String query = "SELECT * FROM ville WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Ville ville = new Ville(result.getInt("id"), result.getString("nom"), result.getInt("population"), result.getInt("idDept"), result.getInt("idRegion"));
                    return ville;
                }
            } catch (SQLException e) {
            	System.err.println("Erreur lors de l'extraction de la ville");
			}
        } catch (SQLException e1) {
        	System.err.println("Erreur lors de l'extraction de la ville");
		}
        return null;
	}

	@Override
	public void insert(Ville ville) {
        String query = "INSERT INTO ville (id, nom, population, idDepartement, idRegion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setInt(1, ville.getId());
            statement.setString(2, ville.getNom());
            statement.setInt(3, ville.getPopulation());
            statement.setInt(4, ville.getIdDept());
            statement.setInt(5, ville.getIdRegion());
            statement.executeUpdate();
        } catch (SQLException e) {
			System.err.println("Erreur lors de l'insertion de la ville" + e.getMessage());
		}
	}

	@Override
	public void update(Ville ville) {
        String query = "UPDATE ville SET nom = ?, population = ?, idDepartement = ?, idRegion = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ville.getNom());
            statement.setInt(2, ville.getPopulation());
            statement.setInt(3, ville.getIdDept());
            statement.setInt(4, ville.getIdRegion());
            statement.setInt(5, ville.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
        	System.err.println("Erreur lors de la modification de la ville");
		}
	}

	@Override
	public void delete(int id) {
        String query = "DELETE FROM ville WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
        	System.err.println("Erreur lors de la suppréssion de la ville");
		}
	}
	
	@Override
    public boolean exists(String nom) {
        String query = "SELECT COUNT(*) FROM ville WHERE nom = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setString(1, nom);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    int count = result.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
