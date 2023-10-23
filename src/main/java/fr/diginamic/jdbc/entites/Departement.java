package fr.diginamic.jdbc.entites;

public class Departement {
    private int id;
    private String code;
    
	/**
	 * @param id
	 * @param code
	 */
	public Departement(String code) {
		super();
		this.code = code;
	}
	
	public Departement(int id, String code) {
		this.id = id;
		this.code = code;
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Departement [id=" + id + ", code=" + code + "]";
	}
    
    
}
