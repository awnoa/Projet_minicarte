package controler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.ModelApp;
import model.PointInteret;
import view.PointInteretView;
import view.ViewApp;

public class ControlMap extends Control implements MouseListener {

	public ControlMap(ModelApp model, ViewApp view) {
		super(model, view);
		view.setMapControler(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		
		view.resetPanInformations();
		
		for(PointInteretView p : view.getDisplayedPoints()){
			if(p.getPosX()<=x+10 && p.getPosX()>=x-10)
				if(p.getPosY()<=y+10 && p.getPosY()>=y-10){
					view.getPanInformations().addPointInteret(p.getPointInformations());
				}
		}
		view.refreshMapInformations();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
