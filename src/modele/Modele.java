package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Archive;
import controleur.Candidat;
import controleur.Candidature;
import controleur.Departement;
import controleur.Poste;
import controleur.Professionel;
import controleur.Secteur;
import controleur.User;


public class Modele {

	private static Bdd uneBdd = new Bdd("localhost","kanyu1","root","");
	
	
	
	//*************************************CONNEXION DES USERS *********************************
	public static User selectWhereUser ( String mail, String mdp)
	{
		//Ici il s'agit de se connnecter à son compte admin.
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
	//================================ INSERTION D'UN CANDIDATS==================================
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
	
	//================================ METTRE A JOUR UN CANDIDAT==================================
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
	//================================ ????? UN CANDIDAT==================================
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
			//parcours des résultats pour construire les instances des candidats 
			while (desResultats.next()) //tant qu'il y a un résultat suivant 
			{
				//instancier la classe candidat : créer un objet candidat
				Candidat unCandidat = new Candidat (
						desResultats.getInt("id"), desResultats.getInt ("numd"),
						desResultats.getString ("prenom"), 
						desResultats.getString ("nom"), desResultats.getString ("adresse"),
						desResultats.getString ("cp"), desResultats.getString ("ville"), desResultats.getString("mail")
						, desResultats.getString ("mdp"), desResultats.getString ("role")
						);
				//On ajoute cet objet à la liste des Candidats 
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
	
	//================================ RECHERCHE D'UN CANDIDAT==================================
	
	public static ArrayList<Candidat> selectAllCandidats(String mot) {
		 
		ArrayList<Candidat> lesCandidats = new ArrayList<Candidat>(); 
		String requete ; 
		if (mot.equals("") ) {
			requete = "select * from candidat"; 
		}
		else {
			requete= 
					"select * from candidat where prenom like '%"+mot+"' or "
			+ " nom like '%"+mot+"' or "
			+ " adresse like '%"+mot+"'  or "
			+ " cp like '%"+mot+"' or "
			+ " ville like '%"+mot+"' or "
			+ " mail like '%"+mot+"'  or "
			+ " role like '%"+mot+"' or numd like '%"+mot+"' ;"; 
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
			//parcours des rÃ©sultats pour construire les instances de Techniciens 
			while (desResultats.next()) //tant qu'il y a un résultat suivant 
			{
				//instancier la classe Candidat : créer un objet candidat
				Candidat unCandidat = new Candidat (
						desResultats.getInt("id"), desResultats.getInt ("numd"),
						desResultats.getString ("prenom"), 
						desResultats.getString ("nom"), desResultats.getString ("adresse"),
						desResultats.getString ("cp"), desResultats.getString ("ville"), desResultats.getString("mail")
						, desResultats.getString ("mdp"), desResultats.getString ("role")
						);
				//On ajoute cet objet à  la liste des Candidats 
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
	//===============================================================================
	public static Candidat selectWhereCandidat(String mail) {
		 
		Candidat unCandidat =null; 
		
		String requete =""; 
	
			requete= "select * from candidat where mail ='"+mail+"'; "; 
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
			//parcours des résultats pour construire les instances de Candidats 
			if (desResultats.next()) //tant qu'il y a un résultat suivant 
			{
				//Instancier la classe candidat : créer un objet candidat
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
	//================================ SUPPRIMER UN CANDIDAT==================================
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
	//=======================================COUNT CANDIDAT=========================================
	
	public static int countCandidats () {
		int nbvols = 0;
		String requete = "SELECT count(*) as nb FROM candidat;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next()) {
				nbvols = unResultat.getInt("nb");
			}
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return nbvols;
	}
	

	
	//==============================================================================================
	/********************************** GESTION DES DEPARTEMENTS ***********************************/
	//==================================INSERTION D'UN DEPARTEMENT AU CAS OU========================
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
	//==================================UPDATE D'UN DEPARTEMENT AU CAS OU========================
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
	//==================================         ========================

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
			//parcours des résultats pour construire les instances dES Départements 
			while (desResultats.next()) //tant qu'il n'y a un résultat suivant 
			{
				//instancier la classe Département : créer un objet Département
				Departement unDepartement = new Departement (
						desResultats.getInt ("numdepartement"),
						desResultats.getString ("nomdepartement")
						);
				//On ajoute cet objet à  la liste des Départements 
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
	//=================================RECHERCHE D'UN DEPARTEMENT============================================
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
			//parcours des résultats pour construire les instances des départements 
			while (desResultats.next()) //tant qu'il y a un résultat suivant 
			{
				//instancier la classe Département : créer un objet Département
				Departement unDepartement = new Departement (
						desResultats.getInt ("numdepartement"),
						desResultats.getString ("nomdepartement")
						);
				//On ajoute cet objet à la liste des Départements 
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
	
	//==============================================================================================
		/********************************** GESTION DES PROFESSIONELS ***********************************/
		//==================================INSERTION D'UN PROFESSIONEL========================
		public static void insertProfessionel(Professionel unProfessionel)
		{
			String requete = "insert into professionels values (null, '" 
					+ unProfessionel.getNom()+"','"
					+ unProfessionel.getAdresse() + "','"
					+ unProfessionel.getCp() + "','"
					+ unProfessionel.getVille()+"','"
					+ unProfessionel.getMail()+"','"
					+ unProfessionel.getMdp()+"','"
					+ unProfessionel.getSiret()+"','"
					+ unProfessionel.getRole()+"');";
			
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
		//==================================UPDATE D'UN PROFESSIONEL========================
		public static void updateProfessionel(Professionel unProfessionel)
		{
			String requete = "update professionels set nom ='" + unProfessionel.getNom()
			+"',adresse ='"+ unProfessionel.getAdresse() 
			+ "',cp='"   + unProfessionel.getCp()
			+ "',ville = '"  + unProfessionel.getVille()
			+ "',mail = '"  + unProfessionel.getMail()
			+ "',mdp = '"  + unProfessionel.getMdp()
			+ "',siret = '"  + unProfessionel.getSiret()
			+ "',role = '"  + unProfessionel.getRole()
			+"' where id = "+unProfessionel.getId();
			
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
		//==================================         ========================

		public static ArrayList<Professionel> selectAllProfessionels(int id) {
			 
			ArrayList<Professionel> lesProfessionels = new ArrayList<Professionel>(); 
			String requete ; 
			if (id == 0) {
				requete = "select * from professionels"; 
			}
			else {
				requete= "select * from professionels where id = " + id + " ; "; 
			}
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
				ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
				//parcours des résultats pour construire les instances dES professionels 
				while (desResultats.next()) //tant qu'il n'y a un résultat suivant 
				{
					Professionel unProfessionel = new Professionel (
							desResultats.getInt("id"),
							desResultats.getString ("nom"), desResultats.getString ("adresse"),
							desResultats.getString ("cp"), desResultats.getString ("ville"), desResultats.getString("mail")
							, desResultats.getString ("mdp"),desResultats.getString ("siret"), desResultats.getString ("role")
							);
					//On ajoute cet objet à la liste des Candidats 
					lesProfessionels.add(unProfessionel);
				}
				unStat.close(); 
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp)
			{
				System.out.println("Erreur execution requete : " + requete);
			}
			return lesProfessionels; 
		}
		//=================================RECHERCHE D'UN DEPARTEMENT============================================
		public static ArrayList<Professionel> selectAllProfessionels(String mot) {
			 
			ArrayList<Professionel> lesProfessionels = new ArrayList<Professionel>(); 
			String requete ; 
			if (mot.equals("") ) {
				requete = "select * from professionels"; 
			}
			else {
				requete= 
						"select * from professionels where nom like '%"+mot+"' or "
								+ " adresse like '%"+mot+"'  or "
								+ " cp like '%"+mot+"' or "
								+ " ville like '%"+mot+"' or "
								+ " mail like '%"+mot+"'  or "
								+ " siret like '%"+mot+"'  or "
								+ " role like '%"+mot+"' ;";  
			}
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
				ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
				//parcours des résultats pour construire les instances des professionels 
				while (desResultats.next()) //tant qu'il y a un résultat suivant 
				{
					Professionel unProfessionel = new Professionel (
							desResultats.getInt("id"),
							desResultats.getString ("nom"), desResultats.getString ("adresse"),
							desResultats.getString ("cp"), desResultats.getString ("ville"), desResultats.getString("mail")
							, desResultats.getString ("mdp"),desResultats.getString ("siret"), desResultats.getString ("role")
							);
					//On ajoute cet objet à la liste des Candidats 
					lesProfessionels.add(unProfessionel);
				}
				unStat.close(); 
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp)
			{
				System.out.println("Erreur execution requete : " + requete);
			}
			return lesProfessionels; 
		}
		//==========================SUPPRIMER UN PROFESSIONEL=====================================
		
		
		public static void deleteProfessionel(int id) {
			String requete = "delete from professionels where id = " + id +";" ;
			
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
		//======================SELECTWHERE PROFESSIONEL============================================
		public static Professionel selectWhereProfessionel(String mail) {
			 
			Professionel unProfessionel =null; 
			
			String  requete= "select * from professionels where mail ='"+mail+"'; "; 
			
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
				ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
				//parcours des résultats pour construire les instances de Candidats 
				if (desResultats.next()) //tant qu'il y a un résultat suivant 
				{
					unProfessionel = new Professionel (
							desResultats.getInt("id"),
							desResultats.getString ("nom"), desResultats.getString ("adresse"),
							desResultats.getString ("cp"), desResultats.getString ("ville"), desResultats.getString("mail")
							, desResultats.getString ("mdp"),desResultats.getString ("siret"), desResultats.getString ("role")
							);
					
					 
				}
				unStat.close(); 
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp)
			{
				System.out.println("Erreur execution requete : " + requete);
			}
			return unProfessionel; 
		}
		
		//=======================================COUNT PROFESSIONNELS=========================================
		
		public static int countProfessionels() {
			int nbvols = 0;
			String requete = "SELECT count(*) as nb FROM professionels;";
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unResultat = unStat.executeQuery(requete);
				if (unResultat.next()) {
					nbvols = unResultat.getInt("nb");
				}
				unStat.close();
				uneBdd.seDeconnecter();
			} catch (SQLException exp) {
				System.out.println("Erreur de requete : " + requete);
			}
			return nbvols;
		}
		//==================================CANDIDATURE=======================================
		/********************************** GESTION DES CANDIDATURES ***********************************/
		public static void insertCandidature(Candidature uneCandidature)
		{
			String requete = "INSERT INTO candidature VALUES (null, '"
					+ uneCandidature.getIdsecteur() + "', '" 
					+ uneCandidature.getId() + "', '"
					+ uneCandidature.getNumdepartement() + "', '"
					+ uneCandidature.getIdposte() + "');";
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
//================================SELECT ALL CANDIDATUREZ ====================================
		public static ArrayList<Candidature> selectAllCandidatures(int id, int choix) {
			 
			ArrayList<Candidature> lesCandidatures = new ArrayList<Candidature>(); 
			String requete ; 
			switch(choix)
			{
			case 1 : requete = "select * from candidature where idsecteur = "+id +";"; break; //secteur
			case 2 : requete = "select * from candidature where id = "+id +";";   break; //candidat 
			case 3 : requete = "select * from candidature where numdepartement = "+id +";";   break; //departement
			case 4 : requete = "select * from candidature where idposte = "+id +";";   break; //poste
			case 5 : requete ="select c.idcandidature, c.idsecteur, c.id, c.numdepartement,c.idposte" + 
					" from candidature c, secteur s, departement d, candidat ca, poste p " + 
					" where c.id = ca.id  " + 
					" and c.idsecteur = s.idsecteur "+ 
					" and c.numdepartement = d.numdepartement " +
                    " and c.idposte = p.idposte " +
                    " and c.id =  "+id + ";" ; break; //client
                    
			default :	requete = "select * from candidature ; "; break;
			}
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
				ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
				//parcours des résultats pour construire les instances de candidatures 
				while (desResultats.next()) //tant qu'il y a un résultat suivant 
				{
					//instancier la classe 	Candidature : créer un objet Candidature
					Candidature uneCandidature = new Candidature (
							desResultats.getInt("idcandidature"), desResultats.getInt ("idsecteur"),
							desResultats.getInt ("id"),
							desResultats.getInt ("numdepartement"), desResultats.getInt ("idposte")
							);
					//On ajoute cet objet à la liste des Candidatures 
					lesCandidatures.add(uneCandidature);
				}
				unStat.close(); 
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp)
			{
				System.out.println("Erreur execution requete : " + requete);
			}
			return lesCandidatures; 
		}
//====================================SUPPRIMER UNE CANDIDATURE======================================
		public static void deleteCandidature(int idcandidature) {
			String requete = "delete from candidature where idcandidature = " + idcandidature +";" ;
			
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
		//======================SELECTWHERE CANDIDATURE========================================
		
		public static Candidature selectWhereCandidature (int idcandidature )
		{
			Candidature uneCandidature = null;  
			String requete = "select * from candidature where idcandidature = "+ idcandidature +";" ; 
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
				ResultSet unResultat = unStat.executeQuery(requete); //fetch de PHP 
				//extraire un résultat et construire une seule instance 
				if (unResultat.next()) //s'il y a un résultat suivant 
				{
					//instancier la classe candidature : créer un objet candidature
					uneCandidature = new Candidature (
							unResultat.getInt("idcandidature"), unResultat.getInt ("idsecteur"),
							unResultat.getInt ("id"),
							unResultat.getInt ("numdepartement"), unResultat.getInt ("idposte")
							);
				}
				unStat.close(); 
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp)
			{
				System.out.println("Erreur execution requete : " + requete);
			}
			return uneCandidature; 
		}
		//=================================METTRE A JOUR=====================================
		public static void updateIntervention (Candidature uneCandidature)
		{
			String requete = "update intervention set idsecteur= '" + uneCandidature.getIdsecteur()
			+ "',id = '" + uneCandidature.getId()+"', numdepartement = '"
			+ uneCandidature.getNumdepartement()+ "', idposte = '"
			+ uneCandidature.getIdposte() +"'  where idcandidature = "+ uneCandidature.getIdcandidature()+";";
			
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
		public static int countCandidatures() {
			int nbvols = 0;
			String requete = "SELECT count(*) as nb FROM candidature;";
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unResultat = unStat.executeQuery(requete);
				if (unResultat.next()) {
					nbvols = unResultat.getInt("nb");
				}
				unStat.close();
				uneBdd.seDeconnecter();
			} catch (SQLException exp) {
				System.out.println("Erreur de requete : " + requete);
			}
			return nbvols;
		}
//=================================================GESTION ARCHIVE=========================================
//===========================================================================================================
//====================================SUPPRIMER UNE ARCHIVE======================================
public static void deleteArchive(int idcandidature) {
String requete = "delete from archivecandidature where idcandidature = " + idcandidature +";" ;
					
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

//====================================SELECT ALL ARCHIVE===============================================
//SELECTION DE TOUS LES CANDIDATURES
		public static ArrayList<Archive> selectAllArchives () {
			ArrayList<Archive> lesArchives = new ArrayList<Archive>();
			String requete = "SELECT * FROM archivecandidature;";
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet desResultats = unStat.executeQuery(requete);
				while (desResultats.next()) {
					Archive uneArchive = new Archive (
							desResultats.getInt("idcandidature"),
							desResultats.getInt("idsecteur"),
							desResultats.getInt("id"),
							desResultats.getInt("numdepartement"),
							desResultats.getInt("idposte")
							);
					lesArchives.add(uneArchive);
				}
				unStat.close();
				uneBdd.seDeconnecter();
			} catch (SQLException exp) {
				System.out.println("Erreur de requête : " + requete);
			}
					return lesArchives;
		}
//================================================================================================
//=================================================================================================
		//=========================================SELECTWHERE POSTE===========================
		public static ArrayList<Poste> selectAllPostes(String mot) {
			 
			ArrayList<Poste> lesPostes = new ArrayList<Poste>(); 
			String requete ; 
			if (mot.equals("") ) {
				requete = "select * from poste "; 
			}
			else {
				requete= 
						"select * from poste where intituleposte like '%"+mot+"' or "
								+ " contratposte like '%"+mot+"'  or "
								+ " idsecteur like '%"+mot+"' ;";  
			}
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
				ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
				//parcours des résultats pour construire les instances des postes 
				while (desResultats.next()) //tant qu'il y a un résultat suivant 
				{
					Poste unPoste = new Poste (
							desResultats.getInt("idposte"),desResultats.getInt ("idsecteur"),
							desResultats.getString ("intituleposte"), desResultats.getString ("contratposte")
							);
					//On ajoute cet objet à la liste des Candidats 
					lesPostes.add(unPoste);
				}
				unStat.close(); 
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp)
			{
				System.out.println("Erreur execution requete : " + requete);
			}
			return lesPostes; 
		}
//=========================================SELECT WHERE SECTEUR=================================
		
		// SELECTION DE TOUS LES SECTEURS
		public static ArrayList<Secteur> selectAllSecteurs () {
			ArrayList<Secteur> lesSecteurs = new ArrayList<Secteur>();
			String requete = "SELECT * FROM secteur;";
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet desResultats = unStat.executeQuery(requete);
				while (desResultats.next()) {
					Secteur unSecteur = new Secteur (
							desResultats.getInt("idsecteur"),
							desResultats.getString("noms")
							);
					lesSecteurs.add(unSecteur);
				}
				unStat.close();
				uneBdd.seDeconnecter();
			} catch (SQLException exp) {
				System.out.println("Erreur de requête : " + requete);
			}
			return lesSecteurs;
		}
		
		//=========================================SELECT WHERE CANDIDAT=================================
		
				// SELECTION DE TOUS LES CANDIDATS
				public static ArrayList<Candidat> selectAllCandidatss () {
					ArrayList<Candidat> lesCandidats = new ArrayList<Candidat>();
					String requete = "SELECT * FROM candidat;";
					try {
						uneBdd.seConnecter();
						Statement unStat = uneBdd.getMaConnexion().createStatement();
						ResultSet desResultats = unStat.executeQuery(requete);
						while (desResultats.next()) {
							Candidat unCandidat = new Candidat (
									desResultats.getInt("id"), desResultats.getInt ("numd"),
									desResultats.getString ("prenom"), 
									desResultats.getString ("nom"), desResultats.getString ("adresse"),
									desResultats.getString ("cp"), desResultats.getString ("ville"), desResultats.getString("mail")
									, desResultats.getString ("mdp"), desResultats.getString ("role")
									);
							lesCandidats.add(unCandidat);
						}
						unStat.close();
						uneBdd.seDeconnecter();
					} catch (SQLException exp) {
						System.out.println("Erreur de requête : " + requete);
					}
					return lesCandidats;
				}

//=========================================SELECT WHERE DEPARTEMENT=================================
				
// SELECTION DE TOUS LES DEPARTEMENTS
		public static ArrayList<Departement> selectAllDepartementss () {
			ArrayList<Departement> lesDepartements = new ArrayList<Departement>();
			String requete = "SELECT * FROM departement;";
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet desResultats = unStat.executeQuery(requete);
				while (desResultats.next()) {
					Departement unDepartement = new Departement (
						desResultats.getInt("numdepartement"), desResultats.getString("nomdepartement")
						);
					lesDepartements.add(unDepartement);
				}
			unStat.close();
			uneBdd.seDeconnecter();
			} catch (SQLException exp) {
				System.out.println("Erreur de requête : " + requete);
			}
			return lesDepartements;
		}
		//=========================================SELECT WHERE POSTE=================================
		
		// SELECTION DE TOUS LES POSTES
				public static ArrayList<Poste> selectAllPostess () {
					ArrayList<Poste> lesPostes = new ArrayList<Poste>();
					String requete = "SELECT * FROM poste;";
					try {
						uneBdd.seConnecter();
						Statement unStat = uneBdd.getMaConnexion().createStatement();
						ResultSet desResultats = unStat.executeQuery(requete);
						while (desResultats.next()) {
							Poste unPoste = new Poste (
								desResultats.getInt("idposte"), desResultats.getInt("idsecteur"),
								desResultats.getString("intituleposte"),
								desResultats.getString("contratposte")
								);
							lesPostes.add(unPoste);
						}
					unStat.close();
					uneBdd.seDeconnecter();
					} catch (SQLException exp) {
						System.out.println("Erreur de requête : " + requete);
					}
					return lesPostes;
			
				}
				
// SELECTION DE TOUS LES CANDIDATURES
		public static ArrayList<Candidature> selectAllCandidaturess () {
			ArrayList<Candidature> lesCandidatures = new ArrayList<Candidature>();
			String requete = "SELECT * FROM candidature;";
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet desResultats = unStat.executeQuery(requete);
				while (desResultats.next()) {
					Candidature unCandidature = new Candidature (
							desResultats.getInt("idcandidature"),
							desResultats.getInt("idsecteur"),
							desResultats.getInt("id"),
							desResultats.getInt("numdepartement"),
							desResultats.getInt("idposte")
							);
					lesCandidatures.add(unCandidature);
				}
				unStat.close();
				uneBdd.seDeconnecter();
			} catch (SQLException exp) {
				System.out.println("Erreur de requête : " + requete);
			}
					return lesCandidatures;
		}
		// EDITION D'UN CANDIDATURE
		public static void updateCandidature (Candidature uneCandidature) {
			String requete = "UPDATE candidature SET idsecteur = '"
					+ uneCandidature.getIdsecteur() + "', id = '" 
					+ uneCandidature.getId() + "', numdepartement = '"
					+ uneCandidature.getNumdepartement() + "', idposte = '"
					+ uneCandidature.getIdposte() + "' WHERE id = "+uneCandidature.getId()+";";
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			} catch (SQLException exp) {
				System.out.println("Erreur de requête : " + requete);
			}
		}
		//=====================================COUNT ARCHIVE==========================================
		public static int countArchives() {
			int nbvols = 0;
			String requete = "SELECT count(*) as nb FROM archivecandidature;";
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unResultat = unStat.executeQuery(requete);
				if (unResultat.next()) {
					nbvols = unResultat.getInt("nb");
				}
				unStat.close();
				uneBdd.seDeconnecter();
			} catch (SQLException exp) {
				System.out.println("Erreur de requete : " + requete);
			}
			return nbvols;
		}
}
	
