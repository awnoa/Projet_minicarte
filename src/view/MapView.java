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
	BufferedImage icon ;
	BufferedImage icontest;
	JLabel iconLabel;
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
			icontest = ImageIO.read(new File(System.getProperty("user.dir")+"/images/musee.png"));
			icon = ImageIO.read(new File(System.getProperty("user.dir")+"/images/musee.png"));
			labMap = new MapPanel(imageMap);
			mapPaneltest = new MapPanel(imageMap);
			mapPaneltest.setBackground(Color.red);
			add(mapPaneltest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		JPanel panNote1 = new JPanel();
		panNote1.setLayout(new BorderLayout());

		panNote1.add(labMap);
		
		JTabbedPane panTab = new JTabbedPane();
		
		int nbLigneCheckBox = (categories.size() - categories.size()%3)/3 +1;
		JPanel panNote2 = new JPanel(new GridLayout( nbLigneCheckBox,3));//TODO faire calcul pour un bon grid layout

		for (JCheckBox jCheckBox : categories) {
			panNote2.add(jCheckBox);
		}
		JPanel panDroite = new JPanel(new GridLayout(2, 1));//panel moitié droite
		JPanel panDroiteBas = new JPanel();
		
		panTab.addTab("Catégorie", null, panNote2, "Sélection avec la catégorie");
		
		panTab.addTab("Lieu", null, new JPanel(), "Sélection avec le lieu");
		
		JPanel tmpNom = new JPanel(new GridLayout(3,1));
		tmpNom.add(new JLabel("Saisie du nom :"));
		saisieNom = new JTextField();
		tmpNom.add(saisieNom);
		JButton valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				    System.out.println("recherche avec "+saisieNom.getText());
			} 
		});
		tmpNom.add(valider);
		
		panTab.addTab("Nom", null, tmpNom, "Sélection avec le nom");
		
		panDroite.add(panTab);
		panDroite.add(panDroiteBas);
		
		JPanel panAll = new JPanel();
		panAll.setLayout(new GridLayout(1, 2));
		panAll.add(panNote1);
		panAll.add(panDroite);
		setContentPane(panAll);

		//		for (int i = 0; i < model.getData().size(); i++) {
		//			addIcon(icon,model.getData().get(i).getLongitude(),model.getData().get(i).getLatitude());
		//		}
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
		//System.out.println(y);

		return (int) (((y-mapStart) / mapHeight) * (height)) - iconHeight/2;

	}
	public void displayCategorie(String key){
		//System.out.println(key);
		String withoutAccent = Normalizer.normalize(key, Normalizer.Form.NFD);
		withoutAccent = withoutAccent.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		withoutAccent = withoutAccent.trim();
		ImageIcon i = new ImageIcon(System.getProperty("user.dir")+"/images/"+withoutAccent+".png");
		//System.out.println("j'affiche"+withoutAccent+".png");

		for (PointInteret pt : this.model.getCategorie(key)) {
			addIcon(i,key, pt.getLongitude(), pt.getLatitude());
		}
	}
	public void hideCategorie(String key){
		labMap.removePointInteretView(key);
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