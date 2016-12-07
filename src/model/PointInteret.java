package model;

public class PointInteret {
	
	float latitude ;
	float longitude	;
	int departement;
	String commune ;
	int numInsee ;
	String nom ;
	String description;
	String categorie;
	
	
	public PointInteret() {
	}
	
	public PointInteret(float latitude, float longitude, int departement, String commune, int numInsee, String nom,
			String description) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.departement = departement;
		this.commune = commune;
		this.numInsee = numInsee;
		this.nom = nom;
		this.description = description;
	}
	
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public int getDepartement() {
		return departement;
	}
	public void setDepartement(int departement) {
		this.departement = departement;
	}
	public String getCommune() {
		return commune;
	}
	public void setCommune(String commune) {
		this.commune = commune;
	}
	public int getNumInsee() {
		return numInsee;
	}
	public void setNumInsee(int numInsee) {
		this.numInsee = numInsee;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
