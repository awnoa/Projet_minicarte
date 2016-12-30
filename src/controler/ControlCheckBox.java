package controler;

import java.awt.event.*;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.*;
import view.ViewApp;
import model.CritereCategorie;
import model.ModelApp;

public class ControlCheckBox extends Control implements ActionListener {
	
	public ControlCheckBox(ModelApp model, ViewApp view) {
		super(model, view);
		view.setCheckBoxControler(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		JCheckBox cbSelected = (JCheckBox) e.getSource();
		String currentString = cbSelected.getText();
		HashMap<String, Object> categorie = new HashMap<>();
		categorie.put("categorie", currentString);
		
		if (currentString.equals("tout (dé)sélectionner")) {
			if (cbSelected.isSelected()) {
				// maj hashset categorie
				model.setHashCategorie(view.displayAllCategorie());
			} else {
				// maj hashset categorie
				view.hideAllCategorie();
			}
		} else {
			if (cbSelected.isSelected()) {
				// maj hashset categorie
				model.setHashCategorie(view.addCritereCategorie(currentString));
			} else {
				/// maj hashset categorie
				view.removeCritereCategorie(currentString);
			}
		}
		view.paintIcons();
	}
}