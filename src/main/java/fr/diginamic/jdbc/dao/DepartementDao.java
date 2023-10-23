/**
 * 
 */
package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.jdbc.entites.Departement;

/**
 * 
 */
public interface DepartementDao {
	List<Departement> extraire();
	Departement extraireParId(int id);
	void insert(Departement departement);
	void update(Departement departement);
	void delete(int id);
	Departement extraireParCode(String code);
	boolean exists(String code);
}
