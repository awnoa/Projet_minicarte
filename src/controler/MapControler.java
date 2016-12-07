package controler;

import model.MapModel;
import view.MapView;

public class MapControler {
	
	MapModel model;
	MapView app ;
	
	
	public MapControler() {
		model = new MapModel();
		app = new MapView(model);
		
	}
}
