package view;

import model.MapModel;
import model.PointInteret;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Iterator;

public class MapView extends JFrame {

	private ImageIcon imageMap;
	private BufferedImage icon ;
	private JLabel iconLabel;
	private MapPanel labMap;
	private ArrayList<JCheckBox> categories;
	private MapModel model;
	private JTextField saisieNom;

	private MapPanel mapPaneltest;

	public MapView(MapModel model) {
		
		this.model = model;
		initAttribut();
		creerVue();
		setTitle("Carte interactive des musées et monuments historiques de Franche-Comté");
		setLocation(0, 0);
		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		changeDisplay(true);
	}

	public void initAttribut() {

		try {
			imageMap = new ImageIcon(System.getProperty("user.dir")+"/images/carte.jpg");
			icon = ImageIO.read(new File(System.getProperty("user.dir")+"/images/musee.png"));
			labMap = new MapPanel(imageMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initCategories();
	}
	
	private void initCategories() {
		
		categories = new ArrayList<>();
		for (Iterator iterator = model.categories.keySet().iterator(); iterator.hasNext();) {
			final String currentString = (String) iterator.next();
			//System.out.println(currentString);
			this.categories.add(new JCheckBox(currentString));

			this.categories.get(this.categories.size()-1).addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JCheckBox c = (JCheckBox) arg0.getSource();
					if(c.isSelected())
						displayCategorie(currentString);
					else
						hideCategorie(currentString);
						
				}
			});			
		}
		
