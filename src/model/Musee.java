package model;

public class Musee extends PointInteret {

	String adresse;
	String acces;
	String telephone;
	String email;
	String siteInternet;
	String facebook;


	public Musee(float latitude, float longitude, int departement, String commune, int numInsee, String nom,
			String description, String adresse, String acces, String telephone, String email, String siteInternet, String facebook,
			int codePostal) {
		super(latitude, longitude, departement, commune, numInsee, nom, description);
		this.adresse = adresse;
		this.acces = acces;
		this.telephone = telephone;
		this.email = email;
		this.siteInternet = siteInternet;
		this.facebook = facebook;
		this.codePostal = codePostal;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getAcces() {
		return acces;
	}
	public void setAcces(String acces) {
		this.acces = acces;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSiteInternet() {
		return siteInternet;
	}
	public void setSiteInternet(String siteInternet) {
		this.siteInternet = siteInternet;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	int codePostal;

}
