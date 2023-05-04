package model.Dao;

import entities.Cliente;

import java.util.List;

public interface ClienteDAO {

    void insert (Cliente obj);

    void delete (long cpf_Cnpj);

    void uptade (Cliente obj);

    Cliente findCliente (long cpf_Cnpj);

    List<Cliente> findAll();



}
