package controler;

import java.awt.Rectangle;
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
		view.getMap().displaySelection(new Rectangle(x-5,y-5,10,10));
		view.resetPanInformations();
		
		for(PointInteretView p : view.getDisplayedPoints()){
			if(p.getPosX()<=x+13&& p.getPosX()>=x-13)
				if(p.getPosY()<=y+13 && p.getPosY()>=y-13){
					view.getPanInformations().addPointInteret(p.getPointInformations());
				}
		}
		view.refreshMapInformations();
		view.repaint();

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
