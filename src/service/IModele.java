package service;

import model.Modele;

import java.util.List;

public interface IModele {
    public Modele find(int id) throws Exception;
    public Modele find(String lib) throws Exception;
}
