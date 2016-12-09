package model;

import java.sql.Date;
import java.util.HashMap;

public class MonumentHistorique extends PointInteret {

	String proprietaire;
	String date;
	String protection;
	String auteur;
	String siecle;


	
	public MonumentHistorique(HashMap<String, String> dataMonuments) {
		
		this.categorie = dataMonuments.get("catégorie");
		this.latitude = Float.valueOf(dataMonuments.get("latitude")); ;
		this.longitude = Float.valueOf(dataMonuments.get("longitude"));;
		this.departement = Integer.valueOf(dataMonuments.get("département"));
		this.commune = dataMonuments.get("commune");
		this.numInsee = Integer.valueOf(dataMonuments.get("INSEE"));
		this.nom = dataMonuments.get("designation");
		this.description = dataMonuments.get("description");
		this.proprietaire = dataMonuments.get("propriétaire");
		this.date = dataMonuments.get("date protection");
		this.protection = dataMonuments.get("date protection");
		this.auteur = dataMonuments.get("auteur(s)");
		this.siecle = dataMonuments.get("siècle");
	}
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getProtection() {
		return protection;
	}
	public void setProtection(String protection) {
		this.protection = protection;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getSiecle() {
		return siecle;
	}
	public void setSiecle(String siecle) {
		this.siecle = siecle;
	}

}
