package service;

import model.Marque;
import model.Modele;

import java.util.List;

public interface IMarque {
    public Marque find(int id) throws Exception;
}
