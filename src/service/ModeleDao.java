package service;

import model.Modele;
import utilitaires.Db;

import java.sql.ResultSet;

public class ModeleDao implements IModele{
    @Override
    public Modele find(int id) throws Exception {
        Modele m = new Modele();
        Db db = Db.getInstance();
        String sql = "SELECT * FROM modele WHERE id="+id;
        db.myPrepareStatement(sql);
        ResultSet res = db.myExecuteQuery();
        if(res.next()){
            IMarque marque = new MarqueDao();
            m.setId(res.getInt("id"));
            m.setLibelle(res.getString("lib"));
            m.setMarque(marque.find(res.getInt("idMarque")));
        }
        return m;
    }

    @Override
    public Modele find(String lib) throws Exception {
        Modele m = new Modele();
        Db db = Db.getInstance();
        String sql = "SELECT * FROM modele WHERE lib='"+lib+"'";
        db.myPrepareStatement(sql);
        ResultSet res = db.myExecuteQuery();
        if(res.next()){
            IMarque marque = new MarqueDao();
            m.setId(res.getInt("id"));
            m.setLibelle(res.getString("lib"));
            m.setMarque(marque.find(res.getInt("idMarque")));
        }
        return m;
    }
}
