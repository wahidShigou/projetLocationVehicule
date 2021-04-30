package model;

import java.util.ArrayList;
import java.util.List;

public class Marque {
    private int id;
    private String libelle;
    private List<Modele> modeles;

    public Marque(){
        modeles = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Modele> getModeles() {
        return modeles;
    }

    public void setModeles(List<Modele> modeles) {
        this.modeles = modeles;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
