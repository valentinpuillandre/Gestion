package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controleur.PPE_Valentin;

import controleur.User;

public class VueGenerale extends JFrame implements ActionListener {
	
	//=============INITIALISATION DES BOUTTONS==================
	private JButton btProfessionel = new JButton("Professionel");
	private JButton btCandidat = new JButton("Candidat");
	private JButton btCandidature = new JButton("Candidatures");
	private JButton btArchive = new JButton("Archives");
	private JButton btBord = new JButton("T-Bord");
	private JButton btQuitter = new JButton("Quitter");
	ImageIcon uneImage = new ImageIcon("src/image/logo.png");
	JLabel logo = new JLabel(uneImage);
	//instanciation des Panels 
	
		private PanelCandidat unPanelCandidats  = new PanelCandidat();
		private PanelProfessionel unPanelProfessionels  = new PanelProfessionel();
		private PanelCandidature unPanelCandidatures  = new PanelCandidature();
		private PanelStats unPanelStats  = new PanelStats();
		private PanelArchive unPanelArchives  = new PanelArchive();
	
	private JPanel panelMenu = new JPanel(); 
	private JPanel panelProfil = new JPanel();
	public VueGenerale(User unUser)
	{
	
		//====================================Page principal logiciel=========================
		this.setTitle("Administration Kanyu"); //titre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//fermeture
		this.setBounds(200,100,900,550); //position et hauteur/largeur
		this.setLayout(null);
		logo.setBounds(0,80,900,400);
		this.add(logo);
		this.getContentPane().setBackground(Color.BLACK);//couleur de fond de la page
		
		
		this.setResizable(false);//impossible à agrandir ou rétrecir 
		
		//=======================================COULEUR DU TEXTE===============================
		this.btCandidature.setForeground(Color.white);
		this.btArchive.setForeground(Color.white);
		this.btCandidat.setForeground(Color.white);
		this.btProfessionel.setForeground(Color.white);
		this.btQuitter.setForeground(Color.white);
		this.btBord.setForeground(Color.white);
		
		//======================================POLICE DE CARACTERE==============================
		this.btCandidature.setFont(new Font("Lucida",15,15));
		this.btArchive.setFont(new Font("Lucida",15,15));
		this.btCandidat.setFont(new Font("Lucida",15,15));
		this.btProfessionel.setFont(new Font("Lucida",15,15));
		this.btQuitter.setFont(new Font("Lucida",15,15));
		this.btBord.setFont(new Font("Lucida",15,15));
		
		//======================================COULEUR DE FOND DES BOUTTONS==============================
		this.btCandidat.setBackground(new Color(84, 140, 168));
		this.btArchive.setBackground(new Color(84, 140, 168));
		this.btCandidature.setBackground(new Color(84, 140, 168));
		this.btProfessionel.setBackground(new Color(84, 140, 168));
		this.btQuitter.setBackground(new Color(84, 140, 168));
		this.btBord.setBackground(new Color(84, 140, 168));
		
		//======================================BORDURE DES BOUTTONS==============================
	    //this.btBord.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
		
		
		//===========================CONSTRUCTION DU PANEL MENU===================================
		this.panelMenu.setLayout(new GridLayout(1,7,5,5));
		this.panelMenu.setBounds(50, 20, 800, 30);
		this.panelMenu.setBackground(new Color(0, 0, 0));
		this.panelMenu.add(this.btCandidature);
		this.panelMenu.add(this.btArchive);
		this.panelMenu.add(this.btCandidat);
		this.panelMenu.add(this.btProfessionel);
		this.panelMenu.add(this.btBord);
		this.panelMenu.add(this.btQuitter);
		this.add(this.panelMenu); //ajouter le panelmenu à la page
		
		 //========================CONSTRUCTION DU PANEL PROFIL====================================
		//this.panelProfil.setLayout(new GridLayout(5, 1));
		//this.panelProfil.setBounds(17, 120, 850, 330);
		//this.panelProfil.setBackground(new Color(84, 140, 168));
		//this.panelProfil.add(new JLabel ("Nom user : "+ unUser.getNom()));
		//this.panelProfil.add(new JLabel ("Email user : "+ unUser.getMail()));
		//this.panelProfil.add(new JLabel ("Role user : "+ unUser.getRole()));
		//this.add(this.panelProfil);
		//this.panelProfil.setVisible(false);
			
			//rendre les boutons écoutables
		 this.btCandidat.addActionListener(this);
		 this.btArchive.addActionListener(this);
		 this.btCandidature.addActionListener(this);
		 this.btProfessionel.addActionListener(this);
		 this.btQuitter.addActionListener(this);
		 this.btBord.addActionListener(this);
		
		//ajout des panels dans la fenetre 
		  
		 this.add(this.unPanelCandidats); 
		 this.add(this.unPanelProfessionels);
		 this.add(this.unPanelCandidatures);
		 this.add(this.unPanelStats);
		 this.add(this.unPanelArchives);
		 
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == this.btQuitter)
		{
			int retour = JOptionPane.showConfirmDialog(this, "Quitter Application","Vous-vous quitter l'application ?",
					JOptionPane.YES_NO_OPTION);
			if(retour == 0)
			{
				this.dispose();
				PPE_Valentin.rendreVisibleVueConnexion(true);
			}
			}
		else if (e.getSource() == this.btCandidat)
		{
			this.unPanelProfessionels.setVisible(false);
			this.unPanelCandidatures.setVisible(false);
			this.unPanelCandidats.setVisible(true);
			this.unPanelStats.setVisible(false);
			this.logo.setVisible(false);
			this.unPanelArchives.setVisible(false);
			
			
		}
		else if(e.getSource() ==this.btProfessionel)
		{
			this.unPanelCandidats.setVisible(false);
			this.unPanelCandidatures.setVisible(false);
			this.unPanelProfessionels.setVisible(true);
			this.unPanelStats.setVisible(false);
			this.logo.setVisible(false);
			this.unPanelArchives.setVisible(false);
		}
		else if(e.getSource() ==this.btCandidature)
		{
			this.unPanelCandidats.setVisible(false);
			this.unPanelProfessionels.setVisible(false);
			this.unPanelCandidatures.setVisible(true);
			this.unPanelStats.setVisible(false);
			this.logo.setVisible(false);
			this.unPanelArchives.setVisible(false);
		}
		else if(e.getSource() ==this.btBord)
		{
			this.unPanelCandidats.setVisible(false);
			this.unPanelProfessionels.setVisible(false);
			this.unPanelCandidatures.setVisible(false);
			this.unPanelStats.setVisible(true);
			this.logo.setVisible(false);
			this.unPanelArchives.setVisible(false);
		}
		else if(e.getSource() ==this.btArchive)
		{
			this.unPanelCandidats.setVisible(false);
			this.unPanelProfessionels.setVisible(false);
			this.unPanelCandidatures.setVisible(false);
			this.unPanelStats.setVisible(false);
			this.logo.setVisible(false);
			this.unPanelArchives.setVisible(true);
		}
			
	}
	}
