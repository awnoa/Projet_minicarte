import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

import controler.ControlApp;
import view.ViewApp;
import model.CsvReader;
import model.ModelApp;

public class App {

	public static void main(String args[]) {
		
		javax.swing.SwingUtilities.invokeLater( new Runnable() {
	          public void run() {
	        	  
	        	  ModelApp model = new ModelApp();
	    	      ControlApp controler = new ControlApp(model);
	        	 
	          }
		});
	}
}
