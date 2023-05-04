package model.Dao;

import bancoDados.DBconection;
import model.Dao.impl.ClienteDaoJDBC;

public class DbconnectionJDBC {

    public static ClienteDaoJDBC connectionJDBC(){
        return new ClienteDaoJDBC(DBconection.getConectionDB());
    }
}
