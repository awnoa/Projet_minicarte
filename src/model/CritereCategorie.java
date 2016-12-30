package model;

import java.util.HashMap;

public class CritereCategorie extends CritereRecherche{

	public CritereCategorie(HashMap<String, Object> criteres) {
		super(criteres);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean checkCritere(HashMap<String, Object> criteres) {
		// TODO Auto-generated method stub
		String categorie = (String) criteres.get("categorie");
		System.out.println(categorie.equals(this.criteres.get("categorie")));
		return categorie.equals(this.criteres.get("categorie"));
	}
	
}