		this.categories.add(new JCheckBox("tout (dé)sélectionner"));
		this.categories.get(this.categories.size()-1).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JCheckBox c = (JCheckBox) arg0.getSource();
				if(c.isSelected())
					displayAllCategorie();
				else
					hideAllCategorie();
					
			}
		});			
	}
	
	public void creerVue() {
		JPanel panAll = new JPanel();
		panAll.setLayout(new GridLayout(1, 2));
		panAll.add(coteGauche());
		panAll.add(coteDroite());
		setContentPane(panAll);
	}

	private JPanel coteGauche()
	{
		
		JPanel panMap = new JPanel();
		panMap.setLayout(new BorderLayout());
		panMap.add(labMap);
		
		return panMap;
	}
	
	private JPanel coteDroite()
	{
		JPanel panDroite = new JPanel(new GridLayout(2, 1));
		
		panDroite.add(creerPanCriteres());
		panDroite.add(creerPanInformations());
		
		return panDroite;
	}
	
	private JTabbedPane creerPanCriteres()
	{
		
		JTabbedPane panTab = new JTabbedPane();
		panTab.addTab("Catégorie", null, creerOngletCategorie(), "Sélection avec la catégorie");
		panTab.addTab("Lieu", null, creerOngletLieu(), "Sélection avec le lieu");
		panTab.addTab("Nom", null, creerOngletNom(), "Sélection avec le nom");
		
		return panTab;
	}
	
	private JPanel creerPanInformations() {
		
		return new JPanel();
	}
	
	private JPanel creerOngletCategorie()
	{
		
		int nbLigneCheckBox = (categories.size() - categories.size()%3)/3 +1;
		JPanel panCategorie = new JPanel(new GridLayout( nbLigneCheckBox,3)); //TODO faire calcul pour un bon grid layout
		for (JCheckBox jCheckBox : categories) {
			panCategorie.add(jCheckBox);
		}
		
		return panCategorie;
	}
	
	private JPanel creerOngletLieu()
	{
		
		JPanel panLieu = new JPanel(new GridLayout(3,0));
		
		panLieu.add(creerPanLatitude());
		panLieu.add(creerPanLongitude());
		panLieu.add(creerPanAdresse());
		
		return panLieu;
	}
	
	private JPanel creerOngletNom()
	{
		
		JPanel panNom = new JPanel(new GridLayout(3,1));
		panNom.add(new JLabel("Saisie du nom :"));
		saisieNom = new JTextField();
		panNom.add(saisieNom);
		JButton valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				System.out.println("recherche avec "+saisieNom.getText());
			} 
		});
		panNom.add(valider);
		
		return panNom;
	}
	
	private JPanel creerPanLatitude()
	{
		
		SpinnerNumberModel modelMinLatitude = new SpinnerNumberModel(46.23, 46.23, 48.183, 0.1); 
		SpinnerNumberModel modelMaxLatitude = new SpinnerNumberModel(48.183, 46.23, 48.183, 0.1);
		
		JPanel panLatitude = new JPanel(new GridLayout(0, 2));
		Border borderLatitude = BorderFactory.createTitledBorder("Latitude");
		panLatitude.setBorder(borderLatitude);
		
		JPanel panLatitudeMin = new JPanel();
		panLatitudeMin.add(new JLabel("Min"));
		JSpinner minLatitude = new JSpinner(modelMinLatitude);
		panLatitudeMin.add(minLatitude);
		
		JPanel panLatitudeMax = new JPanel();
		panLatitudeMax.add(new JLabel("Max"));
		JSpinner maxLatitude = new JSpinner(modelMaxLatitude);
		panLatitudeMax.add(maxLatitude);
		
		panLatitude.add(panLatitudeMin);
		panLatitude.add(panLatitudeMax);
		
		return panLatitude;
	}
	
	private JPanel creerPanLongitude()
	{
		
		SpinnerNumberModel modelMinLongitude = new SpinnerNumberModel(5.25, 5.25, 7.23, 0.1); 
		SpinnerNumberModel modelMaxLongitude = new SpinnerNumberModel(7.23, 5.25, 7.23, 0.1); 
	
		JPanel panLongitude = new JPanel(new GridLayout(0, 2));
		Border borderLongitude = BorderFactory.createTitledBorder("Longitude");
		panLongitude.setBorder(borderLongitude);
		
		JPanel panLongitudeMin = new JPanel();
		panLongitudeMin.add(new JLabel("Min"));
		JSpinner minLongitude = new JSpinner(modelMinLongitude);
		panLongitudeMin.add(minLongitude);
		
		JPanel panLongitudeMax = new JPanel();
		panLongitudeMax.add(new JLabel("Max"));
		JSpinner maxLongitude = new JSpinner(modelMaxLongitude);
		panLongitudeMax.add(maxLongitude);
		
		panLongitude.add(panLongitudeMin);
		panLongitude.add(panLongitudeMax);
		
		return panLongitude;
	}
	
	private JPanel creerPanAdresse() {
		
		JPanel panAdresse = new JPanel(new GridLayout(0, 2));
		Border borderAdresse = BorderFactory.createTitledBorder("Adresse");
		panAdresse.setBorder(borderAdresse);
		
		panAdresse.add(creerPanDepartement());
		panAdresse.add(creerPanCommune());
		
		return panAdresse;
	}
	
	private JPanel creerPanDepartement() {
		
		JPanel panDepartement = new JPanel();
		panDepartement.add(new JLabel("Département"));
		
		// construire le modèle dynamique avec les départements
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("Tout");
		listModel.addElement("90");
		listModel.addElement("70");
		listModel.addElement("39");
		listModel.addElement("25");
	
		JList listDepartements = new JList(listModel);
		listDepartements.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listDepartements.setSelectedIndex(0);
		listDepartements.setVisibleRowCount(1);
	
		JScrollPane listScrollPaneDep = new JScrollPane(listDepartements);
		
		// listener
		
		panDepartement.add(listDepartements);
		
		return panDepartement;
	}
	
	private JPanel creerPanCommune() {
		
		JPanel panCommune = new JPanel();
		panCommune.add(new JLabel("Commune"));
		
		// remplir dynamique
		DefaultListModel listModel2 = new DefaultListModel();
		listModel2.addElement("Tout");
		listModel2.addElement("issou");
		listModel2.addElement("la chancla");
		listModel2.addElement("el bagnalo");
		listModel2.addElement("yankatagi");
		
		JList listCommunes = new JList(listModel2);
		listCommunes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listCommunes.setSelectedIndex(0);
		listCommunes.setVisibleRowCount(1);
		
		JScrollPane listScrollPaneCom = new JScrollPane(listCommunes);
		
		// listener
		
		panCommune.add(listCommunes);
		
		return panCommune;
	}

	public void changeDisplay(boolean visibility) {
		
		setVisible(visibility);
	}
	
	public void addIcon(ImageIcon i, String key, float x, float y){
		
		labMap.addPointInteret(new PointInteretView(
				i,key,
				toMapCoordX(x, imageMap.getIconWidth(), i.getIconWidth()),
				toMapCoordY(y, imageMap.getIconHeight(), i.getIconHeight())
				));
	}
	
	public int toMapCoordX(float x , int width, int iconWidth){
		
		float mapStart = 5.25f;
		float mapEnd = 7.23f;
		float mapWidth = mapEnd - mapStart;

		return (int) (((x-mapStart) / mapWidth ) * (width)) - iconWidth/2  ;

	}
	
	public int toMapCoordY(float y, float height, int iconHeight){
		
		float mapStart = 48.183f;
		float mapEnd = 46.23f;
		float mapHeight = mapEnd - mapStart;
		
		return (int) (((y-mapStart) / mapHeight) * (height)) - iconHeight/2;
	}
	
	public void displayCategorie(String key){
		
		String withoutAccent = Normalizer.normalize(key, Normalizer.Form.NFD);
		withoutAccent = withoutAccent.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		withoutAccent = withoutAccent.trim();
		ImageIcon i = new ImageIcon(System.getProperty("user.dir")+"/images/"+withoutAccent+".png");
		for (PointInteret pt : this.model.getCategorie(key)) {
			addIcon(i,key, pt.getLongitude(), pt.getLatitude());
		}
		labMap.repaint();
		
	}
	
	public void hideCategorie(String key){
		
		labMap.removePointInteretView(key);
		labMap.repaint();
	}
	
	public void displayAllCategorie(){
		
		for (JCheckBox cb : categories) {
			if (! cb.isSelected()) {
				cb.setSelected(true);
				displayCategorie(cb.getText());
			}
		}
	}
	
	public void hideAllCategorie() {
		
		for (JCheckBox cb : categories) {
			if (cb.isSelected()) {
				cb.setSelected(false);
				hideCategorie(cb.getText());
			}
		}
	}
}