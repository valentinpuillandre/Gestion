package controleur;

import vue.VueConnexion;
import vue.VueGenerale;

public class PPE_Valentin {

	
	
	private static VueConnexion uneVueConnexion ; 
	
	private static VueGenerale uneVueGenerale ; 
	
	public static void rendreVisibleVueConnexion(boolean action)
	{
		uneVueConnexion.setVisible(action);
	}
	public static void instancierVueGenerale (User unUser)
	{
		uneVueGenerale  = new VueGenerale (unUser);
	}
	public static void main(String[] args) 
	{
		uneVueConnexion = new VueConnexion();
	}

}
