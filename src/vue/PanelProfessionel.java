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

import controleur.Professionel;
import controleur.Tableau;
import modele.Modele;

public class PanelProfessionel extends PanelDeBase implements ActionListener, KeyListener {

	public PanelProfessionel(Color uneCouleur) {
		super(uneCouleur);
		// TODO Auto-generated constructor stub
	}

	private JPanel panelForm = new JPanel(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer"); 
	
	
	private JTextField txtNom = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtCp = new JTextField();
	private JTextField txtVille = new JTextField();
	private JTextField txtMail = new JTextField();
	private JTextField txtMdp = new JTextField();
	private JTextField txtSiret = new JTextField();
	private JTextField txtRole = new JTextField();
	
	
	
	private JTable uneTable ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau ; 
	
	private JPanel panelRechercher = new JPanel(); 
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");
	
	
	
	public PanelProfessionel() {
		super(new Color(13, 28, 74));
		
		//=================================CONSTRUCTION DU PANEL FORM===============================
		this.panelForm.setLayout(new GridLayout(10,1));
		this.panelForm.setBounds(10,20, 270, 390);
		this.panelForm.setBackground(Color.gray);
		
		
		this.panelForm.add(new JLabel("Nom Professionel : "));
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
		
		this.panelForm.add(new JLabel("Siret : "));
		this.panelForm.add(this.txtSiret);
		
		this.panelForm.add(new JLabel("Role : "));
		this.panelForm.add(this.txtRole);
		
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm); 
		
		
		
		//============================CONSTRUCTION DU PANEL DE RECHERCHE============================= 
		this.panelRechercher.setLayout(new GridLayout(1,3));
		this.panelRechercher.setBounds(300, 40, 460, 20);
		this.panelRechercher.setBackground(Color.gray);
		this.panelRechercher.add(new JLabel("Filtrer les professionels : "));
		this.panelRechercher.add(this.txtMot); 
		this.panelRechercher.add(this.btRechercher); 
		this.add(this.panelRechercher); 
		
		//===========================RENDRE LES BOUTONS CLIQUABLES==================================== 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);
		this.txtNom.addKeyListener(this);
		this.txtAdresse.addKeyListener(this);
		this.txtCp.addKeyListener(this);
		this.txtVille.addKeyListener(this);
		this.txtMail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		this.txtSiret.addKeyListener(this);
		this.txtRole.addKeyListener(this);
		
		
		//=======================CONSTRUCTION DE LA TABLE==============================================
		String entetes [] = {"Id","Nom","Adresse","Code postal","Ville","Mail","Mot de passe","Siret","Role"};
		
		Object donnees [][] = this.getDonnees ("") ; //select tous les professionels  
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
							int retour = JOptionPane.showConfirmDialog(null,  "Voulez-vous supprimer le professionel ?", 
									"Suppression Professionel", JOptionPane.YES_NO_OPTION); 
							if(retour ==0) {
								int id = Integer.parseInt(unTableau.getValueAt(numLigne,0).toString()); 
								//on supprime le professionel dans la base 
								Modele.deleteCandidat(id);
								//on le supprime de l'affichage 
								unTableau.supprimerLigne(numLigne);
							}
						}else if (nbClic==1)
						{
							
							txtNom.setText(unTableau.getValueAt(numLigne,1).toString());
							txtAdresse.setText(unTableau.getValueAt(numLigne,2).toString());
							txtCp.setText(unTableau.getValueAt(numLigne,3).toString());
							txtVille.setText(unTableau.getValueAt(numLigne,4).toString());
							txtMail.setText(unTableau.getValueAt(numLigne,5).toString());
							txtMdp.setText(unTableau.getValueAt(numLigne,6).toString());
							txtSiret.setText(unTableau.getValueAt(numLigne,7).toString());
							txtRole.setText(unTableau.getValueAt(numLigne,8).toString());
							
							btEnregistrer.setText("Modifier");
						}
					}

					
				});
				
	}

			public Object [][] getDonnees (String mot)
			{
				ArrayList<Professionel> lesProfessionels = Modele.selectAllProfessionels(mot); 
				Object [][] matrice = new Object [lesProfessionels.size()][9]; 
				int i=0; 
				for (Professionel unProfessionel : lesProfessionels)
				{
					matrice[i][0] = unProfessionel.getId(); 
					matrice[i][1] = unProfessionel.getNom(); 
					matrice[i][2] = unProfessionel.getAdresse(); 
					matrice[i][3] = unProfessionel.getCp(); 
					matrice[i][4] = unProfessionel.getVille();
					matrice[i][5] = unProfessionel.getMail();
					matrice[i][6] = unProfessionel.getMdp();
					matrice[i][7] = unProfessionel.getSiret(); 
					matrice[i][8] = unProfessionel.getRole();
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
				
				this.txtNom.setText("");
				this.txtAdresse.setText("");
				this.txtCp.setText("");
				this.txtVille.setText("");
				this.txtMail.setText("");
				this.txtMdp.setText("");
				this.txtSiret.setText("");
				
				this.btEnregistrer.setText("Enregistrer");
				
				
				this.txtNom.setBackground(Color.white);
				this.txtAdresse.setBackground(Color.white);
				this.txtCp.setBackground(Color.white);
				this.txtVille.setBackground(Color.white);
				this.txtMail.setBackground(Color.white);
				this.txtMdp.setBackground(Color.white);
				this.txtSiret.setBackground(Color.white);
			}
			
			public void traitement (int choix)
			{
				
				String nom = this.txtNom.getText(); 
				String adresse = this.txtAdresse.getText();
				String cp = this.txtCp.getText();
				String ville = this.txtVille.getText();
				String mail = this.txtMail.getText(); 
				String mdp = this.txtMdp.getText();
				String siret = this.txtSiret.getText();
				String role = this.txtRole.getText();
				 
				
				if (nom.equals(""))  {
					this.txtNom.setBackground(Color.red);
				}else {
					this.txtNom.setBackground(Color.white);
				}
				if (mail.equals("")){
					this.txtMail.setBackground(Color.red);
				}else {
					this.txtMail.setBackground(Color.white);
				}
				if (nom.equals("") || mail.equals("") || siret.equals("")){
					JOptionPane.showMessageDialog(this, "Veuillez remplir les champs obligatoires");
					//this.viderChamps();
				}
				else { if (choix == 0) {
								//instancier la classe Candidat 
								Professionel unProfessionel = new Professionel(0, nom, adresse, cp, ville,mail,mdp,siret,role); 
								
								//Insertion dans la BDD 
								Modele.insertProfessionel(unProfessionel);
								
								JOptionPane.showMessageDialog(this, "Insertion reussie dans la base");
								
								//on récupérer le candidat inséré pour son nouvel ID 
								unProfessionel = Modele.selectWhereProfessionel(mail); 
								
								Object ligne[] ={unProfessionel.getId(), 
										unProfessionel.getNom(), unProfessionel.getAdresse(),unProfessionel.getCp(),unProfessionel.getVille(),
										unProfessionel.getMail(),unProfessionel.getMdp(),unProfessionel.getSiret(),unProfessionel.getRole()}; 
								
								this.unTableau.ajouterLigne(ligne);
				}else {
					int numLigne = this.uneTable.getSelectedRow(); 
					int id = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
					Professionel unProfessionel = new Professionel(id,nom, adresse, cp,ville,mail,mdp,siret,role);
					//update dans la base de données
					Modele.updateProfessionel(unProfessionel);
					//update dans le tableau d'affichage 
					Object ligne[] ={unProfessionel.getId(),
							unProfessionel.getNom(), unProfessionel.getAdresse(),unProfessionel.getCp(),unProfessionel.getVille(),
							unProfessionel.getMail(),unProfessionel.getMdp(),unProfessionel.getSiret(),unProfessionel.getRole()}; 
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
