package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Musee extends PointInteret {

	String adresse;
	String acces;
	String telephone;
	String email;
	String siteInternet;
	String facebook;



	public Musee(HashMap<String, String> dataMusee) {
		
		this.categorie = "Musée";
		this.latitude = Float.valueOf(dataMusee.get("Latitude")); ;
		this.longitude = Float.valueOf(dataMusee.get("Longitude"));;
		this.departement = Integer.valueOf(dataMusee.get("CodePostal"));
		this.commune = dataMusee.get("Commune");
		this.numInsee = Integer.valueOf(dataMusee.get("NumInsee"));
		this.nom = dataMusee.get("Nom");
		this.description = dataMusee.get("DescriptifLong");
		this.adresse = dataMusee.get("Adresse");
		this.acces = dataMusee.get("Acces");
		this.telephone = dataMusee.get("Telephone");
		this.email = dataMusee.get("Courriel");
		this.siteInternet = dataMusee.get("SiteInternet");
		this.facebook = dataMusee.get("Facebook");
		this.codePostal = Integer.valueOf(dataMusee.get("CodePostal"));
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
