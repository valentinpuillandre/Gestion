package controleur;

public class Poste {

	private int idposte, idsecteur;
	private String intituleposte,contratposte;
	public Poste(int idposte, int idsecteur, String intituleposte, String contratposte) {
		super();
		this.idposte = idposte;
		this.idsecteur = idsecteur;
		this.intituleposte = intituleposte;
		this.contratposte = contratposte;
	}
	public int getIdposte() {
		return idposte;
	}
	public void setIdposte(int idposte) {
		this.idposte = idposte;
	}
	public int getIdsecteur() {
		return idsecteur;
	}
	public void setIdsecteur(int idsecteur) {
		this.idsecteur = idsecteur;
	}
	public String getIntituleposte() {
		return intituleposte;
	}
	public void setIntituleposte(String intituleposte) {
		this.intituleposte = intituleposte;
	}
	public String getContratposte() {
		return contratposte;
	}
	public void setContratposte(String contratposte) {
		this.contratposte = contratposte;
	}
	
}
