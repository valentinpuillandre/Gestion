package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import controleur.PPE_Valentin;
import controleur.User;
import modele.Modele;

public class VueConnexion extends JFrame implements ActionListener, KeyListener{
	
	private JPanel panelConnexion = new JPanel();
	private JTextField txtEmail = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btConnexion = new JButton("Se connecter");
	public VueConnexion()
	{
		this.setTitle("KANYU");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(200,100,800,335);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.gray);
		this.setResizable(false);
		
		//construction du pannelConnexion
		this.panelConnexion.setLayout(new GridLayout(2,2,3,3));
		this.panelConnexion.setBounds(300,0,500,299);
		this.panelConnexion.setBackground(Color.white);
		
		//this.panelConnexion.setBorder(BorderFactory.createLineBorder(new Color(42,43,40)));
		Font uneFonte = new Font("Lucida",10,30);
		//JLabel lbEmail = new JLabel("\t\t\t Email "); 
		//lbEmail.setFont(uneFonte);
		//this.panelConnexion.add(lbEmail);
		
		//JLabel lbmdp = new JLabel("\t\t\t Mot de passe  ");
		//lbmdp.setFont(uneFonte);
		//this.panelConnexion.add(lbmdp);
		
		this.panelConnexion.add(this.txtEmail);
		//this.panelConnexion.add(new JLabel("Mot de passe :"));
		this.panelConnexion.add(this.txtMdp);
		this.panelConnexion.add(this.btAnnuler);
		
		this.txtMdp.setFont(uneFonte);
		this.txtEmail.setFont(uneFonte);
		this.btConnexion.setForeground(Color.white);
		this.btAnnuler.setForeground(Color.white);
		this.btConnexion.setFont(new Font("Lucida",30,23));
		this.btAnnuler.setFont(new Font("Lucida",30,23));
		this.btAnnuler.setBackground(new Color(84, 140, 168));
		this.btConnexion.setBackground(new Color(84, 140, 168));
		
		
		this.panelConnexion.add(this.btConnexion);
		this.add(this.panelConnexion);
		//placement de l'image
		ImageIcon uneImage = new ImageIcon("src/image/logo.png");
		JLabel logo = new JLabel(uneImage);
		logo.setBounds(0,0,300,300);
		this.add(logo);
		
		this.btAnnuler.addActionListener(this);
		this.btConnexion.addActionListener(this);
		
		//rendre les zones de textes écoutables 
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
	
		
		
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btAnnuler)
		{
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}
		else if(e.getSource() == this.btConnexion)
		{
		
		traitement();
		
	}
}
public void traitement()
{
	String mail = this.txtEmail.getText();
	String mdp = new String (this.txtMdp.getPassword());
	
	 //cryptage de mot de passe
	 mdp = PPE_Valentin.getCrypteMDP(mdp);
	 
	//on vérifie dans la bdd : modele
	User unUser = Modele.selectWhereUser(mail, mdp);
	if(unUser == null)
	{
		JOptionPane.showConfirmDialog(this, "Vérifier vos identifiants");
	}
	else
	{
		JOptionPane.showMessageDialog(this, "\n Bienvenue M/Mme." +unUser.getNom() + " \n\n"
				+ "Vous avez le role : " + unUser.getRole());
		//instancier la vue générale
		PPE_Valentin.instancierVueGenerale(unUser);
		PPE_Valentin.rendreVisibleVueConnexion(false);
		this.txtEmail.setText("");
		this.txtMdp.setText("");
	}
}

// touches de clavier 
@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void keyPressed(KeyEvent e) {
	if(e.getKeyCode() == KeyEvent.VK_ENTER)
	{
		traitement();
	}
	
}
@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
}
