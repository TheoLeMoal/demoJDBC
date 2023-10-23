package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.jdbc.entites.Region;

public interface RegionDao {
	List<Region> extraire();
	Region extraireParId(int id);
	Region extraireParNom(String nom);
	void insert(Region region);
	void update(Region region);
	void delete(int id);
	boolean exists(String nom);
}
