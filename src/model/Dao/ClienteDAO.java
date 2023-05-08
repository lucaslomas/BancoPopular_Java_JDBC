package model.Dao;

import entities.Cliente;

import java.util.List;

public interface ClienteDAO {

    void insert (Cliente obj);

    void delete (String cpf_Cnpj);

    void uptade (Cliente obj);

    Cliente findCliente (String cpf_Cnpj);

    List<Cliente> findAll();



}
