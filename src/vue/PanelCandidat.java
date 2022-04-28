package vue;

import java.awt.Color;
import java.awt.Font;
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
import controleur.Departement;
import controleur.Tableau;
import modele.Modele;

public class PanelCandidat extends PanelDeBase implements ActionListener, KeyListener {

	private JPanel panelForm = new JPanel(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer"); 
	private JLabel titre = new JLabel("Gestion des candidatures");
	
	private JTextField txtPrenom = new JTextField();
	private JTextField txtNom = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtCp = new JTextField();
	private JTextField txtVille = new JTextField();
	private JTextField txtMail = new JTextField();
	private JTextField txtMdp = new JTextField();
	private JTextField txtRole = new JTextField();
	private JComboBox<String> txtNumd = new JComboBox<String>();
	private JTextField txtCommentaire = new JTextField();
	
	
	private JTable uneTable ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau ; 
	
	private JPanel panelRechercher = new JPanel(); 
	private JPanel panelTitre = new JPanel(); 
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");
	
	
	
	public PanelCandidat() {
		super(new Color(84, 140, 168));
		
		//=================================CONSTRUCTION DU PANEL FORM===============================
		this.panelForm.setLayout(new GridLayout(11,1));
		this.panelForm.setBounds(10,20, 270, 340);
		this.panelForm.setBackground(new Color(84, 140, 168));
		
		this.panelForm.add(new JLabel("Pr�nom Candidat : "));
		this.panelForm.add(this.txtPrenom);
		
		this.panelForm.add(new JLabel("Nom Candidat : "));
		this.panelForm.add(this.txtNom);
		
		this.panelForm.add(new JLabel("Adresse : "));
		this.panelForm.add(this.txtAdresse);
		
		this.panelForm.add(new JLabel("Code postal : "));
		this.panelForm.add(this.txtCp);
	
		this.panelForm.add(new JLabel("Ville : "));
		this.panelForm.add(this.txtVille);
		
		this.panelForm.add(new JLabel("Email : "));
		this.panelForm.add(this.txtMail);
		
		this.panelForm.add(new JLabel("Mot de passe : "));
		this.panelForm.add(this.txtMdp);
		
		this.panelForm.add(new JLabel("Role : "));
		this.panelForm.add(this.txtRole);
		
		this.panelForm.add(new JLabel("Departement : "));
		this.panelForm.add(this.txtNumd);
		
		this.panelForm.add(new JLabel("Description : "));
		this.panelForm.add(this.txtCommentaire);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm); 
		
		for (Departement unDepartement : Modele.selectAllDepartements(""))
		{
			this.txtNumd.addItem(unDepartement.getNumdepartement()+"-"+unDepartement.getNomdepartement());
		}
		
	
		//============================CONSTRUCTION DU PANEL DE RECHERCHE============================= 
		this.panelRechercher.setLayout(new GridLayout(1,3));
		this.panelRechercher.setBounds(300, 40, 460, 20);
		this.panelRechercher.setBackground(new Color(84, 140, 168));
		this.panelRechercher.add(new JLabel("Filtrer les candidats : "));
		this.panelRechercher.add(this.txtMot); 
		this.panelRechercher.add(this.btRechercher); 
		
		this.add(this.panelRechercher); 
		//=============================================================================
		this.panelTitre.setLayout(new GridLayout(1,1));
		
		this.panelTitre.setBounds(400, 10, 150, 30);
		this.panelTitre.setBackground(new Color(84, 140, 168));
		this.panelTitre.setFont(new Font("Lucida",30,18));
		this.panelTitre.setForeground(Color.white);
		this.panelTitre.add(new JLabel("Gestion des candidats"));
		this.add(this.panelTitre); 
		
		//===========================RENDRE LES BOUTONS CLIQUABLES==================================== 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);
		this.txtPrenom.addKeyListener(this);
		this.txtNom.addKeyListener(this);
		this.txtAdresse.addKeyListener(this);
		this.txtCp.addKeyListener(this);
		this.txtVille.addKeyListener(this);
		this.txtMail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		this.txtRole.addKeyListener(this);
		this.txtNumd.addKeyListener(this);
		this.txtCommentaire.addKeyListener(this);
		
		//=======================CONSTRUCTION DE LA TABLE==============================================
		String entetes [] = {"Id","Prenom","Nom","Adresse","Code postal","Ville","Mail","Mot de passe","Role","ID dep","Commentaire"};
		
		Object donnees [][] = this.getDonnees ("") ; //select tous les candidats  
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
							int retour = JOptionPane.showConfirmDialog(null,  "Voulez-vous supprimer le client ?", 
									"Suppression Candidat", JOptionPane.YES_NO_OPTION); 
							if(retour ==0) {
								int id = Integer.parseInt(unTableau.getValueAt(numLigne,0).toString()); 
								//on supprime le candidat dans la base 
								Modele.deleteCandidat(id);
								//on le supprime de l'affichage 
								unTableau.supprimerLigne(numLigne);
							}
						}else if (nbClic==1)
						{
							txtPrenom.setText(unTableau.getValueAt(numLigne,1).toString());
							txtNom.setText(unTableau.getValueAt(numLigne,2).toString());
							txtAdresse.setText(unTableau.getValueAt(numLigne,3).toString());
							txtCp.setText(unTableau.getValueAt(numLigne,4).toString());
							txtVille.setText(unTableau.getValueAt(numLigne,5).toString());
							txtMail.setText(unTableau.getValueAt(numLigne,6).toString());
							txtMdp.setText(unTableau.getValueAt(numLigne,7).toString());
							txtRole.setText(unTableau.getValueAt(numLigne,8).toString());
							
							
							//txtNumd.setText(unTableau.getValueAt(numLigne,9).toString());
							btEnregistrer.setText("Modifier");
						}
					}

					
				});
				
	}

			public Object [][] getDonnees (String mot)
			{
				ArrayList<Candidat> lesCandidats = Modele.selectAllCandidats(mot); 
				Object [][] matrice = new Object [lesCandidats.size()][11]; 
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
					matrice[i][10] = unCandidat.getCommentaire();
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
					//mise � jour de l'affichage 
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
				this.txtCommentaire.setText("");
				
				this.btEnregistrer.setText("Enregistrer");
				
				this.txtPrenom.setBackground(Color.white);
				this.txtNom.setBackground(Color.white);
				this.txtAdresse.setBackground(Color.white);
				this.txtCp.setBackground(Color.white);
				this.txtVille.setBackground(Color.white);
				this.txtMail.setBackground(Color.white);
				this.txtMdp.setBackground(Color.white);
				this.txtCommentaire.setBackground(Color.white);
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
				String commentaire = this.txtCommentaire.getText();
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
								Candidat unCandidat = new Candidat(0,numd, prenom, nom, adresse, cp, ville,mail,mdp,role,commentaire); 
								
								//Insertion dans la BDD 
								Modele.insertCandidat(unCandidat);
								
								JOptionPane.showMessageDialog(this, "Insertion réussie dans la base");
								
								//on r�cup�rer le candidat ins�r� pour son nouvel ID 
								unCandidat = Modele.selectWhereCandidat(mail,0); 
								
								Object ligne[] ={unCandidat.getId(), unCandidat.getPrenom(), 
										unCandidat.getNom(), unCandidat.getAdresse(),unCandidat.getCp(),unCandidat.getVille(),
										 unCandidat.getMail(),unCandidat.getMdp(),unCandidat.getRole(),
										 unCandidat.getNumd(), unCandidat.getCommentaire()}; 
								
								this.unTableau.ajouterLigne(ligne);
				}else {
					int numLigne = this.uneTable.getSelectedRow(); 
					int id = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
					Candidat unCandidat = new Candidat(id,numd, prenom,nom, adresse, cp,ville,mail,mdp,role,commentaire);
					//update dans la base de donn�es
					Modele.updateCandidat(unCandidat);
					//update dans le tableau d'affichage 
					Object ligne[] ={unCandidat.getId(), unCandidat.getPrenom(), 
							unCandidat.getNom(), unCandidat.getAdresse(),unCandidat.getCp(),unCandidat.getVille(),
							 unCandidat.getMail(),unCandidat.getMdp(),unCandidat.getRole(),
							 unCandidat.getNumd(),unCandidat.getCommentaire()}; 
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
