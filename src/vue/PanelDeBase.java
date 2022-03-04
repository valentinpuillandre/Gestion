package vue;

import java.awt.Color;

import javax.swing.JPanel;

public class PanelDeBase extends JPanel{

	public PanelDeBase(Color uneCouleur)
	{
		this.setBackground(uneCouleur);
		this.setLayout(null);
		this.setBounds(40, 80, 800, 380);
		this.setVisible(false);
	}
}
