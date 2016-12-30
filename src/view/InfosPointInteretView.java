package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.PointInteret;

public class InfosPointInteretView extends Panel {

	private JLabel labLatitude;
	private JLabel labLongitude;
	private JLabel labDepartement;
	private JLabel labCommune;
	private JLabel labNumInsee;
	private JLabel labNom;
	private JLabel labDescription;
	private JLabel labCategorie;

	private JButton butPrecedent;
	private JButton butSuivant;

	private int currentIndex;
	private PointInteret currentPointInteret;
	private ArrayList<PointInteret> pointInterets;

	public InfosPointInteretView() {
		super(new BorderLayout());
		pointInterets = new ArrayList<>();
		currentIndex = -1;
		initAttributs();
		panelTableau();
		this.add(butPrecedent, BorderLayout.WEST);
		this.add(butSuivant, BorderLayout.EAST);
		this.add(new JLabel("Informations"), BorderLayout.NORTH);

	}

	public void initAttributs() {
		labLatitude = new JLabel("___");
		labLongitude = new JLabel("___");
		labDepartement = new JLabel("___");
		labCommune = new JLabel("___");
		labNumInsee = new JLabel("___");
		labNom = new JLabel("___");
		labDescription = new JLabel("___");
		labCategorie = new JLabel("___");

		butPrecedent = new JButton("<<");
		butSuivant = new JButton(">>");
	}

	public void panelTableau() {
		JPanel panTab = new JPanel(new GridLayout(8, 2));

		panTab.add(new JLabel("Categorie :"));
		panTab.add(labCategorie);

		panTab.add(new JLabel("Nom :"));
		panTab.add(labNom);

		panTab.add(new JLabel("Description :"));
		panTab.add(labDescription);

		panTab.add(new JLabel("N° Insee :"));
		panTab.add(labNumInsee);

		panTab.add(new JLabel("Département :"));
		panTab.add(labDepartement);

		panTab.add(new JLabel("Commune :"));
		panTab.add(labCommune);

		panTab.add(new JLabel("Latitude :"));
		panTab.add(labLatitude);

		panTab.add(new JLabel("Longitude :"));
		panTab.add(labLongitude);

		this.add(panTab, BorderLayout.CENTER);
	}

	public void setButtonControler(ActionListener listener) {
		butPrecedent.addActionListener(listener);
		butSuivant.addActionListener(listener);
	}

	public void setLabLatitude(String strLatitude) {
		this.labLatitude.setText(strLatitude);
	}

	public void setLabLongitude(String strLongitude) {
		this.labLongitude.setText(strLongitude);
	}

	public void setLabDepartement(String strDepartement) {
		this.labDepartement.setText(strDepartement);
	}

	public void setLabCommune(String strCommune) {
		this.labCommune.setText(strCommune);
	}

	public void setLabNumInsee(String strNumInsee) {
		this.labNumInsee.setText(strNumInsee);
	}

	public void setLabNom(String strNom) {
		this.labNom.setText(strNom);
	}

	public void setLabDescription(String strDescription) {
		this.labDescription.setText(strDescription);
	}

	public void setLabCategorie(String strCategorie) {
		this.labCategorie.setText(strCategorie);
	}

	public JButton getButPrecedent() {
		return butPrecedent;
	}

	public JButton getButSuivant() {
		return butSuivant;
	}
	public void resetListPointInteret(){
		pointInterets.clear();
		currentIndex = -1;
	}
	public void addPointInteret(PointInteret p) {
		pointInterets.add(p);
		currentIndex = 0;
	}
	public void displayNextPage() {
		if(pointInterets.size()>0){
			if(currentIndex<pointInterets.size()-1)
				currentIndex++;
		}
		refresh();
	}
	public void displayPreviousPage(){
		if(pointInterets.size()>0){
			if(currentIndex>0)
				currentIndex--;
		}
		refresh();
	}
	public void refresh(){
		if(currentIndex>=0){

			currentPointInteret = pointInterets.get(currentIndex);

			setLabCategorie(currentPointInteret.getCategorie());
			setLabCommune(currentPointInteret.getCommune());
			setLabDepartement(currentPointInteret.getDepartement()+"");
			setLabDescription(currentPointInteret.getDescription());
			setLabLatitude(currentPointInteret.getLatitude()+"");
			setLabLongitude(currentPointInteret.getLongitude()+"");
			setLabNom(currentPointInteret.getNom());
			setLabNumInsee(currentPointInteret.getNumInsee()+"");
		}
	
	}

	public void reset() {
		resetListPointInteret();

		setLabCategorie("__");
		setLabCommune("__");
		setLabDepartement("__");
		setLabDescription("__");
		setLabLatitude("__");
		setLabLongitude("__");
		setLabNom("__");
		setLabNumInsee("__");

	}
}
