package model.Dao.impl;

import bancoDados.DBconection;
import bancoDados.DBexception;
import com.mysql.cj.util.SaslPrep;
import entities.Cliente;
import entities.TipoDePessoa;
import model.Dao.ClienteDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoJDBC implements ClienteDAO {

    Connection conn;

    public ClienteDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Cliente obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("INSERT INTO cliente " +
                    "(nome, email, cpf_cnpj, tipoDePessoa, tipoDeConta, dataAberturaConta) " +
                    "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getNome());
            st.setString(2, obj.getEmail());
            st.setLong(3, obj.getCpf_Cnpj());
            st.setString(4, String.valueOf(obj.getTipoDePessoa()));
            st.setString(5,null);
            st.setDate(6, null);
            st.executeUpdate();


        } catch (SQLException e) {
            throw new DBexception(e.getMessage());
        }
        finally {
            DBconection.closeStatement(st);
        }
    }

    @Override
    public void delete(long cpf_Cnpj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM cliente " +
                    "WHERE cpf_cnpj = ?");
            st.setLong(1,cpf_Cnpj);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DBexception(e.getMessage());
        }
        finally {
            DBconection.closeStatement(st);
        }
    }

    @Override
    public void uptade(Cliente obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("UPDATE cliente " +
                    "SET nome = ?, email = ?, tipoDePessoa = ?, tipoDeConta = ?, dataAberturaConta = ? " +
                    "WHERE cpf_cnpj = ?");
            st.setString(1,obj.getNome());
            st.setString(2, obj.getEmail());
            st.setString(3, String.valueOf(obj.getTipoDePessoa()));
            st.setString(4, null);
            st.setDate(5, null);
            st.setLong(6, obj.getCpf_Cnpj());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DBexception(e.getMessage());
        }
        finally {
            DBconection.closeStatement(st);
        }

    }

    @Override
    public Cliente findCliente(long cpf_Cnpj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM bancopopular.cliente WHERE cpf_cnpj = ?");
            st.setLong(1,cpf_Cnpj);
            rs = st.executeQuery();
            if (rs.next()){
                Cliente cliente = instantiateCliente(rs);
                return cliente;
            }
            else {
                throw new RuntimeException("Error! não foi possivel achar o cliente.");
            }
        } catch (SQLException e) {
            throw new DBexception(e.getMessage());
        }finally {
            DBconection.closeResultSet(rs);
            DBconection.closeStatement(st);
        }
    }
    public Cliente instantiateCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNome(rs.getString("nome"));
        cliente.setEmail(rs.getString("email"));
        cliente.setTipoDePessoa(TipoDePessoa.valueOf(rs.getString("tipoDepessoa").toUpperCase()));
        cliente.setCpf_Cnpj(String.valueOf(rs.getLong("cpf_cnpj")));
        return cliente;
    }

    @Override
    public List<Cliente> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();
        try{
            st = conn.prepareStatement("SELECT * FROM cliente");
            rs = st.executeQuery();
            while (rs.next()){
                Cliente cliente = instantiateCliente(rs);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }
}
