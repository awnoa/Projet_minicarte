package controler;

import javax.swing.*;
import javax.swing.text.*; 
import javax.swing.event.*;
import java.awt.event.*;
import view.ViewApp;
import model.ModelApp;

public abstract class Control {

    protected ModelApp model;
    protected ViewApp view;

    public Control(ModelApp model, ViewApp view) {
		this.model = model;
		this.view = view;
    }

    protected void setResultats() { }
}
