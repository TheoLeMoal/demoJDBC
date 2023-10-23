package fr.diginamic.jdbc.entites;

public class Region {
    private int id;
    private String nom;
    
	/**
	 * @param id
	 * @param nom
	 */
	public Region(String nom) {
		super();
		this.nom = nom;
	}
	
	/**
	 * @param id
	 * @param nom
	 */
	public Region(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
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
    
    
}