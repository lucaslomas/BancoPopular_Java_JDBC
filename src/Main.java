import entities.Cliente;
import entities.CpfCnpjValidator;
import entities.TipoDePessoa;
import entities.tiposDeConta.ContaCorrente;
import entities.tiposDeConta.ContaEmpresarial;
import model.Dao.DbconnectionJDBC;
import model.Dao.impl.ClienteDaoJDBC;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws ParseException {
        ClienteDaoJDBC clienteDaoJDBC = DbconnectionJDBC.connectionJDBC();
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("************->MENU<-************");
        while (true){
            System.out.println("\nBem vindo ao Sistema de gestão de Clientes do Banco Popular Brasileiro: ");
            System.out.println("Inserir cliente -> [1]" +
                    "\nDeletar cliente -> [2]" +
                    "\nAtualizar cliente -> [3]" +
                    "\nProcurar por um cliente -> [4]" +
                    "\nMostrar todos os clientes-> [5]" +
                    "\nPara sair do Sistema -> [6]");
            int option = sc.nextInt();
            if (option == 1){
                Cliente cliente = new Cliente();
                System.out.println("Entre com os dados do Cliente: ");
                System.out.println("Name: ");
                String clienteName = sc.next();
                System.out.println("Email: ");
                String clienteEmail = sc.next();
                System.out.println("CPF/CNPJ: ");
                String cpf_cnpj = sc.next();
                System.out.println("________________________________________________________");
                System.out.println("Digite os dados da conta: ");
                System.out.println("Digite a data de Abertura (dd/MM/yyyy): ");
                Date dataAbertura = sdf.parse(sc.next());
                System.out.println("Saldo Bancario: ");
                float saldoBancario = sc.nextFloat();
                System.out.println("Qual o limite de credito inicial? ");
                float limiteDeCredito = sc.nextFloat();

                if(CpfCnpjValidator.isCpf(cpf_cnpj)){
                    cliente = new Cliente(clienteName,clienteEmail, TipoDePessoa.FISICA,
                            new ContaCorrente(dataAbertura, limiteDeCredito, saldoBancario));
                    cliente.setCpf_Cnpj(cpf_cnpj);
                }
                if (CpfCnpjValidator.isCnpj(cpf_cnpj)){
                    cliente = new Cliente(clienteName,clienteEmail, TipoDePessoa.JURIDICA,
                            new ContaEmpresarial(dataAbertura, limiteDeCredito, saldoBancario));
                    cliente.setCpf_Cnpj(cpf_cnpj);
                }
                else {
                    throw new RuntimeException("Error!! escolha uma opção de pessoa Física valida");
                }
                clienteDaoJDBC.insert(cliente);
            }

            if (option == 2){
                System.out.println("Deletar cliente, Tipo de cliente ([1] -> Fisica ; [2] -> Juridica");
                int opcao = sc.nextInt();
                String cpf_cnpj;
                if (opcao == 1){
                    System.out.println("Digite o CPF: ");
                    cpf_cnpj = sc.next();
                    clienteDaoJDBC.delete(cpf_cnpj);
                }
                if (opcao == 2){
                    System.out.println("Digite o CNPJ: ");
                    cpf_cnpj = sc.next();
                    clienteDaoJDBC.delete(cpf_cnpj);
                }
            }

            if (option == 3){
                System.out.println("Digite o CPF ou CNPJ do cliente que irá alterar: ");
                String cpf_cnpj = sc.next();
                System.out.println("Entre com os dados de atualização: ");
                System.out.print("Nome: ");
                String name = sc.next();
                System.out.println("Email: ");
                String email = sc.next();
                clienteDaoJDBC.uptade(new Cliente(name, email, cpf_cnpj));

            }

            if (option == 4){
                System.out.println("Digite o CPF OU CNPJ do cliente a ser encontrado: ");
                String cpf_cnpj = sc.next();
                System.out.println(clienteDaoJDBC.findCliente(cpf_cnpj));
            }

            if (option == 5){
                List<Cliente> clientes = clienteDaoJDBC.findAll();
                for (Cliente cliente: clientes) {
                    System.out.println(cliente);
                }
            }

            if (option == 6){
                break;
            }

        }
    }


}