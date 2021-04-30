package service;

import model.Marque;
import utilitaires.Db;

import java.sql.ResultSet;

public class MarqueDao implements IMarque {
    @Override
    public Marque find(int id) throws Exception {
        Marque m = new Marque();
        Db db = Db.getInstance();
        String sql = "SELECT * FROM marque WHERE id="+id;
        db.myPrepareStatement(sql);
        ResultSet res = db.myExecuteQuery();
        if (res.next()){
            m.setId(res.getInt("id"));
            m.setLibelle(res.getString("lib"));
        }
        return m;
    }
}
