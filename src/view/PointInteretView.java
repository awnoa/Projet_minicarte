package view;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import model.PointInteret;

public class PointInteretView {
	
	ImageIcon icon;
	int posX;
	int posY;
	PointInteret modelInformations;
	String categorie;
	
	public PointInteretView(ImageIcon icon, PointInteret modelInformations, int posX, int posY) {
		this.icon = icon;
		this.posX = posX;
		this.posY = posY;
		this.modelInformations = modelInformations;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public String getCategorie() {
		// TODO Auto-generated method stub
		return modelInformations.getCategorie() ;
	}

	public PointInteret getPointInformations() {
		// TODO Auto-generated method stub
		return modelInformations;
	}

}
