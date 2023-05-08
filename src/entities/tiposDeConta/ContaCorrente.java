package entities.tiposDeConta;

import entities.ContaBancaria;

import java.util.Date;

public class ContaCorrente extends ContaBancaria {
    private Double limiteCredito;
    private TipoDeConta tipoDeConta = TipoDeConta.CC;


    public ContaCorrente(){

    }

    public ContaCorrente(Date aberturaConta, double limiteCredito, float saldoBancario) {
        setDataDeAberturaConta(aberturaConta);
        setSaldoBancario(saldoBancario);
        this.limiteCredito = limiteCredito;
    }

    public TipoDeConta getTipoDeConta() {
        return tipoDeConta;
    }

    public void setTipoDeConta(TipoDeConta tipoDeConta) {
        this.tipoDeConta = tipoDeConta;
    }

    public Double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(Double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    @Override
    public Float deposito(Float quantia) {
        if (quantia > 1000)
            throw new RuntimeException("Error! você não tem permisão para realizar esse deposito");
        else {
            Float novoSaldo = getSaldoBancario() + quantia;
            setSaldoBancario(novoSaldo);
            return getSaldoBancario();
        }
    }
    @Override
    public Float transferencia(Float quantia){
        if(quantia > 1000){
            throw new RuntimeException("Error! você não tem limite de transferencia suficiente para realizar essa ação");
        }
        else {
            Float novoSaldo = getSaldoBancario() - quantia;
            setSaldoBancario(novoSaldo);
            return getSaldoBancario();
        }
    }

}
