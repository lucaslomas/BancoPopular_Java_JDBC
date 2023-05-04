package entities;

import java.util.Date;

public class ContaBancaria {
    private Date dataDeAberturaConta;
    private Double saldoBancario;

    private int agencia;

    private int numeroConta;


    public Date getDataDeAberturaConta() {
        return dataDeAberturaConta;
    }

    public void setDataDeAberturaConta(Date dataDeAberturaConta) {
        this.dataDeAberturaConta = dataDeAberturaConta;
    }

    public Double getSaldoBancario() {
        return saldoBancario;
    }
    public void setSaldoBancario(Double saldoBancario){
        this.saldoBancario = saldoBancario;
    }

    public Double deposito(Double quantia){
        return 0.0;

    }

    public Double transferencia(Double quantia){
        return 0.0;
    }


}
