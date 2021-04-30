package service;

import model.Modele;
import model.Vehicule;
import utilitaires.Db;

import java.sql.ResultSet;

public class VehiculeDao implements IVehicule {

    @Override
    public Vehicule find(int id) throws Exception {
        Vehicule v = new Vehicule();
        Db db = Db.getInstance();
        String sql = "SELECT * FROM vehicule WHERE id=?";
        db.myPrepareStatement(sql);
        Object[] tab={id};
        db.addParameters(tab);
        ResultSet res = db.myExecuteQuery();
        if(res.next()){
            IModele m = new ModeleDao();
            v.setId(res.getInt("id"));
            v.setCouleur(res.getString("couleur"));
            v.setMatricule(res.getString("mat"));
            v.setNbCheveaux(res.getInt("nbCheveaux"));
            v.setNumCategorie(res.getString("numCategorie"));
            v.setModele(m.find(res.getInt("idModele")));
        }
        return v;
    }

    @Override
    public Vehicule find(String mat) throws Exception {
        Vehicule v = null;
        String sql = "SELECT * FROM vehicule WHERE mat='"+mat+"'";
        Db db = Db.getInstance();
        db.myPrepareStatement(sql);
        ResultSet res = db.myExecuteQuery();
        if(res.next()){
            v = new Vehicule();
            IModele m = new ModeleDao();
            v.setId(res.getInt("id"));
            v.setCouleur(res.getString("couleur"));
            v.setMatricule(res.getString("mat"));
            v.setNbCheveaux(res.getInt("nbCheveaux"));
            v.setNumCategorie(res.getString("numCategorie"));
            v.setModele(m.find(res.getInt("idModele")));
        }
        return v;
    }

}
