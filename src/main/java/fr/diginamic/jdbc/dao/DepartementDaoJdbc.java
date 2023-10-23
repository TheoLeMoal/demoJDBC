/**
 * 
 */
package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import fr.diginamic.jdbc.entites.Departement;

/**
 * 
 */
public class DepartementDaoJdbc implements DepartementDao{
	private Connection connection;
	
	/**
	 * @param connection
	 */
	public DepartementDaoJdbc(Connection connection) {
			this.connection = connection;
	}
	
	@Override
	public List<Departement> extraire() {
        List<Departement> departements = new ArrayList<>();
        String query = "SELECT * FROM departement";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
            	Departement departement = new Departement(result.getInt("id"), result.getString("code"));
            	departements.add(departement);
            }
        } catch (SQLException e) {
        	System.err.println("Erreur lors de l'extraction des departements");
		}
        return departements;
	}

	@Override
	public Departement extraireParId(int id) {
        String query = "SELECT * FROM departement WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                	Departement departement = new Departement(result.getInt("id"), result.getString("code"));
                    return departement;
                }
            } catch (SQLException e) {
            	System.err.println("Erreur lors de l'extraction du departement");
			}
        } catch (SQLException e1) {
        	System.err.println("Erreur lors de l'extraction du departement");
		}
        return null;
	}
	
	public Departement extraireParCode(String code) {
        String query = "SELECT * FROM departement WHERE code = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, code);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                	Departement departement = new Departement(result.getInt("id"), result.getString("code"));
                    return departement;
                }
            } catch (SQLException e) {
            	System.err.println("Erreur lors de l'extraction du departement");
			}
        } catch (SQLException e1) {
        	System.err.println("Erreur lors de l'extraction du departement");
		}
        return null;
	}

	@Override
	public void insert(Departement departement) {
        String query = "INSERT INTO departement (id, code) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	statement.setInt(1, departement.getId());
            statement.setString(2, departement.getCode());
            statement.executeUpdate();
        } catch (SQLException e) {
			System.err.println("Erreur lors de l'insertion du departement");
		}
	}

	@Override
	public void update(Departement departement) {
        String query = "UPDATE departement SET code = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, departement.getCode());
            statement.setInt(2, departement.getId());
 
            statement.executeUpdate();
        } catch (SQLException e) {
        	System.err.println("Erreur lors de la modification du departement");
		}
	}

	@Override
	public void delete(int id) {
        String query = "DELETE FROM departement WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
        	System.err.println("Erreur lors de la suppréssion du departement");
		}
	}
	
	@Override
    public boolean exists(String code) {
        String query = "SELECT COUNT(*) FROM departement WHERE code = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, code);
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
