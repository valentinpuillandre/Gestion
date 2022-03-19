package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Candidat;
import controleur.Candidature;
import controleur.Secteur;
import controleur.User;
import controleur.Departement;
import controleur.Poste;



import controleur.Tableau;
import modele.Modele;

public class PanelCandidature extends PanelDeBase implements ActionListener, KeyListener {

	public PanelCandidature(Color uneCouleur) {
		super(uneCouleur);
		// TODO Auto-generated constructor stub
	}

	private JPanel panelForm = new JPanel(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer"); 
	
	

	private JComboBox<String> txtIdsecteur = new JComboBox<String>();
	private JComboBox<String> txtId = new JComboBox<String>();
	private JComboBox<String> txtNumdepartement = new JComboBox<String>();
	private JComboBox<String> txtIdposte = new JComboBox<String>();
	
	
	private JTable uneTable ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau ; 
	
	private JPanel panelRechercher = new JPanel(); 
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");
	
	
	
	public PanelCandidature() {
		super(new Color(84, 140, 168));
		
		//=================================CONSTRUCTION DU PANEL FORM===============================
		this.panelForm.setLayout(new GridLayout(10,1));
		this.panelForm.setBounds(10,20, 270, 340);
		this.panelForm.setBackground(Color.gray);
		
		this.panelForm.add(new JLabel("Secteur : "));
		this.panelForm.add(this.txtIdsecteur);
		
		this.panelForm.add(new JLabel("Nom : "));
		this.panelForm.add(this.txtId);
		
		this.panelForm.add(new JLabel("Departement : "));
		this.panelForm.add(this.txtNumdepartement);
		
		this.panelForm.add(new JLabel("Poste : "));
		this.panelForm.add(this.txtIdposte);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm); 
		
		for (Secteur unSecteur : Modele.selectAllSecteurs(""))
		{
			this.txtIdsecteur.addItem(unSecteur.getIdsecteur()+"-"+unSecteur.getNoms());
		}
		for (Candidat unCandidat : Modele.selectAllCandidats(""))
		{
			this.txtId.addItem(unCandidat.getId()+"-"+unCandidat.getNom());
		}
		for (Departement unDepartement : Modele.selectAllDepartements(""))
		{
			this.txtNumdepartement.addItem(unDepartement.getNumdepartement()+"-"+unDepartement.getNomdepartement());
		}
		for (Poste unPoste : Modele.selectAllPostes(""))
		{
			this.txtIdposte.addItem(unPoste.getIdposte()+"-"+unPoste.getIntituleposte());
		}
		
		
		//============================CONSTRUCTION DU PANEL DE RECHERCHE============================= 
		this.panelRechercher.setLayout(new GridLayout(1,3));
		this.panelRechercher.setBounds(300, 40, 460, 20);
		this.panelRechercher.setBackground(Color.gray);
		this.panelRechercher.add(new JLabel("Filtrer les candidats : "));
		this.panelRechercher.add(this.txtMot); 
		this.panelRechercher.add(this.btRechercher); 
		this.add(this.panelRechercher); 
		
		//===========================RENDRE LES BOUTONS CLIQUABLES==================================== 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);
		this.txtIdsecteur.addKeyListener(this);
		this.txtId.addKeyListener(this);
		this.txtNumdepartement.addKeyListener(this);
		this.txtIdposte.addKeyListener(this);
		
		
		//=======================CONSTRUCTION DE LA TABLE==============================================
		String entetes [] = {"Id Candidature","Secteur","Nom","Departement","Poste"};
		
		Object donnees [][] = this.getDonnees ("") ; //select toutes les candidatures  
		this.unTableau = new Tableau (entetes, donnees); 
		
		this.uneTable = new JTable(unTableau); 
		this.uneScroll = new JScrollPane(this.uneTable); 
		this.uneScroll.setBounds(290, 80, 500, 280);
		this.add(this.uneScroll);
		
		//gestion de la suppression / modification 
				this.uneTable.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {	
					}
					
					@Override
					public void mousePressed(MouseEvent e) {				
					}
					
					@Override
					public void mouseExited(MouseEvent e) {				
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {				
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {	
						int nbClic = e.getClickCount(); 
						int numLigne = uneTable.getSelectedRow(); 
						if( nbClic == 2 )
						{
							int retour = JOptionPane.showConfirmDialog(null,  "Voulez-vous supprimer la candidature ?", 
									"Suppression Candidature", JOptionPane.YES_NO_OPTION); 
							if(retour ==0) {
								int id = Integer.parseInt(unTableau.getValueAt(numLigne,0).toString()); 
								//on supprime la candidature dans la base
								
								Modele.deleteCandidature(id);
								
								//on le supprime de l'affichage 
								unTableau.supprimerLigne(numLigne);
							}
						}else if (nbClic==1)
						{
							
							btEnregistrer.setText("Modifier");
						}
					}

					
				});
				
	}
	// =========================================BLOCAGE ICI ===================================
// =========================================BLOCAGE ICI ===================================
			public Object [][] getDonnees (String mot)
			{
				ArrayList<Candidature> lesCandidatures = Modele.selectAllCandidatures(mot); 
				Object [][] matrice = new Object [lesCandidats.size()][10]; 
				int i=0; 
				for (Candidat unCandidat : lesCandidats)
				{
					matrice[i][0] = unCandidat.getId(); 
					matrice[i][1] = unCandidat.getPrenom(); 
					matrice[i][2] = unCandidat.getNom(); 
					matrice[i][3] = unCandidat.getAdresse(); 
					matrice[i][4] = unCandidat.getCp(); 
					matrice[i][5] = unCandidat.getVille();
					matrice[i][6] = unCandidat.getMail();
					matrice[i][7] = unCandidat.getMdp();
					matrice[i][8] = unCandidat.getRole();
					matrice[i][9] = unCandidat.getNumd();
					i++; 
				}
				return matrice ; 
	}
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == this.btAnnuler)
				{
					this.viderChamps (); 
				}
				else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer"))
				{
					this.traitement (0); 
				}
				else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier"))
				{
					this.traitement (1); 
				}
				else if (e.getSource() == this.btRechercher)
				{
					String mot = this.txtMot.getText(); 
					//mise à  jour de l'affichage 
					this.unTableau.setDonnees (this.getDonnees(mot)); 
				}
			}
			public void viderChamps()
			{
				this.txtPrenom.setText("");
				this.txtNom.setText("");
				this.txtAdresse.setText("");
				this.txtCp.setText("");
				this.txtVille.setText("");
				this.txtMail.setText("");
				this.txtMdp.setText("");
				
				this.btEnregistrer.setText("Enregistrer");
				
				this.txtPrenom.setBackground(Color.white);
				this.txtNom.setBackground(Color.white);
				this.txtAdresse.setBackground(Color.white);
				this.txtCp.setBackground(Color.white);
				this.txtVille.setBackground(Color.white);
				this.txtMail.setBackground(Color.white);
				this.txtMdp.setBackground(Color.white);
			}
			
			public void traitement (int choix)
			{
				String prenom = this.txtPrenom.getText();
				String nom = this.txtNom.getText(); 
				String adresse = this.txtAdresse.getText();
				String cp = this.txtCp.getText();
				String ville = this.txtVille.getText();
				String mail = this.txtMail.getText(); 
				String mdp = this.txtMdp.getText();
				String role = this.txtRole.getText();
				String chaine = this.txtNumd.getSelectedItem().toString(); 
				String tab [] = chaine.split("-");
				int numd = Integer.parseInt(tab[0]);  
				
				if (nom.equals(""))  {
					this.txtNom.setBackground(Color.red);
				}else {
					this.txtNom.setBackground(Color.white);
				}
				if (prenom.equals("")) {
					this.txtPrenom.setBackground(Color.red);
				}else {
					this.txtPrenom.setBackground(Color.white);
				}
				if (mail.equals("")){
					this.txtMail.setBackground(Color.red);
				}else {
					this.txtMail.setBackground(Color.white);
				}
				if (nom.equals("") || prenom.equals("") || mail.equals("")){
					JOptionPane.showMessageDialog(this, "Veuillez remplir les champs obligatoires");
					//this.viderChamps();
				}
				else { if (choix == 0) {
								//instancier la classe Candidat 
								Candidat unCandidat = new Candidat(0,numd, prenom, nom, adresse, cp, ville,mail,mdp,role); 
								
								//Insertion dans la BDD 
								Modele.insertCandidat(unCandidat);
								
								JOptionPane.showMessageDialog(this, "Insertion rÃ©ussie dans la base");
								
								//on récupérer le candidat inséré pour son nouvel ID 
								unCandidat = Modele.selectWhereCandidat(mail); 
								
								Object ligne[] ={unCandidat.getId(), unCandidat.getPrenom(), 
										unCandidat.getNom(), unCandidat.getAdresse(),unCandidat.getCp(),unCandidat.getVille(),
										 unCandidat.getMail(),unCandidat.getMdp(),unCandidat.getRole(),
										 unCandidat.getNumd()}; 
								
								this.unTableau.ajouterLigne(ligne);
				}else {
					int numLigne = this.uneTable.getSelectedRow(); 
					int id = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
					Candidat unCandidat = new Candidat(id,numd, prenom,nom, adresse, cp,ville,mail,mdp,role);
					//update dans la base de données
					Modele.updateCandidat(unCandidat);
					//update dans le tableau d'affichage 
					Object ligne[] ={unCandidat.getId(), unCandidat.getPrenom(), 
							unCandidat.getNom(), unCandidat.getAdresse(),unCandidat.getCp(),unCandidat.getVille(),
							 unCandidat.getMail(),unCandidat.getMdp(),unCandidat.getRole(),
							 unCandidat.getNumd()}; 
					this.unTableau.modifierLigne (numLigne, ligne); 
				}
				
				//vider les champs 
				this.viderChamps();
				}
	
			}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}

