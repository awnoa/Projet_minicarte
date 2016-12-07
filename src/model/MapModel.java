package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MapModel {

	ArrayList<PointInteret> data;

	public ArrayList<PointInteret> getData() {
		return data;
	}
	CsvReader cr =  new CsvReader();

	public MapModel() {
		
		data = new ArrayList<>();
		createMusees();

	}

	void createMusees(){
		HashMap<String, ArrayList<String>> rawData;
		rawData = cr.read(System.getProperty("user.dir")+"/data/Musee.csv");
		int lineCount ;

		Iterator iter = rawData.keySet().iterator();

		String first = (String) iter.next();
		HashMap<String,String> dataMusee ;
		
		for (int i = 0; i < rawData.get(first).size(); i++) {
			dataMusee = new HashMap<>();

			for(String key : rawData.keySet()){
				dataMusee.put(key, rawData.get(key).get(i));
			}
			data.add(new Musee(dataMusee));
		}
	}
	void createMonuments(){
		HashMap<String, ArrayList<String>> rawData;
		rawData = cr.read(System.getProperty("user.dir")+"/data/Musee.csv");

	}
}
