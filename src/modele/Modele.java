package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Candidat;
import controleur.Departement;
import controleur.User;


public class Modele {

	private static Bdd uneBdd = new Bdd("localhost","kanyu1","root","");
	
	
	
	//*************************************GESTION DES USERS *********************************
	public static User selectWhereUser ( String mail, String mdp)
	{
		
		User unUser = null;
		String requete = "select * from user where mail ='" + mail + "' and mdp  = '" +mdp +"' ; ";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete); 
			
			if (unResultat.next())
			{
				unUser = new User(unResultat.getInt("id"),
				unResultat.getString("nom"),unResultat.getString("adresse"),unResultat.getString("cp"),unResultat.getString("ville"),
				unResultat.getString("mail"), unResultat.getString("mdp"), 
			    unResultat.getString("role"));
			}
			unStat.close();
			uneBdd.seDeconnecter();

		}
		catch(SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		return unUser;
	}
	/********************************** GESTION DES CANDIDATS ***********************************/
	public static void insertCandidat(Candidat unCandidat)
	{
		String requete = "insert into candidat values (null, '" 
	+ unCandidat.getPrenom()+ "','" 
	+ unCandidat.getNom()+"','"
	+ unCandidat.getAdresse() + "','"
	+ unCandidat.getCp() + "','"
	+ unCandidat.getVille()+"','"
	+ unCandidat.getMail()+"','"
	+ unCandidat.getMdp()+"','"
	+ unCandidat.getRole()+"','"
	+ unCandidat.getNumd()+"');";
		
	try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeconnecter();
		}
	catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	public static void updateCandidat(Candidat unCandidat)
	{
		String requete = "update candidat set prenom ='" + unCandidat.getPrenom()
		+ "',nom = '"  + unCandidat.getNom()
		+"',adresse ='"+ unCandidat.getAdresse() 
		+ "',cp='"   + unCandidat.getCp()
		+ "',ville = '"  + unCandidat.getVille()
		+ "',mail = '"  + unCandidat.getMail()
		+ "',mdp = '"  + unCandidat.getMdp()
		+ "',role = '"  + unCandidat.getRole()
		+"',numd='"+ unCandidat.getNumd()
		+"' where id = "+unCandidat.getId();
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}

	public static ArrayList<Candidat> selectAllCandidats(int numd) {
		 
		ArrayList<Candidat> lesCandidats = new ArrayList<Candidat>(); 
		String requete ; 
		if (numd == 0) {
			requete = "select * from candidat"; 
		}
		else {
			requete= "select * from candidat where numd = " + numd + " ; "; 
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
			//parcours des résultats pour construire les instances de Techniciens 
			while (desResultats.next()) //tant qu'il y a un résultat suivant 
			{
				//instancier la classe Technicien : créer un objet Technicien
				Candidat unCandidat = new Candidat (
						desResultats.getInt("id"), desResultats.getInt ("numd"),
						desResultats.getString ("prenom"), 
						desResultats.getString ("nom"), desResultats.getString ("adresse"),
						desResultats.getString ("cp"), desResultats.getString ("ville"), desResultats.getString("mail")
						, desResultats.getString ("mdp"), desResultats.getString ("role")
						);
				//On ajoute cet objet à la liste des Techniciens 
				lesCandidats.add(unCandidat);
			}
			unStat.close(); 
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		return lesCandidats; 
	}
	public static ArrayList<Candidat> selectAllCandidats(String mot) {
		 
		ArrayList<Candidat> lesCandidats = new ArrayList<Candidat>(); 
		String requete ; 
		if (mot.equals("") ) {
			requete = "select * from candidat"; 
		}
		else {
			requete= "select * from candidat where matricule like '%"+mot+"' or "
					+ " marque like '%"+mot+"' or energie like '%"+mot+"' ;";  
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
			//parcours des résultats pour construire les instances de Techniciens 
			while (desResultats.next()) //tant qu'il y a un résultat suivant 
			{
				//instancier la classe Technicien : créer un objet Technicien
				Candidat unCandidat = new Candidat (
						desResultats.getInt("id"), desResultats.getInt ("numd"),
						desResultats.getString ("prenom"), 
						desResultats.getString ("nom"), desResultats.getString ("adresse"),
						desResultats.getString ("cp"), desResultats.getString ("ville"), desResultats.getString("mail")
						, desResultats.getString ("mdp"), desResultats.getString ("role")
						);
				//On ajoute cet objet à la liste des Techniciens 
				lesCandidats.add(unCandidat);
			}
			unStat.close(); 
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		return lesCandidats; 
	}
	
	public static Candidat selectWhereCandidat(String mail) {
		 
		Candidat unCandidat =null; 
		
		String requete =""; 
	
			requete= "select * from candidat where mail ='"+mail+"'; "; 
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
			//parcours des résultats pour construire les instances de Techniciens 
			if (desResultats.next()) //tant qu'il y a un résultat suivant 
			{
				//Instancier la classe candidat : cr�er un objet candidat
				unCandidat = new Candidat (
						desResultats.getInt("id"), desResultats.getInt ("numd"),
						desResultats.getString ("prenom"), 
						desResultats.getString ("nom"), desResultats.getString ("adresse"),
						desResultats.getString ("cp"), desResultats.getString ("ville"), desResultats.getString("mail")
						, desResultats.getString ("mdp"), desResultats.getString ("role")
						);
				 
			}
			unStat.close(); 
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		return unCandidat; 
	}
	
	public static void deleteCandidat(int id) {
		String requete = "delete from candidat where id = " + id +";" ;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
	}
	/********************************** GESTION DES DEPARTEMENTS ***********************************/
	public static void insertDepartement(Departement unDepartement)
	{
		String requete = "insert into departement values (null, '" 
	+ unDepartement.getNomdepartement()+"');";
		
	try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeconnecter();
		}
	catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	public static void updateDepartement(Departement unDepartement)
	{
		String requete = "update departement set nomdepartement ='" + unDepartement.getNomdepartement()
		+"' where numdepartement = "+unDepartement.getNumdepartement();
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
//=============================================================================
	public static ArrayList<Departement> selectAllDepartements(int numdepartement) {
		 
		ArrayList<Departement> lesDepartements = new ArrayList<Departement>(); 
		String requete ; 
		if (numdepartement == 0) {
			requete = "select * from departement"; 
		}
		else {
			requete= "select * from departement where numdepartement = " + numdepartement + " ; "; 
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
			//parcours des résultats pour construire les instances de Techniciens 
			while (desResultats.next()) //tant qu'il y a un résultat suivant 
			{
				//instancier la classe Technicien : créer un objet Technicien
				Departement unDepartement = new Departement (
						desResultats.getInt ("numdepartement"),
						desResultats.getString ("nomdepartement")
						);
				//On ajoute cet objet à la liste des Techniciens 
				lesDepartements.add(unDepartement);
			}
			unStat.close(); 
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		return lesDepartements; 
	}
	//=============================================================================
	public static ArrayList<Departement> selectAllDepartements(String mot) {
		 
		ArrayList<Departement> lesDepartements = new ArrayList<Departement>(); 
		String requete ; 
		if (mot.equals("") ) {
			requete = "select * from departement"; 
		}
		else {
			requete= "select * from vehicule where nomdepartement like '%"+mot+"' ;";  
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
			//parcours des résultats pour construire les instances de Techniciens 
			while (desResultats.next()) //tant qu'il y a un résultat suivant 
			{
				//instancier la classe Technicien : créer un objet Technicien
				Departement unDepartement = new Departement (
						desResultats.getInt ("numdepartement"),
						desResultats.getString ("nomdepartement")
						);
				//On ajoute cet objet à la liste des Techniciens 
				lesDepartements.add(unDepartement);
			}
			unStat.close(); 
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		return lesDepartements; 
	}
	//====================================================================================
	
	
	public static void deleteDepartement(int numdepartement) {
		String requete = "delete from departement where numdepartement = " + numdepartement +";" ;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeconnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
	}
	
	}
