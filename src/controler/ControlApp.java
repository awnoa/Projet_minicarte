package controler;

import view.ViewApp;
import model.ModelApp;

public class ControlApp {

	private ModelApp model;
	private ViewApp view;
	
	public ControlCheckBox controlCheckBox;
	public ControlSpinner controlSpinner;
	
	public ControlApp(ModelApp model) {
		this.model = model;
		view = new ViewApp(model);
		controlCheckBox = new ControlCheckBox(model, view);
		controlSpinner = new ControlSpinner(model, view);
		view.changeDisplay(true);
	}
}
