package model;

import java.util.HashMap;

public abstract class CritereRecherche {
	HashMap<String, Object> criteres;

	public CritereRecherche(HashMap<String, Object> criteres) {
		this.criteres = criteres;
	}
	public boolean checkCritere(HashMap<String, Object> criteres) {
		return false;
	}
	public HashMap<String, Object> getCriteres() {
		return criteres;
	}
}
