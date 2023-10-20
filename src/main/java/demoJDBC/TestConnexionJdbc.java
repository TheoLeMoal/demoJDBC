package demoJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexionJdbc {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/compta";
		String user = "root";
		String password = "";
		Connection connection = null;
		try {
			// Établissez la connexion à la base de données
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connexion a la base de données 'compta' établie.");
			
		} catch (SQLException e) {
			System.err.println("Erreur lors de la connexion a la base de données 'compta'.");
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}finally {
			// Fermez la connexion
			if (connection != null) {
				try {
					connection.close();
					System.out.println("Connexion à la base de données 'compta' fermé.");
				} catch (SQLException e) {
					System.err.println(e);
				}
			}
		}
	}

}
