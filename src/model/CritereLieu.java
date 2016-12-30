package model;

import java.util.ArrayList;
import java.util.HashMap;

public class CritereLieu extends CritereRecherche {

	public CritereLieu(HashMap<String, Object> criteres) {
		super(criteres);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean checkCritere(HashMap<String, Object> criteres) {
		// TODO Auto-generated method stub
		float latitudeMax = (float) this.criteres.get("latitudeMax");
		float latitudeMin = (float) this.criteres.get("latitudeMin");
		float latitude =  (float) criteres.get("latitude");
		String commune = (String) criteres.get("commune");
		ArrayList<String> communes = (ArrayList<String>) this.criteres.get("communes");
		
		return latitude <= latitudeMax && latitude >= latitudeMin &&
				commune.contains(commune) ;
	}
	

}
