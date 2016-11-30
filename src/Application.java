import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

import view.MapView;
import model.CsvReader;
import model.MapModel;

public class Application {

	public static void main(String args[]) {
		MapModel model = new MapModel();
		MapView app = new MapView(model);;
	}
	/*
	public static void main(String args[]){
		CsvReader reader = new CsvReader();
		HashMap<String, ArrayList<String>> museesArray = reader.read(System.getProperty("user.dir")+"/data/MonumentsHistoriquesFrancheComte.csv");
		System.out.println(museesArray);
	}
	*/
}
