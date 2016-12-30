package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ModelApp {

	// il faudrait que ce soit une hashset comme ça chaque element aurait un id entier
	// ou alors on rajoute un id dans pointinteret
	public int currentId;
	HashMap<Integer, PointInteret> data;

	public HashMap<String, ArrayList<PointInteret>> categories;

	// pour liste departements & communes
	public HashMap<String, HashSet<String>> localisation;

	// avec l'id de chaque eleent on pourrait tenir independement une liste
	// des points d'interets par criteres
	// et on n'a plus qu'a afficher l'intersection
	// donc les id present dans chaque liste
	// a l'utilsiaeur (avec un test de vide avant pour ne pas prendre en compte si jamais)
	public HashSet<Integer> idCritereCategories;
	public HashSet<Integer> idCritereLocalisation;
	public HashSet<Integer> idCritereNom;

	public HashMap<Integer, PointInteret> getData() {
		return data;
	}
	CsvReader cr =  new CsvReader();

	public ModelApp() {

		currentId = 0;
		data = new HashMap<>();

		categories = new HashMap<>();
		localisation = new HashMap<>();

		populateMusees();
		populateMonuments();
		idCritereCategories = new HashSet<>();
		idCritereNom = new HashSet<>();
		idCritereLocalisation = new HashSet<>();

	}

	public ArrayList<PointInteret> getCategorie(String key){
		return this.categories.get(key);
	}

	void populateMusees(){

		String tmpDep;

		HashMap<String, ArrayList<String>> rawData;
		rawData = cr.read(System.getProperty("user.dir")+"/data/Musee.csv");

		Iterator iter = rawData.keySet().iterator();

		String first = (String) iter.next();
		HashMap<String,String> dataMusee ;
		Musee musee;

		for (int i = 0; i < rawData.get(first).size(); i++) {
			dataMusee = new HashMap<>();

			for(String key : rawData.keySet()){
				dataMusee.put(key, rawData.get(key).get(i));
			}
			musee = new Musee(dataMusee);

			data.put(currentId, musee);
			currentId++;

			if(!categories.keySet().contains(musee.getCategorie())){
				ArrayList<PointInteret> pts = new ArrayList<>();
				pts.add(musee);
				categories.put(musee.getCategorie(), pts);
			}
			else {
				categories.get(musee.getCategorie()).add(musee);

			}

			tmpDep = Integer.toString(musee.getDepartement()).substring(0, 2);
			if(!localisation.keySet().contains(tmpDep)) {
				HashSet<String> communes = new HashSet<>();
				communes.add(musee.getCommune());
				localisation.put(tmpDep, communes);
			}
			else {
				localisation.get(tmpDep).add(musee.getCommune());
			}


		}
	}

	void populateMonuments(){

		String tmpDep;

		HashMap<String, ArrayList<String>> rawData;
		rawData = cr.read(System.getProperty("user.dir")+"/data/MonumentsHistoriquesFrancheComte.csv");
		Iterator iter = rawData.keySet().iterator();

		String first = (String) iter.next();
		HashMap<String,String> dataMonuments ;
		MonumentHistorique monumentHistorique;
		for (int i = 0; i < rawData.get(first).size(); i++) {
			dataMonuments = new HashMap<>();

			for(String key : rawData.keySet()){
				//System.out.println(key+":"+rawData.get(key).get(i));
				dataMonuments.put(key, rawData.get(key).get(i));
			}
			monumentHistorique = new MonumentHistorique(dataMonuments);

			data.put(currentId, monumentHistorique);
			currentId++;

			if(!categories.keySet().contains(monumentHistorique.getCategorie())){
				ArrayList<PointInteret> pts = new ArrayList<>();
				pts.add(monumentHistorique);
				categories.put(monumentHistorique.getCategorie(), pts);
			}
			else {
				categories.get(monumentHistorique.getCategorie()).add(monumentHistorique);
			}


			tmpDep = Integer.toString(monumentHistorique.getDepartement()).substring(0, 2);
			if(!localisation.keySet().contains(tmpDep)) {
				HashSet<String> communes = new HashSet<>();
				communes.add(monumentHistorique.getCommune());
				localisation.put(tmpDep, communes);
			}
			else {
				localisation.get(tmpDep).add(monumentHistorique.getCommune());
			}
		}
	}

	public void setHashCategorie(HashSet<Integer> addCritere) {
		idCritereCategories = addCritere;

	}

	public HashSet<Integer> mergeHashsets(){

		HashSet<Integer> ids = new HashSet<>();
		ids.addAll(idCritereCategories);
		ids.addAll(idCritereLocalisation);
		ids.addAll(idCritereNom);

		return ids;

	}

	public void addHashCategorie(HashSet<Integer> addCritereCategorie) {
		idCritereCategories.addAll(addCritereCategorie);
		
	}

	public void setHashNom(HashSet<Integer> addCritereNom) {
		idCritereNom = addCritereNom;

		
	}
}
