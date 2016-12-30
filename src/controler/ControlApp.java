package controler;

import view.ViewApp;
import model.ModelApp;

public class ControlApp {

	private ModelApp model;
	private ViewApp view;
	
	public ControlCheckBox controlCheckBox;
	public ControlSpinner controlSpinner;
	public ControlList controlList;
	public ControlButton controlButton;
	public ControlMap controlMap;
	
	public ControlApp(ModelApp model) {
		this.model = model;
		view = new ViewApp(model);
		controlCheckBox = new ControlCheckBox(model, view);
		controlSpinner = new ControlSpinner(model, view);
		controlList = new ControlList(model, view);
		controlButton = new ControlButton(model, view);
		controlMap = new ControlMap(model, view);
		view.changeDisplay(true);
	}
}
