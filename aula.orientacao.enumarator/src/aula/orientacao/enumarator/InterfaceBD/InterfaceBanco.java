package aula.orientacao.enumarator.InterfaceBD;

import aula.orientacao.enumarator.modelo.Cliente;
import aula.orientacao.enumarator.modelo.ContaCorrente;
import aula.orientacao.enumarator.modelo.ContaInvestimento;
import aula.orientacao.enumarator.modelo.ContaPoupanca;
import aula.orientacao.enumarator.persistencia.ClientePersistencia;
import java.util.Scanner;
import java.time.Month;

public class InterfaceBanco {
	private Scanner scan;
	private ClientePersistencia p;
	private String cpf;

	public InterfaceBanco() {
		p = new ClientePersistencia();
		scan = new Scanner(System.in);
		iniciar();
	}

	private void iniciar() {
		if (cpf == null) {
			System.out.println("DIGITE SEU CPF ANTES DE CONTINUAR PARA UM POSSIVEL CADASTRAMENTO OU CONSULTAS" +
					"\n>>>>>CASO NÃO TENHA CADASTRO, RELAXE, NÃO VAMOS USAR SEU CPF PARA NADA, É SÓ PARA SABER QUEM É VOCE<<<<<");
			cpf = scan.next();
		}
	}

	public void opcoes(int key) {
		switch (key) {
			case 1:
				cadastrarCliente();
				break;
			case 2:
				listarClientes();
				break;
			case 3:
				consultarClientes();
				break;
			case 4:
				removerCliente();
				break;
			case 5:
				criarContaAssociada();
				break;
			case 6:
				listarContas();
				break;
			case 7:
				removerConta();
				break;
			case 8:
				deposito();
				break;
			case 9:
				saque();
				break;
			case 10:
				transferencia();
				break;
			case 11:
				extrato();
				break;
			case 12:
				consultarSaldo();
				break;
			case 13:
				consultarBalanco();
				break;
			case 0:
				System.out.println("VOCE SAIU");
				cpf = null;
				iniciar();
				break;
		}
	}

	// 1- CADASTRAR CLIENTE
	private void cadastrarCliente() {
		System.out.println("DIGITE O NOME");
		String nome = scan.next();
		p.salvarCliente(new Cliente(cpf, nome));
		System.out.println("pressione qualquer tecla para sair");
		scan.next();
	}

	// 2- LISTAR CLIENTES
	private void listarClientes() {
		System.out.println("CLIENTES CADASTRADOS");
		p.listarClientes();
		System.out.println("pressione qualquer tecla para sair");
		scan.next();
	}

	// 3- CONSULTAR CLIENTE
	private void consultarClientes() {
		System.out.println("DIGITE O CPF DO CLIENTE");
		String cpf2 = scan.next();
		if (p.localizarClientePorCPF(cpf2) != null) {
			System.out.println("CLIENTE ENCONTRADO: " + p.localizarClientePorCPF(cpf2));
		} else {
			System.out.println("CLIENTE NÃO ENCONTRADO");
		}
		System.out.println("pressione qualquer tecla para sair");
		scan.next();
	}

	// 4- REMOVER CLIENTE
	private void removerCliente() {
		p.removerCliente(p.localizarClientePorCPF(cpf));
		System.out.println("pressione qualquer tecla para sair");
		scan.next();
	}

	// 5- CRIAR CONTA ASSOCIADA
	private void criarContaAssociada() {
		if (isCliente(cpf)) {
			System.out.println(
					"DIGITE NUMRERO PARA O TIPO DE CONTA: \n 1- CONTA CORRENTE \n 2- CONTA POUPANÇA \n 3- CONTA INVESTIMENTO");
			switch (scan.nextInt()) {
				case 1:
					System.out.println("DIGITE O NUMERO DA CONTA");
					p.localizarClientePorCPF(cpf).adicionarConta(new ContaCorrente(scan.next()));
					p.atualizarCliente(p.localizarClientePorCPF(cpf));
					break;
				case 2:
					System.out.println("DIGITE O NUMERO DA CONTA");
					p.localizarClientePorCPF(cpf).adicionarConta(new ContaPoupanca(scan.next()));
					p.atualizarCliente(p.localizarClientePorCPF(cpf));
					break;
				case 3:
					System.out.println("DIGITE O NUMERO DA CONTA");
					p.localizarClientePorCPF(cpf).adicionarConta(new ContaInvestimento(scan.next()));
					p.atualizarCliente(p.localizarClientePorCPF(cpf));
					break;
			}
			// System.out.println("DIGITE O NUMERO DA CONTA");
			// p.localizarClientePorCPF(cpf).adicionarConta(new ContaPoupanca(scan.next()));
			// p.atualizarCliente(p.localizarClientePorCPF(cpf));
		}
		System.out.println("pressione qualquer tecla para sair");
		scan.next();
	}

	// 6- LISTAR CONTAS
	private void listarContas() {
		System.out.println("LISTANDO CONTAS");
		if (isCliente(cpf)) {
			p.localizarClientePorCPF(cpf).listarContas();
		}
		System.out.println("pressione qualquer tecla para sair");
		scan.next();
	}

