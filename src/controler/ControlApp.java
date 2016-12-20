package controler;

import model.ModelApp;
import view.ViewApp;

public class ControlApp {
	
	ModelApp model;
	ViewApp app ;
	
	
	public ControlApp() {
		model = new ModelApp();
		app = new ViewApp(model);
		
	}
}
