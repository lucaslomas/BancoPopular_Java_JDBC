package entities.tiposDeConta;

import entities.ContaBancaria;

import java.util.Date;

public class ContaEmpresarial extends ContaBancaria {
    private double limiteDeCredito;
    private TipoDeConta tipoDeConta = TipoDeConta.CE;

    public ContaEmpresarial(Date aberturaConta, double limiteCredito, float saldoBancario   ) {
        setDataDeAberturaConta(aberturaConta);
        setSaldoBancario(saldoBancario);
        this.limiteDeCredito = limiteCredito;
    }

    @Override
    public TipoDeConta getTipoDeConta() {
        return tipoDeConta;
    }

    @Override
    public void setTipoDeConta(TipoDeConta tipoDeConta) {
        this.tipoDeConta = tipoDeConta;
    }

    public double getLimiteDeCredito() {
        return limiteDeCredito;
    }

    public void setLimiteDeCredito(double limiteDeCredito) {
        this.limiteDeCredito = limiteDeCredito;
    }
    @Override
    public Float deposito(Float quantia) {
        if (quantia > 1000000)
            throw new RuntimeException("Error! você não tem permisão para realizar esse deposito");
        else {
            Float novoSaldo = getSaldoBancario() + quantia;
            setSaldoBancario(novoSaldo);
            return getSaldoBancario();
        }
    }

    @Override
    public Float transferencia(Float quantia) {
        if(quantia > 1000000){
            throw new RuntimeException("Error! você não tem limite de transferencia suficiente para realizar essa ação");
        }
        else {
            Float novoSaldo = getSaldoBancario() - quantia;
            setSaldoBancario(novoSaldo);
            return getSaldoBancario();
        }
    }
}
