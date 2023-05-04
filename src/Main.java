import entities.Cliente;
import model.Dao.DbconnectionJDBC;
import model.Dao.impl.ClienteDaoJDBC;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        ///Cliente cliente = new Cliente("Globo", "Globo@gmail.com",TipoDePessoa.JURIDICA,
                ///new ContaEmpresarial(null, 0, 0));
       ///cliente.setCpf_Cnpj("27865757000102");
        ClienteDaoJDBC clienteDaoJDBC = DbconnectionJDBC.connectionJDBC();
        List<Cliente> clientes = clienteDaoJDBC.findAll();
        for (Cliente entidade: clientes) {
            System.out.println(entidade);

        }
    }


}