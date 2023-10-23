package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.jdbc.entites.Ville;

public interface VilleDao {
	List<Ville> extraire();
	Ville extraireParId(int id);
	void insert(Ville ville);
	void update(Ville ville);
	void delete(int id);
	boolean exists(String nom);
}
