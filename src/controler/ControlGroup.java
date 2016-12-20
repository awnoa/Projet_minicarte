package controler;

import view.ViewApp;
import model.ModelApp;

public class ControlGroup {

	private ModelApp model;
	private ViewApp view;
	
	public ControlCheckBox controlCheckBox;
	
	public ControlGroup(ModelApp model) {
		this.model = model;
		view = new ViewApp(model);
		controlCheckBox = new ControlCheckBox(model, view);
		view.changeDisplay(true);
	}
}
