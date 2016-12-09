package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.PointInteret;

public class MapPanel extends JPanel{

	BufferedImage icon;
	ImageIcon mapImage;
	ArrayList<PointInteretView> points ;

	public MapPanel(ImageIcon imageMap) {
		points = new ArrayList<>();
		this.mapImage = imageMap;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(mapImage.getImage(), 0, 0, null);
		setPreferredSize(getPreferredSize());	
		
		revalidate();
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		for (PointInteretView p : points) {
			g.drawImage(p.getIcon().getImage(), p.getPosX(), p.getPosY(), null);
		}
	}
	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return new Dimension(mapImage.getIconWidth(), mapImage.getIconHeight());
	}

	public void addPointInteret(PointInteretView p){
		this.points.add(p);
	}

	public void removePointInteretView(String key) {
		for (java.util.Iterator<PointInteretView> iterator = points.iterator(); iterator.hasNext(); ) {
			  PointInteretView pt = iterator.next();
			  if(pt.getCategorie().equals(key)){
			    iterator.remove();
			  }
			}

	}

}
