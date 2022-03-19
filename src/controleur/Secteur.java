package controleur;

public class Secteur {
	private int idsecteur;
	private String noms;
	public Secteur(int idsecteur, String noms) {
		super();
		this.idsecteur = idsecteur;
		this.noms = noms;
	}
	public int getIdsecteur() {
		return idsecteur;
	}
	public void setIdsecteur(int idsecteur) {
		this.idsecteur = idsecteur;
	}
	public String getNoms() {
		return noms;
	}
	public void setNoms(String noms) {
		this.noms = noms;
	}
	
}
