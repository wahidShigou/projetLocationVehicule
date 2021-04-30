package service;

import model.Location;

import java.util.List;

public interface ILocation {
    public boolean add(Location l) throws Exception;
    public List<Location> findAll() throws Exception;
}