	// 7- REMOVER CONTA
	private void removerConta() {
		System.out.println("DIGITE O NUMERO DA CONTA");
		String numero = scan.next();
		if (isConta(numero) && isCliente(cpf)) {
			p.localizarClientePorCPF(cpf).removerConta(p.localizarClientePorCPF(cpf).localizarContaPorNumero(numero));
			p.atualizarCliente(p.localizarClientePorCPF(cpf));
			System.out.println("CONTA REMOVIDA COM SUCESSO");
		} else {
			System.out.println("CONTA NÃO ENCONTRADA");
		}
		System.out.println("pressione qualquer tecla para sair");
		scan.next();
	}

	// 8- DEPOSITO
	private void deposito() {
		System.out.println("DIGITE O NUMERO DA CONTA");
		String numero = scan.next();
		System.out.println("DIGITE O VALOR DO DEPOSITO");
		float valor = scan.nextFloat();
		if (isConta(numero) && isCliente(cpf)) {
			p.localizarClientePorCPF(cpf).localizarContaPorNumero(numero).depositar(valor);
			p.atualizarCliente(p.localizarClientePorCPF(cpf));
			System.out.println("DEPOSITO REALIZADO COM SUCESSO");
		}
		System.out.println("pressione qualquer tecla para sair");
		scan.next();
	}

	// 9- SAQUE
	private void saque() {
		System.out.println("DIGITE O NUMERO DA CONTA");
		String numero = scan.next();
		System.out.println("DIGITE O VALOR DO SAQUE");
		float valor2 = scan.nextFloat();
		if (isConta(numero) && isCliente(cpf)) {
			p.localizarClientePorCPF(cpf).localizarContaPorNumero(numero).sacar(valor2);
			p.atualizarCliente(p.localizarClientePorCPF(cpf));
			System.out.println("SAQUE REALIZADO COM SUCESSO");
		}
		System.out.println("pressione qualquer tecla para sair");
		scan.next();
	}

	// 10- TRANSFERENCIA
	private void transferencia() {
		System.out.println("DIGITE O NUMERO DA SUA CONTA");
		String numero = scan.next();
		System.out.println("DIGITE O CPF DO CLIENTE DE DESTINO");
		String cpf2 = scan.next();
		System.out.println("DIGITE O NUMERO DA CONTA DE DESTINO");
		String numero2 = scan.next();
		System.out.println("DIGITE O VALOR DA TRANSFERENCIA");
		float valor = scan.nextFloat();
		if (isConta(numero) && isConta(cpf2, numero2)) {
			p.localizarClientePorCPF(cpf).localizarContaPorNumero(numero).transferir(valor,
					p.localizarClientePorCPF(cpf2).localizarContaPorNumero(numero2));
			p.atualizarCliente(p.localizarClientePorCPF(cpf));
			p.atualizarCliente(p.localizarClientePorCPF(cpf2));
		}
		System.out.println("pressione qualquer tecla para sair");
		scan.next();
	}

	// 11- EXTRATO
	private void extrato() {
		System.out.println("DIGITE O NUMERO DA CONTA");
		String numero6 = scan.next();
		System.out.println("DIGITE O ANO");
		int ano = scan.nextInt();
		System.out.println("DIGITE O MES");
		int mes = scan.nextInt();
		Month mes2 = Month.of(mes);
		if (isConta(numero6) && isCliente(cpf)) {
			p.localizarClientePorCPF(cpf).localizarContaPorNumero(numero6).extrato(ano, mes2);
		}
		System.out.println("pressione qualquer tecla para sair");
		scan.next();
	}

	// 12- CONSULTAR SALDO
	private void consultarSaldo() {
		System.out.println("DIGITE O NUMERO DA CONTA");
		String numero = scan.next();
		if (isConta(numero)) {
			System.out.println("SALDO DA CONTA");
			System.out.println(p.localizarClientePorCPF(cpf).localizarContaPorNumero(numero).getSaldo());
		}
		System.out.println("pressione qualquer tecla para sair");
		scan.next();
	}

	// 13- CONSULTAR BALANÇO
	private void consultarBalanco() {
		if (isCliente(cpf)) {
			System.out.println("BALANÇO DA CONTA");
			System.out.println("saldo total das contas: " + p.localizarClientePorCPF(cpf).balanco());
		}
		System.out.println("pressione qualquer tecla para sair");
		scan.next();
	}

	private boolean isCliente(String cpf) {
		if (p.localizarClientePorCPF(cpf) != null) {
			return true;
		} else {
			System.out.println("CLIENTE INEXISTENTE POR FAVOR CADASTRE-SE OU DIGITE O CPF CORRETAMENTE");
			return false;
		}
	}

	private boolean isConta(String numero) {
		if (isCliente(cpf) && p.localizarClientePorCPF(cpf).localizarContaPorNumero(numero) != null) {
			return true;
		} else {
			System.out.println("CONTA INEXISTENTE NESTE CLIENTE POR FAVOR CADASTRE-SE OU DIGITE O NUMERO CORRETAMENTE");
			return false;
		}
	}

	private boolean isConta(String cpf, String numero) {
		if (isCliente(cpf) && p.localizarClientePorCPF(cpf).localizarContaPorNumero(numero) != null) {
			return true;
		} else {
			System.out.println("CONTA INEXISTENTE NESTE CLIENTE POR FAVOR CADASTRE-SE OU DIGITE O NUMERO CORRETAMENTE");
			return false;
		}
	}

}
