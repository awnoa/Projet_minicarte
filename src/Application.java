import java.util.ArrayList;
import java.util.HashMap;

import model.CsvReader;

public class Application {

	
	public static void main(String args[]){
		CsvReader reader = new CsvReader();
		HashMap<String, ArrayList<String>> museesArray = reader.read(System.getProperty("user.dir")+"/src/Musee.csv");
		System.out.println(museesArray);
	}
}
