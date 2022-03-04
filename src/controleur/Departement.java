package controleur;

public class Departement {

	private int numdepartement;
	private String nomdepartement;
	public Departement(int numdepartement, String nomdepartement) {
		
		this.numdepartement = numdepartement;
		this.nomdepartement = nomdepartement;
	}
	public int getNumdepartement() {
		return numdepartement;
	}
	public void setNumdepartement(int numdepartement) {
		this.numdepartement = numdepartement;
	}
	public String getNomdepartement() {
		return nomdepartement;
	}
	public void setNomdepartement(String nomdepartement) {
		this.nomdepartement = nomdepartement;
	}
}
