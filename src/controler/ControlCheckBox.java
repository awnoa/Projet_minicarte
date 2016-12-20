package controler;

import java.awt.event.*;
import javax.swing.*;
import view.ViewApp;
import model.ModelApp;

public class ControlCheckBox extends Control implements ActionListener {
	
	public ControlCheckBox(ModelApp model, ViewApp view) {
		super(model, view);
		view.setCheckBoxControler(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		JCheckBox cbSelected = (JCheckBox) e.getSource();
		String currentString = cbSelected.getText();
		if (currentString.equals("tout (dé)sélectionner")) {
			if (cbSelected.isSelected()) {
				view.displayAllCategorie();
			} else {
				view.hideAllCategorie();
			}
		} else {
			if (cbSelected.isSelected()) {
				view.displayCategorie(currentString);
			} else {
				view.hideCategorie(currentString);
			}
		}
		//setResultats();
	}
}