package aula.orientacao.enumarator.modelo;

import java.util.Scanner;

import aula.orientacao.enumarator.InterfaceBD.InterfaceBanco;

public class Aplicacao {

	public static void main(String[] args) {
		InterfaceBanco i = new InterfaceBanco();
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("SELECIONE A OPÇÃO DESEJADA");
			System.out.println(
					" 1- CADASTRAR CLIENTE \n 2- LISTAR CLIENTES \n 3- CONSULTAR CLIENTES \n 4- REMOVER CLIENTE" +
							"\n 5- CRIAR CONTA ASSOCIADA \n 6- LISTAR CONTAS \n 7- REMOVER CONTA \n 8- DEPOSITO \n 9- SAQUE \n "
							+
							"10- TRANSFERENCIA \n 11- EXTRATO \n 12- CONSULTAR SALDO \n 13- CONSULTAR BALANÇO \n TECLE 0 PARA SAIR DA CONTA");
			try {
				i.opcoes(scan.nextInt());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("OPÇÃO INVALIDA :()");
			}
		}
	}

}
