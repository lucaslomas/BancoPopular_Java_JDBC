package model.Dao.impl;

import bancoDados.DBconection;
import bancoDados.DBexception;
import entities.Cliente;
import entities.CpfCnpjValidator;
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
                    "(nome, email, cpf_cnpj, tipoDePessoa, tipoDeConta, dataAberturaConta, saldoBancario) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getNome());
            st.setString(2, obj.getEmail());
            st.setLong(3, obj.getCpf_Cnpj());
            st.setString(4, String.valueOf(obj.getTipoDePessoa()));
            st.setString(5, String.valueOf(obj.getContaBancaria().getTipoDeConta()));
            st.setDate(6, null);
            st.setFloat(7,obj.getContaBancaria().getSaldoBancario());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DBexception(e.getMessage());
        }

        finally {
            DBconection.closeStatement(st);
        }
    }

    @Override
    public void delete(String cpf_Cnpj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM cliente " +
                    "WHERE cpf_cnpj = ?");
            if (CpfCnpjValidator.isCpf(String.valueOf(cpf_Cnpj))){
                cpf_Cnpj = cpf_Cnpj.replace(".", "");
                cpf_Cnpj = cpf_Cnpj.replace("-", "");
                st.setLong(1, Long.parseLong(cpf_Cnpj));
            }
            if (CpfCnpjValidator.isCnpj(String.valueOf(cpf_Cnpj))){
               cpf_Cnpj = cpf_Cnpj.replace(".", "");
                cpf_Cnpj = cpf_Cnpj.replace("-", "");
                cpf_Cnpj = cpf_Cnpj.replace("/", "");
                st.setLong(1, Long.parseLong(cpf_Cnpj));
            }
            else {
                throw new RuntimeException("Erro! Digite um CPF ou CNPJ VALIDO");
            }

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
        if ((CpfCnpjValidator.isCnpj(String.valueOf(obj.getCpf_Cnpj()))) || (CpfCnpjValidator.isCpf(String.valueOf(obj.getCpf_Cnpj()))) ){

            try{
                st = conn.prepareStatement("UPDATE cliente " +
                        "SET nome = ?, email = ? " +
                        "WHERE cpf_cnpj = ?");
                st.setString(1,obj.getNome());
                st.setString(2, obj.getEmail());
                st.setLong(3, obj.getCpf_Cnpj());

                st.executeUpdate();

            } catch (SQLException e) {
                throw new DBexception(e.getMessage());
            }
            finally {
                DBconection.closeStatement(st);
            }
        }

    }

    @Override
    public Cliente findCliente(String cpf_Cnpj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM bancopopular.cliente WHERE cpf_cnpj = ?");

            if (CpfCnpjValidator.isCpf(cpf_Cnpj)){
                cpf_Cnpj = cpf_Cnpj.replace(".", "");
                cpf_Cnpj = cpf_Cnpj.replace("-", "");
                st.setLong(1, Long.parseLong(cpf_Cnpj));
                rs = st.executeQuery();
                if (rs.next()) {
                    Cliente cliente = instantiateCliente(rs);
                    return cliente;
                }
            }


            if (CpfCnpjValidator.isCnpj(cpf_Cnpj)){
                cpf_Cnpj = cpf_Cnpj.replace(".", "");
                cpf_Cnpj = cpf_Cnpj.replace("-", "");
                cpf_Cnpj = cpf_Cnpj.replace("/", "");
                st.setLong(1, Long.parseLong(cpf_Cnpj));
                rs = st.executeQuery();
                if (rs.next()){
                    Cliente cliente = instantiateCliente(rs);
                    return cliente;
                }
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
        return null;
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
        PreparedStatement st;
        ResultSet rs;
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
