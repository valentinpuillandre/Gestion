package controleur;

public class Candidature {
private int idcandidature, idsecteur, id, numdepartement, idposte;
private String etat;

public Candidature(int idcandidature, int idsecteur, int id, int numdepartement, int idposte, String etat) {
	super();
	this.idcandidature = idcandidature;
	this.idsecteur = idsecteur;
	this.id = id;
	this.numdepartement = numdepartement;
	this.idposte = idposte;
	this.etat = etat;
}

public int getIdcandidature() {
	return idcandidature;
}

public void setIdcandidature(int idcandidature) {
	this.idcandidature = idcandidature;
}

public int getIdsecteur() {
	return idsecteur;
}

public void setIdsecteur(int idsecteur) {
	this.idsecteur = idsecteur;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getNumdepartement() {
	return numdepartement;
}

public void setNumdepartement(int numdepartement) {
	this.numdepartement = numdepartement;
}

public int getIdposte() {
	return idposte;
}

public void setIdposte(int idposte) {
	this.idposte = idposte;
}

public String getEtat() {
	return etat;
}

public void setEtat(String etat) {
	this.etat = etat;
}

}