package aula.orientacao.enumarator.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Objects;

import Interface.IConta;

public class ContaCorrente implements Serializable, IConta {

    private static final long serialVersionUID = 1L;
    private String numero;
    private float saldo;
    private LocalDateTime dataAbertura;
    private boolean status;

    private ArrayList<Transacao> transacoes = new ArrayList<>();

    public ContaCorrente(String numero) {
        super();
        this.numero = numero;
        this.saldo = 0f;
        this.dataAbertura = LocalDateTime.now();
        this.status = true;
    }

    @Override
    public String toString() {
        return "Conta Corrente[numero=" + numero + ", saldo=" + saldo + ", dataAbertura=" + dataAbertura + ", status="
                + status
                + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContaCorrente other = (ContaCorrente) obj;
        return Objects.equals(numero, other.numero);
    }

    public void depositar(float quantia) {
        if (status && quantia > 0) {
            saldo += quantia;
            transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.CREDITO));
        } else
            System.out.println("Deposito não realizado");
    }

    public void sacar(float quantia) {
        if (status && quantia > 0 && quantia <= saldo) {
            saldo -= quantia;
            transacoes.add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.DEBITO));
        } else
            System.out.println("Operacao nao pode ser realizada");
    }

    public void extrato(int year, Month month) {
        for (Transacao t : transacoes) {
            if (t.dataTransacao.getYear() == year && t.dataTransacao.getMonth() == month) {
                System.out.println(t);
            }
        }
    }

    public void transferir(float quantia, IConta contaDestino) {
        try {
            ContaCorrente contaTempDestino = (ContaCorrente) contaDestino;
            if (contaDestino instanceof ContaCorrente) {
                if (this.status && contaTempDestino.status && quantia > 0 && quantia <= this.saldo) {
                    this.saldo -= quantia;
                    contaTempDestino.saldo += quantia;
                    this.transacoes
                            .add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA_DEBITO,
                                    contaTempDestino));
                    contaTempDestino.transacoes
                            .add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA_CREDITO,
                                    this));
                    System.out.println("TRANSFERENCIA REALIZADA COM SUCESSO");
                } else {
                    System.out.println("Operacao nao pode ser realizada");
                }
            }
        } catch (Exception e) {
            if (this.status && contaDestino.getStatus() && quantia > 0 && quantia <= this.saldo) {
                this.saldo -= quantia;
                contaDestino.setSaldo(contaDestino.getSaldo() + (quantia - TAXA_BB));
                System.out.println("valor novo depois da taxa: " + contaDestino.getSaldo());
                this.transacoes
                        .add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA_DEBITO,
                                contaDestino));
                contaDestino.getTransacoes()
                        .add(new Transacao(quantia, LocalDateTime.now(), TipoTransacao.TRANSFERENCIA_CREDITO, this));
                System.out.println("TRANSFERENCIA REALIZADA COM SUCESSO");
            } else {
                System.out.println("Operacao nao pode ser realizada");
            }
        }
    }

    @Override
    public float getSaldo() {
        return saldo;
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @Override
    public ArrayList<Transacao> getTransacoes() {
        return transacoes;
    }

}
