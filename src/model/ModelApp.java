package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ModelApp {

	ArrayList<PointInteret> data;
	public HashMap<String, ArrayList<PointInteret>> categories;
	public HashSet<String> communes; // test

	public ArrayList<PointInteret> getData() {
		return data;
	}
	CsvReader cr =  new CsvReader();

	public ModelApp() {

		data = new ArrayList<>();
		categories = new HashMap<>();
		communes = new HashSet<String>();

		populateMusees();
		populateMonuments();
		
		System.out.println(communes.size());

	}

	public ArrayList<PointInteret> getCategorie(String key){
		return this.categories.get(key);
	}
	void populateMusees(){
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
			
			communes.add(musee.getCommune());

		}
	}

	void populateMonuments(){

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
			communes.add(monumentHistorique.getCommune());
		}
	}
}
