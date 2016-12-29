package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import model.ModelApp;
import view.ViewApp;

public class ControlButton extends Control implements ActionListener {

	public ControlButton(ModelApp model, ViewApp view) {
		super(model, view);
		view.setButtonControler(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.getButtonValider()) {
			System.out.println("recherche avec "+view.getSaisieNom().getText());
			// maj idCritereNom
			//setResultats(); // intersection des trois hashset
		} else if (e.getSource() == view.panInfoPointsInterets.getButPrecedent()) {
			System.out.println("précédent");
			// set data affiché
		} else if (e.getSource() == view.panInfoPointsInterets.getButSuivant()) {
			System.out.println("suivant");
			// set data affiché
		}
	}
}
