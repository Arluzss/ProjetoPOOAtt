package Interface;

import java.time.Month;
import java.util.ArrayList;

import aula.orientacao.enumarator.modelo.Transacao;

public interface IConta {
    float TAXA_BB = 0.85f;
    void depositar(float quantia);
    void sacar(float quantia);
    void extrato(int year, Month month);
    void transferir(float quantia, IConta iConta);
    float getSaldo();
    boolean getStatus();
    void setSaldo(float saldo);
    ArrayList<Transacao> getTransacoes();
}