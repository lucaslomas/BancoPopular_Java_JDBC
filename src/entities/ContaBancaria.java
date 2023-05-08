package entities;

import entities.tiposDeConta.TipoDeConta;

import java.util.Date;

public class ContaBancaria {
    private Date dataDeAberturaConta;
    private Float saldoBancario;

    private int agencia;

    private int numeroConta;

    private TipoDeConta tipoDeConta;

    public TipoDeConta getTipoDeConta() {
        return tipoDeConta;
    }

    public void setTipoDeConta(TipoDeConta tipoDeConta) {
        this.tipoDeConta = tipoDeConta;
    }

    public Date getDataDeAberturaConta() {
        return dataDeAberturaConta;
    }

    public void setDataDeAberturaConta(Date dataDeAberturaConta) {
        this.dataDeAberturaConta = dataDeAberturaConta;
    }

    public Float getSaldoBancario() {
        return saldoBancario;
    }
    public void setSaldoBancario(Float saldoBancario){
        this.saldoBancario = saldoBancario;
    }

    public Float deposito(Float quantia){
        return Float.valueOf(0);

    }

    public Float transferencia(Float quantia){
        return Float.valueOf(0);
    }


}
