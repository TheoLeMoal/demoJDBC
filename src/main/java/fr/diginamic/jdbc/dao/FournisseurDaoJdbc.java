package fr.diginamic.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao{
	private Connection connection;
	
	/**
	 * @param connection
	 */
	public FournisseurDaoJdbc(Connection connection) {
			this.connection = connection;
	}

	@Override
	public List<Fournisseur> extraire() {
		List<Fournisseur> fournisseurs = new ArrayList<>();
		try {
			//Requête SQL pour extraire les fournisseurs
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * FROM FOURNISSEUR");
			System.out.println("\nSélection des données dans la base de données 'compta' réussie.");

            // Parcourez les résultats et créez des instances de Fournisseur
            while (results.next()) {
                int id = results.getInt("id");
                String nom = results.getString("nom");
                Fournisseur fournisseur = new Fournisseur(id, nom);
                fournisseurs.add(fournisseur);
            }
		} catch (Exception e) {
			System.err.println("\nErreur lors de la Suppression d'une donné dans la base de données 'compta'");
		}
		return fournisseurs;
	}

	@Override
	public void insert(Fournisseur fournisseur) {
		try {
			//Requête SQL pour ajouter un fournisseur
			PreparedStatement statement = connection.prepareStatement("INSERT INTO FOURNISSEUR(ID, NOM) VALUES (?, ?)");
			statement.setInt(1, fournisseur.getId());
			statement.setString(2, fournisseur.getNom());
			statement.executeUpdate();
			System.out.println("\nInsertion d'une donné dans la base de données 'compta' réussie.");
			try {
				statement.close();
			} catch (Exception e) {
				System.err.println("\nErreur lors de la fermeture du statement");
			}
		} catch (Exception e) {
			System.err.println("\nErreur lors de l'nsertion d'une donné dans la base de données 'compta'" + e.getMessage());
		}
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		try {
			//Requête SQL pour modifier un fournisseur
			PreparedStatement statement = connection.prepareStatement("UPDATE FOURNISSEUR SET NOM=? WHERE NOM=?");
			statement.setString(1, nouveauNom);
			statement.setString(2, ancienNom);
			statement.executeUpdate();
			System.out.println("\nModification d'une donné dans la table 'fournisseur' réussie.");
			try {
				statement.close();
			} catch (Exception e) {
				System.err.println("\nErreur lors de la fermeture du statement");
			}
		} catch (Exception e) {
			System.err.println("\nErreur lors de la modification d'une donné dans la base de données 'compta'");
		}
		return 0;
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		try {
			//Requête SQL pour supprimer un fournisseur
			PreparedStatement statement = connection.prepareStatement("DELETE FROM FOURNISSEUR WHERE ID=?");
			statement.setInt(1, fournisseur.getId());
			statement.executeUpdate();
			System.out.println("\nSuppression d'une donné dans la base de données 'compta' réussie.");
			try {
				statement.close();
			} catch (Exception e) {
				System.err.println("\nErreur lors de la fermeture du statement");
			}
		} catch (Exception e) {
			System.err.println("\nErreur lors de la Suppression d'une donné dans la base de données 'compta'");
		}
		return false;
	}

}
