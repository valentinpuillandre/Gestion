package vue;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Tableau;
import modele.Modele;

public class PanelStats extends PanelDeBase {
	
	private JPanel panelStat = new JPanel();
	private JLabel titre = new JLabel("Les candidatures archivées");
	private JPanel panelTitre = new JPanel(); 

	public PanelStats() {
		super(Color.LIGHT_GRAY); 
		
		//=============================================================================
		this.panelTitre.setLayout(new GridLayout(1,1));
		
		this.panelTitre.setBounds(330, 10, 150, 30);
		this.panelTitre.setBackground(new Color(84, 140, 168));
		this.panelTitre.setFont(new Font("Lucida",30,18));
		this.panelTitre.setForeground(Color.white);
		this.panelTitre.add(new JLabel("Statistiques globales"));
		this.add(this.panelTitre); 
		
		
		this.panelStat.setLayout(new GridLayout(4,1));
		this.panelStat.setBounds(35, 50, 730, 200);
		this.setBackground(new Color(84, 140, 168));
		this.setVisible(false);
		
		String entetes[] = { "Nombre de candidatures", "Nombre de candidats", "Nombre de professionnels","Nombre de candidatures archivées"};
		Object matrice [][] = {{Modele.countCandidatures(), Modele.countCandidats(), Modele.countProfessionels(), Modele.countArchives()}};
		Tableau unTableau = new Tableau (entetes, matrice);
		JTable uneTable = new JTable(unTableau);
		
		/* Center les valeurs du tableau */
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i=0; i<uneTable.getColumnCount(); i++) {
			uneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		// uneTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		// uneTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		
		JScrollPane uneScoll = new JScrollPane(uneTable);
		uneScoll.setBounds(60, 40, 250, 1);
		
		this.panelStat.add(uneScoll);
		
		/*
		this.panelStat.add(new JLabel(" Nombre de Pilotes : " + Modele.countPilotes()));
		this.panelStat.add(new JLabel(" Nombre d'Avions : " + Modele.countAvions()));
		this.panelStat.add(new JLabel(" Nombre de Vols : " + Modele.countVols()));
		*/
		this.add(this.panelStat);
	}

}
