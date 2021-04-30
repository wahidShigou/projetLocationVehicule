package service;

import model.Locataire;
import utilitaires.Db;

import java.sql.ResultSet;
import java.time.LocalDate;

public class LocataireDao implements ILocataire {
    @Override
    public Locataire find(int id) throws Exception {
        Locataire l = null;
        Db db = Db.getInstance();
        String sql = "SELECT * FROM locataire WHERE id=?";
        db.myPrepareStatement(sql);
        Object[] tab={id};
        db.addParameters(tab);
        ResultSet res = db.myExecuteQuery();
        if(res.next()){
            l =  new Locataire();
            l.setId(res.getInt("id"));
            l.setNumPiece(res.getString("numPiece"));
            l.setNom(res.getString("nom"));
            l.setPnom(res.getString("pnom"));
            l.setTel(res.getString("tel"));
            l.setDateNaiss(LocalDate.parse(res.getString("dateNaiss")));
        }
        return l;
    }

    @Override
    public Locataire find(String num) throws Exception {
        Locataire l = null;
        Db db = Db.getInstance();
        String sql = "SELECT * FROM locataire WHERE numPiece=?";
        db.myPrepareStatement(sql);
        Object[] tab={num};
        db.addParameters(tab);
        ResultSet res = db.myExecuteQuery();
        if(res.next()){
            l =  new Locataire();
            l.setId(res.getInt("id"));
            l.setNumPiece(res.getString("numPiece"));
            l.setNom(res.getString("nom"));
            l.setPnom(res.getString("pnom"));
            l.setTel(res.getString("tel"));
            l.setDateNaiss(LocalDate.parse(res.getString("dateNaiss")));
        }
        return l;
    }

    @Override
    public int add(Locataire l) throws Exception {

        Db db = Db.getInstance();
        String sql = "INSERT INTO locataire VALUES(null,?,?,?,?,?)";
        db.myPrepareStatement(sql);
        Object[] tab = {l.getNumPiece(), l.getNom(), l.getPnom(), l.getTel(), l.getDateNaiss()};
        db.addParameters(tab);
        return db.myExecuteUpdate();
    }

    @Override
    public int last() throws Exception {
        Db db = Db.getInstance();
        int ret = 0;
        String sql = "SELECT max(id) as id FROM locataire";
        db.myPrepareStatement(sql);
        ResultSet res = db.myExecuteQuery();
        if(res.next()){
            ret = res.getInt("id");
        }
        return ret;
    }
}
