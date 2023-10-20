package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDaoJdbc {
    public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/compta";
		String user = "root";
		String password = "";
		Connection connection = null;
		FournisseurDaoJdbc fournisseurDao = null;
		try {
			// Établissez la connexion à la base de données
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			fournisseurDao = new FournisseurDaoJdbc(connection);
			System.out.println("Connexion a la base de données 'compta' établie.");

	        // Insérer un fournisseur
	        Fournisseur nouveauFournisseur = new Fournisseur(4, "France de matériaux");
	        fournisseurDao.insert(nouveauFournisseur);
	        
	        // Extraire et afficher la liste des fournisseurs
	        List<Fournisseur> fournisseurs = fournisseurDao.extraire();
	        for (Fournisseur fournisseur : fournisseurs) {
	            System.out.println("ID : " + fournisseur.getId() + ", Nom : " + fournisseur.getNom());
	        }
	        
	        // Modifier un fournisseur
	        fournisseurDao.update("France de matériaux", "France matériaux");
	        Fournisseur fournisseurModifier = nouveauFournisseur;
	        fournisseurModifier.setNom("France matériaux");
	        
	        // Extraire et afficher la liste des fournisseurs à nouveau
	        fournisseurs = fournisseurDao.extraire();
	        for (Fournisseur fournisseur : fournisseurs) {
	            System.out.println("ID : " + fournisseur.getId() + ", Nom : " + fournisseur.getNom());
	        }

	        // Supprimer un fournisseur
	        boolean deleteSuccess = fournisseurDao.delete(fournisseurModifier);

	        // Extraire et afficher la liste des fournisseurs à nouveau
	        fournisseurs = fournisseurDao.extraire();
	        for (Fournisseur fournisseur : fournisseurs) {
	            System.out.println("ID : " + fournisseur.getId() + ", Nom : " + fournisseur.getNom());
	        }
		}catch (SQLException | ClassNotFoundException e) {
			System.err.println("Erreur lors de la connexion a la base de données 'compta'.");
		}
    }
}