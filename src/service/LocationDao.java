package service;

import model.Location;
import utilitaires.Db;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LocationDao implements ILocation {
    @Override
    public boolean add(Location l) throws Exception {
        boolean ok = false;
        Db db = Db.getInstance();
        String sql = "INSERT INTO location VALUES(null,?,?,?,?,?)";
        db.myPrepareStatement(sql);
        Object[] tab = {l.getDate(), l.getMontant(), l.getCommentaire(), l.getVehicule().getId(), l.getLocataire().getId()};
        db.addParameters(tab);
        if(db.myExecuteUpdate() > 0){
            ok = true;
        }
        return ok;
    }

    @Override
    public List<Location> findAll() throws Exception {
        List<Location> list = new ArrayList<>();
        Db db = Db.getInstance();
        String sql = "SELECT * FROM location";
        db.myPrepareStatement(sql);
        ResultSet res = db.myExecuteQuery();
        while (res.next()){
            Location loc = new Location();
            loc.setId(res.getInt("id"));
            loc.setDate(LocalDate.parse(res.getString("date")));
            loc.setMontant(Float.parseFloat(res.getString("montant")));
            loc.setCommentaire(res.getString("commentaire"));
            IVehicule v = new VehiculeDao();
            loc.setVehicule(v.find(res.getInt("idVehicule")));
            ILocataire l = new LocataireDao();
            loc.setLocataire(l.find(res.getInt("idLocataire")));
            list.add(loc);
        }
        return list;
    }
}
