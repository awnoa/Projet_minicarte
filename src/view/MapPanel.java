package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.ietf.jgss.Oid;

import model.PointInteret;

public class MapPanel extends JPanel{

	private BufferedImage icon;
	private ImageIcon mapImage;
	private ArrayList<PointInteretView> points ;
	private Rectangle selection;

	public MapPanel(ImageIcon imageMap) {
		points = new ArrayList<>();
		this.mapImage = imageMap;
		selection = new Rectangle(0,0,0,0);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(mapImage.getImage(), 0, 0, null);
		setPreferredSize(getPreferredSize());	

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		for (PointInteretView p : points) {
			g.drawImage(p.getIcon().getImage(), p.getPosX(), p.getPosY(), null);
		}
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.red);
		g2.drawRect((int)selection.getX(), (int)selection.getY(), (int) selection.getWidth(),(int) selection.getHeight());
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
	public void resetPoints(){
		points = new ArrayList<>();
	}

	public ArrayList<PointInteretView> getDisplayedPoints() {
		// TODO Auto-generated method stub
		return points;
	}
	public void displaySelection(Rectangle r){
		selection = r;
	}
}
