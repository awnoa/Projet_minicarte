package controler;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import view.ViewApp;
import model.ModelApp;

public class ControlSpinner extends Control implements ChangeListener {

	public ControlSpinner(ModelApp model, ViewApp view) {
		super(model, view);
		view.setSpinnerControler(this);
	}
	
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == view.getSpinnerLatitudeMin()) {
			System.out.println("1");
		} else if (e.getSource() == view.getSpinnerLatitudeMax()) {
			System.out.println("2");
		} else if (e.getSource() == view.getSpinnerLongitudeMin()) {
			System.out.println("3");
		} else if (e.getSource() == view.getSpinnerLongitudeMax()) {
			System.out.println("4");
		} else {
			// rien
		}
		System.out.println(((JSpinner) e.getSource()).getValue());
	}
}
