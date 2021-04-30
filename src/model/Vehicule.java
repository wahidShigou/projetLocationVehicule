package model;

import java.util.ArrayList;
import java.util.List;

public class Vehicule {
    private int id;
    private String matricule;
    private String couleur;
    private String numCategorie;
    private int nbCheveaux;
    private Modele modele;
    private List<Location> locations;

    public Vehicule() {
        locations = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getNumCategorie() {
        return numCategorie;
    }

    public void setNumCategorie(String numCategorie) {
        this.numCategorie = numCategorie;
    }

    public int getNbCheveaux() {
        return nbCheveaux;
    }

    public void setNbCheveaux(int nbCheveaux) {
        this.nbCheveaux = nbCheveaux;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return matricule;
    }
}
