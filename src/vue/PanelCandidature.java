package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Poste;
import controleur.Secteur;
import controleur.Tableau;
import controleur.Candidat;
import controleur.Candidature;
import controleur.Departement;

import modele.Modele;

public class PanelCandidature extends PanelDeBase implements ActionListener {

	public PanelCandidature(Color uneCouleur) {
		super(uneCouleur);
		// TODO Auto-generated constructor stub
	}

	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JButton btAnnuler = new JButton("Annuler");
	
	
	
	private JComboBox<String> cbxIdsecteur = new JComboBox<String>();
	private JComboBox<String> cbxId = new JComboBox<String>();
	private JComboBox<String> cbxNumdepartement = new JComboBox<String>();
	private JComboBox<String> cbxIdposte = new JComboBox<String>();

	private JTable uneTable = null;

	private static Tableau unTableau = null;

	public PanelCandidature() {
		super(new Color(84, 140, 168));

		this.panelForm.setLayout(new GridLayout(5, 2));


		this.panelForm.add(new JLabel("Secteur  : "));
		this.panelForm.add(this.cbxIdsecteur);

		this.panelForm.add(new JLabel("Candidat: "));
		this.panelForm.add(this.cbxId);

		this.panelForm.add(new JLabel("Département : "));
		this.panelForm.add(this.cbxNumdepartement);
		
		this.panelForm.add(new JLabel("Poste : "));
		this.panelForm.add(this.cbxIdposte);

		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);

		// this.panelForm.setBackground(Color.gray);

		this.panelForm.setBounds(40, 20, 300, 300);
		this.add(this.panelForm);

		// Construction du panel Table
		this.panelTable.setBounds(345, 20, 400, 300);
		this.panelTable.setBackground(new Color(1, 13, 107));
		this.panelTable.setLayout(null);
		
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
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette candidature ?", "Suppression d'une candidature", JOptionPane.YES_NO_OPTION);
					if (retour == 0) {
						int idcandidature = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						Modele.deleteCandidature(idcandidature);
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
		ArrayList<Candidature> lesCandidatures = Modele.selectAllCandidaturess();
		Object[][] matrice = new Object[lesCandidatures.size()][5];
		int i = 0;
		for (Candidature unCandidature : lesCandidatures) {
			matrice[i][0] = unCandidature.getIdcandidature();
			matrice[i][1] = unCandidature.getIdsecteur();
			matrice[i][2] = unCandidature.getId();
			matrice[i][3] = unCandidature.getNumdepartement();
			matrice[i][4] = unCandidature.getIdposte();
			i++;
		}
		return matrice;
	}
	
	public void viderChamps () {
	
		this.btEnregistrer.setText("Enregistrer");
	}
	
	public Candidature saisirCandidature() {
		Candidature uneCandidature = null;
		
		
		String tab[] = this.cbxIdsecteur.getSelectedItem().toString().split("-");
		int idsecteur = Integer.parseInt(tab[0]);
		
		tab = this.cbxId.getSelectedItem().toString().split("-");
		int id = Integer.parseInt(tab[0]);
		
		tab = this.cbxNumdepartement.getSelectedItem().toString().split("-");
		int numdepartement = Integer.parseInt(tab[0]);
		
		tab = this.cbxIdposte.getSelectedItem().toString().split("-");
		int idposte = Integer.parseInt(tab[0]);
		
		uneCandidature = new Candidature(id, idsecteur, id, numdepartement, idposte);
		
		return uneCandidature;
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		} else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equalsIgnoreCase("Enregistrer")) {
			Candidature uneCandidature = this.saisirCandidature();
			Modele.insertCandidature(uneCandidature);
			
			
	
			// Mettre à jour l'affichage
			Object ligne[] = { uneCandidature.getIdcandidature(), uneCandidature.getIdsecteur(), uneCandidature.getId(), uneCandidature.getNumdepartement(),
				uneCandidature.getIdposte() };
			unTableau.ajouterLigne(ligne);

			JOptionPane.showMessageDialog(this, "Insertion de la candidature réussie !");
			unTableau.fireTableDataChanged();
			this.viderChamps();
		} else if(e.getSource() == this.btEnregistrer && e.getActionCommand().equalsIgnoreCase("Modifier")) {
			Candidature uneCandidature = this.saisirCandidature();
			int numLigne = this.uneTable.getSelectedRow();
			int idcandidature = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			uneCandidature.setIdcandidature(idcandidature);
			Modele.updateCandidature(uneCandidature);
			JOptionPane.showMessageDialog(this, "Modification effectuée !");
			Object ligne[] = { uneCandidature.getIdcandidature(), uneCandidature.getIdsecteur(), uneCandidature.getId(), uneCandidature.getNumdepartement(),
					uneCandidature.getIdposte() };
			unTableau.modifierLigne(numLigne, ligne);
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");
		}
	}

}
