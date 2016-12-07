package view;

import model.MapModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapView extends JFrame {

	private BufferedImage imageMap;
	ImageIcon icon = new ImageIcon(System.getProperty("user.dir")+"/images/croix.png");
	JLabel iconLabel;
	private JLabel labMap;
    private JCheckBox rbMusee;
    private JCheckBox rbMonument;   
    private MapModel model;
    
    public MapView(MapModel model) {
    	this.model = model;
    	initAttribut();
    	creerVue();
    	
    	setTitle("Carte interactive des musées et monuments historiques de Franche-Comté");
    	setLocation(0, 0);
    	setResizable(false);
    	pack();
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setVisible(true);
    }
    
    public void initAttribut() {
    	
		try {
			imageMap = ImageIO.read(new File(System.getProperty("user.dir")+"/images/carte.jpg"));
	    	labMap = new JLabel(new ImageIcon(imageMap));
	    	add(labMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    	rbMusee = new JCheckBox("Musées");
    	rbMonument = new JCheckBox("Monuments historique");
    	iconLabel = new JLabel(icon);
    }
    
    public void creerVue() {
    	
    	JPanel panNote1 = new JPanel();
    	panNote1.setBackground(Color.red);

    	panNote1.add(labMap);
    	
    	panNote1.add(iconLabel);
    	iconLabel.setLocation(100, 100);
    	


    	JPanel panNote2 = new JPanel(new GridLayout(1, 2));
    	panNote2.add(rbMusee);
    	panNote2.add(rbMonument);
    	
    	JPanel panAll = new JPanel();
    	panAll.setLayout(new BoxLayout(panAll, BoxLayout.X_AXIS));
    	panAll.add(panNote1);
    	panAll.add(panNote2);
    	
    	setContentPane(panAll);
    }
    

    public void changeDisplay(boolean visibility) {
    	setVisible(visibility);
    }
}