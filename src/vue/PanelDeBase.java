package vue;

import java.awt.Color;

import javax.swing.JPanel;

public class PanelDeBase extends JPanel{

	public PanelDeBase(Color uneCouleur)
	{
		this.setBackground(uneCouleur);
		this.setLayout(null);
		this.setBounds(50, 90, 800, 380); //BOUGER LE PANNEAU DE BASE EN FOND DES PAGES
		this.setVisible(false);
	}
}
