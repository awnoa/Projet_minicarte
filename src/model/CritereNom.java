package model;

import java.util.ArrayList;
import java.util.HashMap;

public class CritereNom extends CritereRecherche{

	
	
	public CritereNom(HashMap<String, Object> criteres) {
		super(criteres);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean checkCritere(HashMap<String, Object> criteres) {
		// TODO Auto-generated method stub
		String recherche = (String) criteres.get("recherche");
		String nom = (String) this.criteres.get("nom");
		
		return nom.toLowerCase().contains(recherche.toLowerCase()) ;
	}
}
