package controleur;

public class Vlescandidatures {
	int idcandidature ;
	private String prenomcandidat, nomcandidat, villecandidat, 
	mail, secteur, intituleposte, departementrecherche, commentaire;

	public Vlescandidatures(int idcandidature,String prenomcandidat, String nomcandidat, String villecandidat, String mail,
			String secteur, String intituleposte, String departementrecherche, String commentaire) {
		super();
		this.idcandidature = idcandidature;
		this.prenomcandidat = prenomcandidat;
		this.nomcandidat = nomcandidat;
		this.villecandidat = villecandidat;
		this.mail = mail;
		this.secteur = secteur;
		this.intituleposte = intituleposte;
		this.departementrecherche = departementrecherche;
		this.commentaire = commentaire;
	}

	public int getIdcandidature() {
		return idcandidature;
	}

	public void setIdcandidature(int idcandidature) {
		this.idcandidature = idcandidature;
	}

	public String getPrenomcandidat() {
		return prenomcandidat;
	}

	public void setPrenomcandidat(String prenomcandidat) {
		this.prenomcandidat = prenomcandidat;
	}

	public String getNomcandidat() {
		return nomcandidat;
	}

	public void setNomcandidat(String nomcandidat) {
		this.nomcandidat = nomcandidat;
	}

	public String getVillecandidat() {
		return villecandidat;
	}

	public void setVillecandidat(String villecandidat) {
		this.villecandidat = villecandidat;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSecteur() {
		return secteur;
	}

	public void setSecteur(String secteur) {
		this.secteur = secteur;
	}

	public String getIntituleposte() {
		return intituleposte;
	}

	public void setIntituleposte(String intituleposte) {
		this.intituleposte = intituleposte;
	}

	public String getDepartementrecherche() {
		return departementrecherche;
	}

	public void setDepartementrecherche(String departementrecherche) {
		this.departementrecherche = departementrecherche;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

}
