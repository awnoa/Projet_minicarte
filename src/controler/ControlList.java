package controler;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.ViewApp;
import model.ModelApp;

public class ControlList extends Control implements ListSelectionListener {

	
	public ControlList(ModelApp model, ViewApp view) {
		super(model, view);
		view.setListControler(this);
	}
	
	public void valueChanged(ListSelectionEvent e) {
		//
		if (e.getSource() == view.getListDepartements()) {
			//
		} else if (e.getSource() == view.getListCommunes()) {
			// si tout est sélectionné
			JList list = view.getListCommunes();
			if (list.getSelectedIndex() == 0) {
				list.setSelectionInterval(0, list.getModel().getSize());
			} else if (false){
				// si on deselectionne tout
			}
			// à chaque fois on reprend on met à jour idCritereLocalisation avec élement sélectionné
		}
		//setResultats(); // intersection des trois hashset
	}
	
}
