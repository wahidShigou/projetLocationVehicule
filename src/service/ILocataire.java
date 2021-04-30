package service;

import model.Locataire;

public interface ILocataire {
    public Locataire find(int id) throws Exception;
    public Locataire find(String num) throws Exception;
    public int add(Locataire l) throws Exception;
    public int last() throws Exception;
}
