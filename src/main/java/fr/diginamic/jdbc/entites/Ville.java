package fr.diginamic.jdbc.entites;

public class Ville {
    private int id;
    private String nom;
    private int population;
    private int idDept;
    private int idRegion;
    
	/**
	 * @param id
	 * @param nom
	 * @param population
	 * @param idDept
	 * @param idRegion
	 */
	public Ville(String nom, int population, int idDept, int idRegion) {
		super();
		this.nom = nom;
		this.population = population;
		this.idDept = idDept;
		this.idRegion = idRegion;
	}
	
	
	
	/**
	 * @param id
	 * @param nom
	 * @param population
	 * @param idDept
	 * @param idRegion
	 */
	public Ville(int id, String nom, int population, int idDept, int idRegion) {
		super();
		this.id = id;
		this.nom = nom;
		this.population = population;
		this.idDept = idDept;
		this.idRegion = idRegion;
	}



	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}
	
	/**
	 * @param population the population to set
	 */
	public void setPopulation(int population) {
		this.population = population;
	}
	
	/**
	 * @return the idDept
	 */
	public int getIdDept() {
		return idDept;
	}
	
	/**
	 * @param idDept the idDept to set
	 */
	public void setIdDept(int idDept) {
		this.idDept = idDept;
	}
	
	/**
	 * @return the idRegion
	 */
	public int getIdRegion() {
		return idRegion;
	}
	
	/**
	 * @param idRegion the idRegion to set
	 */
	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}
    
    
}