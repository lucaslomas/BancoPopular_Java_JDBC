package entities.tiposDeConta;

import entities.ContaBancaria;

import java.util.Date;

public class ContaEmpresarial extends ContaBancaria {
    private double limiteDeCredito;
    private TipoDeConta tipoDeConta = TipoDeConta.CE;

    public ContaEmpresarial(Date aberturaConta, double limiteCredito, double saldoBancario) {
        setDataDeAberturaConta(aberturaConta);
        setSaldoBancario(saldoBancario);
        this.limiteDeCredito = limiteCredito;
    }

    public double getLimiteDeCredito() {
        return limiteDeCredito;
    }

    public void setLimiteDeCredito(double limiteDeCredito) {
        this.limiteDeCredito = limiteDeCredito;
    }
    @Override
    public Double deposito(Double quantia) {
        if (quantia > 1000000)
            throw new RuntimeException("Error! você não tem permisão para realizar esse deposito");
        else {
            Double novoSaldo = getSaldoBancario() + quantia;
            setSaldoBancario(novoSaldo);
            return getSaldoBancario();
        }
    }

    @Override
    public Double transferencia(Double quantia) {
        if(quantia > 1000000){
            throw new RuntimeException("Error! você não tem limite de transferencia suficiente para realizar essa ação");
        }
        else {
            Double novoSaldo = getSaldoBancario() - quantia;
            setSaldoBancario(novoSaldo);
            return getSaldoBancario();
        }
    }
}
