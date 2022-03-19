package controleur;

public class Professionel {
	private int id;
	private String nom,adresse,cp,ville,mail,mdp,siret,role;
	public Professionel(int id, String nom, String adresse, String cp, String ville, String mail, String mdp,
			String siret, String role) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.mail = mail;
		this.mdp = mdp;
		this.siret = siret;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getSiret() {
		return siret;
	}
	public void setSiret(String siret) {
		this.siret = siret;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
