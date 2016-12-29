package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ModelApp {

	// il faudrait que ce soit une hashset comme ça chaque element aurait un id entier
	// ou alors on rajoute un id dans pointinteret
	ArrayList<PointInteret> data;
	
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
	
	public ArrayList<PointInteret> getData() {
		return data;
	}
	CsvReader cr =  new CsvReader();

	public ModelApp() {

		data = new ArrayList<>();
		
		categories = new HashMap<>();
		localisation = new HashMap<>();
		
		populateMusees();
		populateMonuments();

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

			data.add(musee);
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
			data.add(monumentHistorique);
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
}
