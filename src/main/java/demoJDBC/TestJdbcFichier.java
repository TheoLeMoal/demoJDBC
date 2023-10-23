package demoJDBC;

import java.nio.file.Path;
import java.nio.file.Paths;

import fr.diginamic.jdbc.entites.Recensement;

public class TestJdbcFichier {

	public static void main(String[] args) {
		Recensement recencement = new Recensement();
		Path path = Paths.get("E:\\Projets java\\demoJDBC\\src\\main\\resources\\recensement-population.csv");
		recencement.chargerDonnees(path);
	}

}
