package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Poste;
import controleur.Secteur;
import controleur.Tableau;
import controleur.Archive;
import controleur.Candidat;
import controleur.Departement;

import modele.Modele;

public class PanelArchive extends PanelDeBase implements ActionListener {

	public PanelArchive(Color uneCouleur) {
		super(uneCouleur);
		// TODO Auto-generated constructor stub
	}

	

	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	private JLabel titre = new JLabel("Les candidatures archivées");
	private JPanel panelTitre = new JPanel(); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JButton btAnnuler = new JButton("Annuler");
	
	
	
	private JComboBox<String> cbxIdsecteur = new JComboBox<String>();
	private JComboBox<String> cbxId = new JComboBox<String>();
	private JComboBox<String> cbxNumdepartement = new JComboBox<String>();
	private JComboBox<String> cbxIdposte = new JComboBox<String>();

	private JTable uneTable = null;

	private static Tableau unTableau = null;
	

	public PanelArchive() {
		super(new Color(84, 140, 168));

		

		// Construction du panel Table
		this.panelTable.setBounds(200, 40, 400, 300);
		this.panelTable.setBackground(new Color(84, 140, 168));
		this.panelTable.setLayout(null);
		
		//=============================================================================
				this.panelTitre.setLayout(new GridLayout(1,1));
				
				this.panelTitre.setBounds(330, 10, 150, 30);
				this.panelTitre.setBackground(new Color(84, 140, 168));
				this.panelTitre.setFont(new Font("Lucida",30,18));
				this.panelTitre.setForeground(Color.white);
				this.panelTitre.add(new JLabel("Candidatures archivées"));
				this.add(this.panelTitre); 
		
		String entetes[] = { "Secteur", "Candidat", "Département", "Poste"};
		Object donnees[][] = this.getLesDonnees();
		unTableau = new Tableau(entetes, donnees); // Appel du constructeur Tableau
		this.uneTable = new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(10, 10, 380, 280);
		this.panelTable.add(uneScroll);
		
		this.add(this.panelTable);
		
		this.uneTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int nbclic = e.getClickCount();
				if (nbclic == 2) {
					int numLigne = uneTable.getSelectedRow();
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette archive ?", "Suppression d'une archive", JOptionPane.YES_NO_OPTION);
					if (retour == 0) {
						int idcandidature = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						Modele.deleteArchive(idcandidature);
						unTableau.supprimerLigne(numLigne);
						viderChamps();
					}
				} else if (nbclic == 1) {
					int numLigne = uneTable.getSelectedRow();
					
					
					String idsecteur = unTableau.getValueAt(numLigne, 1).toString();
					cbxIdsecteur.setSelectedItem(idsecteur);
					
					String id = unTableau.getValueAt(numLigne, 2).toString();
					cbxId.setSelectedItem(id);
					
					String numdepartement = unTableau.getValueAt(numLigne, 3).toString();
					cbxNumdepartement.setSelectedItem(numdepartement);
					
					String idposte = unTableau.getValueAt(numLigne, 4).toString();
					cbxIdposte.setSelectedItem(idposte);
					
					btEnregistrer.setText("Modifier");
				}
			}
		});
		
		// Remplir les CBX ID
		this.remplirCBX();

		// Rendre les boutons écoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
	}
	
	public void remplirCBX () {
		ArrayList<Secteur> lesSecteurs = Modele.selectAllSecteurs();
		for (Secteur unSecteur : lesSecteurs) {
			this.cbxIdsecteur.addItem(unSecteur.getIdsecteur()+"-"+unSecteur.getNoms());
		}
		
		ArrayList<Candidat> lesCandidats = Modele.selectAllCandidatss();
		for (Candidat unCandidat : lesCandidats) {
			this.cbxId.addItem(unCandidat.getId()+"-"+unCandidat.getNom());
		}
		
		ArrayList<Departement> lesDepartements = Modele.selectAllDepartementss();
		for (Departement unDepartement : lesDepartements) {
			this.cbxNumdepartement.addItem(unDepartement.getNumdepartement()+"-"+unDepartement.getNomdepartement());
		}
		
		ArrayList<Poste> lesPostes = Modele.selectAllPostess();
		for (Poste unPoste : lesPostes) {
			this.cbxIdposte.addItem(unPoste.getIdposte()+"-"+unPoste.getIntituleposte());
		}
	}

	public Object[][] getLesDonnees() {
		ArrayList<Archive> lesArchives = Modele.selectAllArchives();
		Object[][] matrice = new Object[lesArchives.size()][5];
		int i = 0;
		for (Archive uneArchive : lesArchives) {
			matrice[i][0] = uneArchive.getIdcandidature();
			matrice[i][1] = uneArchive.getIdsecteur();
			matrice[i][2] = uneArchive.getId();
			matrice[i][3] = uneArchive.getNumdepartement();
			matrice[i][4] = uneArchive.getIdposte();
			i++;
		}
		return matrice;
	}
	
	public void viderChamps () {
	
		this.btEnregistrer.setText("Enregistrer");
	}
	
	public Archive saisirArchive() {
		Archive uneArchive = null;
		
		
		String tab[] = this.cbxIdsecteur.getSelectedItem().toString().split("-");
		int idsecteur = Integer.parseInt(tab[0]);
		
		tab = this.cbxId.getSelectedItem().toString().split("-");
		int id = Integer.parseInt(tab[0]);
		
		tab = this.cbxNumdepartement.getSelectedItem().toString().split("-");
		int numdepartement = Integer.parseInt(tab[0]);
		
		tab = this.cbxIdposte.getSelectedItem().toString().split("-");
		int idposte = Integer.parseInt(tab[0]);
		
		
		return uneArchive;
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		} 
			
			
	
	
	}

}
