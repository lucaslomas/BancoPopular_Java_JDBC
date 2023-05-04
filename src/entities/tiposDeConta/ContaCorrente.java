package entities.tiposDeConta;

import entities.ContaBancaria;

import java.util.Date;

public class ContaCorrente extends ContaBancaria {
    private Double limiteCredito;

    public ContaCorrente(){

    }

    public ContaCorrente(Date aberturaConta, double limiteCredito, double saldoBancario) {
        setDataDeAberturaConta(aberturaConta);
        setSaldoBancario(saldoBancario);
        this.limiteCredito = limiteCredito;
    }


    public Double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(Double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    @Override
    public Double deposito(Double quantia) {
        if (quantia > 1000)
            throw new RuntimeException("Error! você não tem permisão para realizar esse deposito");
        else {
            Double novoSaldo = getSaldoBancario() + quantia;
            setSaldoBancario(novoSaldo);
            return getSaldoBancario();
        }
    }
    @Override
    public Double transferencia(Double quantia){
        if(quantia > 1000){
            throw new RuntimeException("Error! você não tem limite de transferencia suficiente para realizar essa ação");
        }
        else {
            Double novoSaldo = getSaldoBancario() - quantia;
            setSaldoBancario(novoSaldo);
            return getSaldoBancario();
        }
    }

}
