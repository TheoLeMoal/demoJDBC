package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {

	public static void main(String[] args) {
		List<Fournisseur> fournisseurs = new ArrayList<>();
		String url = "jdbc:mysql://localhost:3306/compta";
		String user = "root";
		String password = "";
		Connection connection = null;
		
		try {
			// Établissez la connexion à la base de données
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connexion a la base de données 'compta' établie.");
			try {
				//Requête SQL pour extraire les fournisseurs
				Statement statement = connection.createStatement();
				ResultSet results = statement.executeQuery("SELECT * FROM FOURNISSEUR");
				System.out.println("Suppression d'une donné dans la base de données 'compta' réussie.");

                // Parcourez les résultats et créez des instances de Fournisseur
                while (results.next()) {
                    int id = results.getInt("id");
                    String nom = results.getString("nom");
                    Fournisseur fournisseur = new Fournisseur(id, nom);
                    fournisseurs.add(fournisseur);
                }
                
                // Parcourez la liste des fournisseurs et affichez-les
                for (Fournisseur fournisseur : fournisseurs) {
                    System.out.println("ID: " + fournisseur.getId() + ", Nom: " + fournisseur.getNom());
                }
			} catch (Exception e) {
				System.err.println("Erreur lors de la Suppression d'une donné dans la base de données 'compta'");
			}
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
