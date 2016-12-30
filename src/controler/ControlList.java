package controler;

import java.awt.event.*;
import java.util.HashSet;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import view.ViewApp;
import model.ModelApp;

public class ControlList extends Control implements ListSelectionListener {

	private boolean toutEstSelectionne;
	
	public ControlList(ModelApp model, ViewApp view) {
		super(model, view);
		view.setListControler(this);
		toutEstSelectionne = false;
	}
	
	public void valueChanged(ListSelectionEvent e) {
		//
		if (e.getSource() == view.getListDepartements()) {
			
			JList list = view.getListDepartements();
			HashSet<String> hash = new HashSet<>();
			
			for (Integer value : list.getSelectedIndices()) {
				String element = Integer.toString(Integer.parseInt((String)list.getModel().getElementAt(value)));
				hash.add(element);
			}
			
			DefaultListModel modelList = view.getListModelCommunes();
			modelList.removeAllElements();
			modelList.addElement("Tout");

			for (Entry<String, HashSet<String>> me : model.localisation.entrySet()) {
				if (hash.contains(me.getKey())) {
					for (String s : me.getValue()) {
				    	  modelList.addElement(s);
				    }
				}
			}
			
		} else if (e.getSource() == view.getListCommunes()) {
			
			// si tout est sélectionné
			JList list = view.getListCommunes();
			if (toutEstSelectionne && list.getSelectedIndex() == 0) {
				list.removeSelectionInterval(0, list.getModel().getSize());
				toutEstSelectionne = false;
				list.removeSelectionInterval(0, 0);
			} else if (!toutEstSelectionne && list.getSelectedIndex() == 0) {
				list.setSelectionInterval(0, list.getModel().getSize());
				toutEstSelectionne = true;
				list.removeSelectionInterval(0, 0);
			} 
			
			// à chaque fois on reprend on met à jour idCritereLocalisation avec élement sélectionné
		}
		//setResultats(); // intersection des trois hashset
	}
	
}
