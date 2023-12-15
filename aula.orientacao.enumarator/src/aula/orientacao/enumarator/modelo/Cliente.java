package aula.orientacao.enumarator.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import Interface.IConta;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	String cpf;
	String nome;
	private ArrayList<IConta> contas = new ArrayList<>();

	public Cliente(String cpf, String nome) {
		super();
		this.cpf = cpf;
		this.nome = nome;
	}

	public void adicionarConta(IConta c) {
		if (!contas.contains(c) && c != null)
			contas.add(c);
		else
			System.out.println(">>>CONTA CADASTRADA OU INEXISTENTE<<<");
	}

	public void removerConta(IConta c) {
		if (contas.contains(c))
			contas.remove(c);
		else
			System.out.println(">>>CONTA INEXISTENTE<<<");
	}

	public IConta localizarContaPorNumero(String numero) {
		IConta temp = whoIs(numero);
		if (contas.contains(temp)) {
			int index = contas.indexOf(temp);
			temp = contas.get(index);
			return temp;
		} else
			return null;
	}

	public void atualizarConta(IConta c) {
		if (contas.contains(c)) {
			int index = contas.indexOf(c);
			contas.set(index, c);
		} else
			System.out.println("Conta não localizada");
	}

	public float balanco() {
		float balanco = 0;
		for (IConta c : contas) {
			balanco += c.getSaldo();
		}
		return balanco;
	}

	public void listarContas() {
		for (IConta c : contas) {
			System.out.println(c);
		}
	}

	private IConta whoIs(String num) {
		ContaCorrente temp = new ContaCorrente(num);
		ContaInvestimento temp2 = new ContaInvestimento(num);
		ContaPoupanca temp3 = new ContaPoupanca(num);
		if (contas.contains(temp))
			return temp;
		else if (contas.contains(temp2))
			return temp2;
		else if (contas.contains(temp3))
			return temp3;
		else
			return null;
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + ", contas=" + contas + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}

}
