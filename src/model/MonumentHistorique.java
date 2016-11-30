package model;

import java.sql.Date;

public class MonumentHistorique extends PointInteret {

	String proprietaire;
	Date date;
	String protection;
	String auteur;
	String siecle;


	public MonumentHistorique(float latitude, float longitude, int departement, String commune, int numInsee, String nom,
			String description, String proprietaire, Date date, String protection, String auteur, String siecle) {
		super(latitude, longitude, departement, commune, numInsee, nom, description);
		this.proprietaire = proprietaire;
		this.date = date;
		this.protection = protection;
		this.auteur = auteur;
		this.siecle = siecle;
	}
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
