package service;

import model.Vehicule;

public interface IVehicule {
    public Vehicule find(int id) throws Exception;
    public Vehicule find(String mat) throws Exception;
}
