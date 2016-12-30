package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;

/*
 * Si on diminue le spinner alors on remove de la liste
 * si on augmente on remove tout de la list et on remplie avec les cb cochés + si les conditions sont vérifiées
 */
import model.ModelApp;
import model.PointInteret;

public class ViewApp extends JFrame {

	private BufferedImage icon ;
	private JLabel iconLabel;
	private MapPanel labMap;
	private ArrayList<JCheckBox> categories;
	private ModelApp model;
	private JTextField saisieNom;
	
	private JSpinner spinMinLatitude;
	private JSpinner spinMaxLatitude;
	private JSpinner spinMinLongitude;
	private JSpinner spinMaxLongitude;
	
	private JList listDepartements;
	private JList listCommunes;
	
	private JButton butValider;
	
	private DefaultListModel listModelCommunes;

	public InfosPointInteretView panInfoPointsInterets;
	private ArrayList<PointInteret> displayedPoints;
	

	public ViewApp(ModelApp model) {
		
		this.model = model;
		initAttribut();
		creerVue();
		setTitle("Carte interactive des musées et monuments historiques de Franche-Comté");
		setLocation(0, 0);
		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initAttribut() {

		try {
			icon = ImageIO.read(new File(System.getProperty("user.dir")+"/images/musee.png"));
			ImageIcon imageMap = new ImageIcon(System.getProperty("user.dir")+"/images/carte.jpg");
			labMap = new MapPanel(imageMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		initCategories();
	}
	
	private void initCategories() {
		
		categories = new ArrayList<>();
		displayedPoints = new ArrayList<>();
		for (Iterator iterator = model.categories.keySet().iterator(); iterator.hasNext();) {
			final String currentString = (String) iterator.next();
			//System.out.println(currentString);
			this.categories.add(new JCheckBox(currentString));
		}
		
		this.categories.add(new JCheckBox("tout (dé)sélectionner"));
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
		creerPanInformations();
		panDroite.add(panInfoPointsInterets);
		
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
	
	private void creerPanInformations() {
		panInfoPointsInterets = new InfosPointInteretView();
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
		Border borderNom = BorderFactory.createTitledBorder("Recherche par nom");
		
		JPanel panNom = new JPanel();
		panNom.setLayout(new BoxLayout(panNom, BoxLayout.Y_AXIS));
		panNom.setBorder(borderNom);
		
		panNom.add(new JLabel("Saisie nom :"));
		
		panNom.add(Box.createVerticalStrut(10));
		
		saisieNom = new JTextField();
		saisieNom.setMaximumSize(new Dimension(360, saisieNom.getPreferredSize().height));
		panNom.add(saisieNom);
		
		panNom.add(Box.createVerticalStrut(10));
		
		butValider = new JButton("Valider");
		panNom.add(butValider);
		
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
		spinMinLatitude = new JSpinner(modelMinLatitude);
		panLatitudeMin.add(spinMinLatitude);
		
		JPanel panLatitudeMax = new JPanel();
		panLatitudeMax.add(new JLabel("Max"));
		spinMaxLatitude = new JSpinner(modelMaxLatitude);
		panLatitudeMax.add(spinMaxLatitude);
		
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
		spinMinLongitude = new JSpinner(modelMinLongitude);
		panLongitudeMin.add(spinMinLongitude);
		
		JPanel panLongitudeMax = new JPanel();
		panLongitudeMax.add(new JLabel("Max"));
		spinMaxLongitude = new JSpinner(modelMaxLongitude);
		panLongitudeMax.add(spinMaxLongitude);
		
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
			
		listDepartements = new JList(model.localisation.keySet().toArray());
		
		listDepartements.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listDepartements.setVisibleRowCount(4);
	
		JScrollPane listScrollPaneDep = new JScrollPane(listDepartements);

		panDepartement.add(listScrollPaneDep);
		
		return panDepartement;
	}
	
	private JPanel creerPanCommune() {
		
		JPanel panCommune = new JPanel();
		panCommune.add(new JLabel("Commune"));
		
		// remplir dynamique
		listModelCommunes = new DefaultListModel();
		listModelCommunes.addElement("Tout");
		for (Entry<String, HashSet<String>> me : model.localisation.entrySet()) {
		      for (String s : me.getValue()) {
		    	  listModelCommunes.addElement(s);
		      }
		}
		
		listCommunes = new JList(listModelCommunes);
		listCommunes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listCommunes.setVisibleRowCount(5);
		
		JScrollPane listScrollPaneCom = new JScrollPane(listCommunes);
		
		panCommune.add(listScrollPaneCom);
		
		return panCommune;
	}

	public void changeDisplay(boolean visibility) {
		
		setVisible(visibility);
	}
	
	public void addIcon(ImageIcon i, PointInteret pt, float x, float y){
		
		labMap.addPointInteret(new PointInteretView(
				i,pt,
				toMapCoordX(x, labMap.getPreferredSize().width, i.getIconWidth()),
				toMapCoordY(y, labMap.getPreferredSize().height, i.getIconHeight())
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
	
	public void setCheckBoxControler(ActionListener listener) {
		for (JCheckBox cb : categories) {
			cb.addActionListener(listener);
		}
	}
	
	public void setSpinnerControler(ChangeListener listener) {
		spinMinLatitude.addChangeListener(listener);
		spinMaxLatitude.addChangeListener(listener);
		spinMinLongitude.addChangeListener(listener);
		spinMaxLongitude.addChangeListener(listener);
	}
	
	public void setListControler(ListSelectionListener listener) {
		listDepartements.addListSelectionListener(listener);
		listCommunes.addListSelectionListener(listener);
	}
	
	public void setButtonControler(ActionListener listener) {
		butValider.addActionListener(listener);
		panInfoPointsInterets.setButtonControler(listener);
	}
	public void setMapControler(MouseListener listener){
		labMap.addMouseListener(listener);
	}
	
	public void displayCategorie(String key){
		
		String withoutAccent = Normalizer.normalize(key, Normalizer.Form.NFD);
		withoutAccent = withoutAccent.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		withoutAccent = withoutAccent.trim();
		ImageIcon i = new ImageIcon(System.getProperty("user.dir")+"/images/"+withoutAccent+".png");
		for (PointInteret pt : this.model.getCategorie(key)) {
			addIcon(i,pt, pt.getLongitude(), pt.getLatitude());
			displayedPoints.add(pt);
		}
		labMap.repaint();
		
	}
	
	public void hideCategorie(String key){
		for (java.util.Iterator<PointInteret> iterator = displayedPoints.iterator(); iterator.hasNext(); ) {
			  PointInteret pt = iterator.next();
			  if(pt.getCategorie().equals(key)){
			    iterator.remove();
			  }
			}
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

	public JSpinner getSpinnerLatitudeMin() {
		return spinMinLatitude;
	}
	
	public JSpinner getSpinnerLatitudeMax() {
		return spinMaxLatitude;
	}
	
	public JSpinner getSpinnerLongitudeMax() {
		return spinMaxLongitude;
	}
	
	public JSpinner getSpinnerLongitudeMin() {
		return spinMinLongitude;
	}
	
	public JList getListDepartements() {
		return listDepartements;
	}
	
	public JList getListCommunes() {
		return listCommunes;
	}
	
	public JButton getButtonValider() {
		return butValider;
	}
	
	public JTextField getSaisieNom() {
		return saisieNom;
	}
	
	public DefaultListModel getListModelCommunes() {
		return listModelCommunes;
	}
	public void refreshMapInformations(){
		panInfoPointsInterets.refresh();
	}
	public ArrayList<PointInteretView> getDisplayedPoints(){
		return labMap.getDisplayedPoints();
	}

	public InfosPointInteretView getPanInformations() {
		// TODO Auto-generated method stub
		return panInfoPointsInterets;
	}

	public void resetPanInformations() {
		panInfoPointsInterets.reset();
	}
}