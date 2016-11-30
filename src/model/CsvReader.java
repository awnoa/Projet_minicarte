package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CsvReader {

	
	BufferedReader br ;
	
	public CsvReader() {
		br = null;
	}
	
	public HashMap<String, ArrayList<String>> read(String filename){
		HashMap<String, ArrayList<String>> res = new HashMap<>();
		
		try {

			String sCurrentLine;
			boolean first = false;
			br = new BufferedReader(new FileReader(filename));
			String keys[] = null;
			String lineSplitted[] = null;
			while ((sCurrentLine = br.readLine()) != null) {
				if(!first){
					keys = sCurrentLine.split("\\t");
					for (int i = 0; i < keys.length; i++) {
						res.put(keys[i], new ArrayList<String>());
						sCurrentLine.split("\\t");						
					}
					first = true;
				}
				else {
					lineSplitted = sCurrentLine.split("\\t");
					for (int i = 0; i < lineSplitted.length; i++) {
						res.get(keys[i]).add(lineSplitted[i]);
					}
				}
			}
			return res;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}	
		return null;
	}
}
