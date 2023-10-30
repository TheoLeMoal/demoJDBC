package fr.diginamic.jdbc.entites;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import fr.diginamic.jdbc.dao.DepartementDaoJdbc;
import fr.diginamic.jdbc.dao.RegionDaoJdbc;
import fr.diginamic.jdbc.dao.VilleDaoJdbc;

public class Recensement {

    /**
     * Constructeur
     */
    public Recensement() {
    }

    /**
     * Mettre les villes du fichier recencement-population.csv dans la base de données villes
     * @param cheminFichier
     */
    public void chargerDonnees(Path cheminFichier) {
    	long startTime = System.currentTimeMillis();
    	String url = "jdbc:mysql://localhost:3306/villes";
    	String user = "root";
    	String password = "";
        DepartementDaoJdbc depDao = null;
        RegionDaoJdbc regionDao = null;
        VilleDaoJdbc villeDao = null;
    	Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
	        depDao = new DepartementDaoJdbc(connection);
	        regionDao = new RegionDaoJdbc(connection);
	        villeDao = new VilleDaoJdbc(connection);
		    List<String> lines = Files.readAllLines(cheminFichier, StandardCharsets.UTF_8);
		    Iterator<String> iterator = lines.iterator();
		    iterator.next();
		    Departement newDepartement = null;
		    Region newRegion = null;

		    while (iterator.hasNext()) {
		        String target = iterator.next();
		        String[] targetSplit = target.split(";");
		        String nomRegion = targetSplit[1];
		        String codeDepartement = targetSplit[2];
		        String nomCommune = targetSplit[6];
		        int populationTotale = Integer.parseInt(targetSplit[9].trim().replaceAll(" ", ""));

		        // Vérifier si la région existe, sinon la créer
		        if (!regionDao.exists(nomRegion)) {
		            newRegion = new Region(0, nomRegion);
		            regionDao.insert(newRegion);
		        }
		        newRegion = regionDao.extraireParNom(nomRegion);

		        // Vérifier si le département existe, sinon le créer
		        if (!depDao.exists(codeDepartement)) {
		            newDepartement = new Departement(0, codeDepartement);
		            depDao.insert(newDepartement);
		        }
		        newDepartement = depDao.extraireParCode(codeDepartement);

		        // Créer une nouvelle ville
		        if (!villeDao.exists(nomCommune)) {
		            Ville targetCity = new Ville(0, nomCommune, populationTotale, newDepartement.getId(), newRegion.getId());
		            villeDao.insert(targetCity);
		        }
		    }
		} catch (IOException | SQLException e) {
		    e.printStackTrace();
		}
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Temps nécessaire pour mettre les données en base de données : " + elapsedTime + " millisecondes");
    }
}
