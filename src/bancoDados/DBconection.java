package bancoDados;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBconection {
    public static Connection conectionDB = null;


    public static Connection getConectionDB() {
        if (conectionDB == null){
            try{
                Properties properties = loadProperties();
                String url = properties.getProperty("dburl");
                conectionDB = DriverManager.getConnection(url, properties);
            }
            catch (SQLException e){
                throw new DBexception(e.getMessage());
            }
        }
        return conectionDB;
    }

    public static Connection closeConnection(){
        if(conectionDB!=null){
            try {
                conectionDB.close();
            } catch (SQLException e) {
                throw new DBexception(e.getMessage());
            }
        }
        return conectionDB;
    }
    public static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties properties = new Properties();
            properties.load(fs);
            return properties;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new DBexception(e.getMessage());
        }
    }


    public static Statement closeStatement(Statement st){
        if (st != null){
            try{
                st.close();
            } catch (SQLException e) {
                throw new DBexception(e.getMessage());
            }
        }
        return st;
    }

    public static ResultSet closeResultSet(ResultSet rs){
        if (rs != null){
            try{
                rs.close();;
            } catch (SQLException e) {
                throw new DBexception(e.getMessage());
            }
        }
        return rs;
    }
}
