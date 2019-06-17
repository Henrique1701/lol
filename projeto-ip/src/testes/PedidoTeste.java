package testes;

import fachada.*;
import pessoas.*;
import filiais.*;
import produtos.*;
import pedidos.*;
import formasPagamento.*;
import utils.*;

public class PedidoTeste {
	public static void main(String[] args) {
		
		// PARTE COMUM
		//RepositorioPessoas repositorioPessoasClientes = new RepositorioPessoasLista();
		//RepositorioPessoas repositorioPessoasFuncionarios = new RepositorioPessoasLista();
		//RepositorioFiliais repositorioFiliais = new RepositorioFiliaisLista();
		//RepositorioProdutos repositorioProdutos = new RepositorioProdutosLista();
		
		RepositorioPessoas repositorioPessoasClientes = new RepositorioPessoasArray();
		RepositorioPessoas repositorioPessoasFuncionarios = new RepositorioPessoasArray();
		RepositorioFiliais repositorioFiliais = new RepositorioFiliaisArray(1);
		RepositorioProdutos repositorioProdutos = new RepositorioProdutosArray(1);
		
		// A TODOS
		
		// PARTE DE PEDIDOS
		//RepositorioPedidos repositorioPedidos = new RepositorioPedidosLista();
		RepositorioPedidos repositorioPedidos = new RepositorioPedidosArray(1);

		Matriz fachada = new Matriz(repositorioPessoasClientes, repositorioPessoasFuncionarios, repositorioFiliais, repositorioProdutos, repositorioPedidos);

		String codigo1 = "001";
		String codigo2 = "002";
		String codigo3 = "003";
		
		//RepositorioProdutosLista produto = new RepositorioProdutosLista();
		RepositorioProdutosArray produto = new RepositorioProdutosArray(1);

		// ENDERECO DE CLIENTE E FUNCIONARIO (AMBAS AS CLASSES PRECISAM DE ENDERECO)
		Endereco endereco = new Endereco("50740-445", "Rua dos Bobos", 0, null, "Recife", "PE", "Casinha");

		Cliente cliente1 = new Cliente("Joao Pedro", endereco, "789.654.321-00");
		Funcionario funcionario1 = new Funcionario("Tia", endereco, "321.654.987-00", "1");
		FormaPagamento formaPagamento1 = new FormaPagamento("Cartao de Credito", 10.0);
		
		Cliente cliente2 = new Cliente("Matheus Correia", endereco, "789.654.321-10");
		Funcionario funcionario2 = new Funcionario("Tio", endereco, "321.654.987-10", "2");
		//FormaPagamento formaPagamento2 = new FormaPagamento("Dinheiro", 30.0);
		
		try {
			fachada.cadastrar(cliente1);
			fachada.cadastrar(cliente2);
			fachada.cadastrar(funcionario1);
			//fachada.cadastrar(formaPagamento1);
		} catch (Exception erro){
			System.out.println(erro.getMessage());
		}
		System.out.println("\n" + "----- CADASTRAR DOIS PEDIDOS -----" + "\n");
		try {
			Pedido pedido = new Pedido(codigo1, produto, cliente1, funcionario1, formaPagamento1);
			fachada.cadastrar(pedido);
			System.out.println("O pedido " + pedido.getCodigo() + " foi cadastrado." + "\n");
			Pedido pedido2 = new Pedido(codigo2, produto, cliente1, funcionario1, formaPagamento1);
			fachada.cadastrar(pedido2);
			System.out.println("O pedido " + pedido2.getCodigo() + " foi cadastrado." + "\n");
		} catch (PedidoJaExistenteException pedidoJaExistente) {
			System.out.println(pedidoJaExistente.getMessage() + "\n");
		} catch (PessoaNaoEncontradaException pessoaNaoEncontrada) {
			System.out.println(pessoaNaoEncontrada.getMessage() + "\n");
		} catch (FormaPagamentoNaoEncontradaException formaPagamentoNaoEncontrada) {
			System.out.println(formaPagamentoNaoEncontrada.getMessage() + "\n");
		}
		System.out.println("----- CADASTRAR UM PEDIDO JA EXISTENTE -----" + "\n");
		try {
			Pedido pedido = new Pedido(codigo1, produto, cliente1, funcionario1, formaPagamento1);
			fachada.cadastrar(pedido);
			System.out.println("O pedido " + pedido.getCodigo() + " foi cadastrado." + "\n");
		} catch (PedidoJaExistenteException pedidoJaExistente) {
			System.out.println(pedidoJaExistente.getMessage() + "\n");
		} catch (PessoaNaoEncontradaException pessoaNaoEncontrada) {
			System.out.println(pessoaNaoEncontrada.getMessage() + "\n");
		} catch (FormaPagamentoNaoEncontradaException formaPagamentoNaoEncontrada) {
			System.out.println(formaPagamentoNaoEncontrada.getMessage() + "\n");
		}
		System.out.println("----- CADASTRAR UM PEDIDO COM FUNCIONARIO NAO EXISTENTE -----" + "\n");
		try {
			Pedido pedido = new Pedido(codigo1, produto, cliente2, funcionario2, formaPagamento1);
			fachada.cadastrar(pedido);
			System.out.println("O pedido " + pedido.getCodigo() + " foi cadastrado." + "\n");
		} catch (PedidoJaExistenteException pedidoJaExistente) {
			System.out.println(pedidoJaExistente.getMessage() + "\n");
		} catch (PessoaNaoEncontradaException pessoaNaoEncontrada) {
			System.out.println(pessoaNaoEncontrada.getMessage() + "\n");
		} catch (FormaPagamentoNaoEncontradaException formaPagamentoNaoEncontrada) {
			System.out.println(formaPagamentoNaoEncontrada.getMessage() + "\n");
		}
		/* System.out.println("----- CADASTRAR UMA FORMA DE PAGAMENTO NAO EXISTENTE -----" + "\n");
		try {
			Pedido pedido = new Pedido(codigo1, produto, cliente1, funcionario1, formaPagamento2);
			fachada.cadastrar(pedido);
			System.out.println("O pedido " + pedido.getCodigo() + " foi cadastrado." + "\n");
		} catch (PedidoJaExistenteException pedidoJaExistente) {
			System.out.println(pedidoJaExistente.getMessage() + "\n");
		} catch (PessoaNaoEncontradaException pessoaNaoEncontrada) {
			System.out.println(pessoaNaoEncontrada.getMessage() + "\n");
		} catch (FormaPagamentoNaoEncontradaException formaPagamentoNaoEncontrada) {
			System.out.println(formaPagamentoNaoEncontrada.getMessage() + "\n");
		}
		*/
		System.out.println("----- ATUALIZAR UM PEDIDO -----" + "\n");
		try {
			Pedido pedido = new Pedido(codigo1, produto, cliente1, funcionario1, formaPagamento1);
			Pedido pedidoAtualizar = new Pedido(codigo1, produto, cliente2, funcionario1, formaPagamento1);
			fachada.atualizar(pedidoAtualizar);
			System.out.println("O pedido " + pedido.getCodigo() + " foi atualizado." + "\n");
		} catch (PedidoNaoEncontradoException pedidoNaoEncontrado) {
			System.out.println(pedidoNaoEncontrado.getMessage() + "\n");
		} catch (PessoaNaoEncontradaException pessoaNaoEncontrada) {
			System.out.println(pessoaNaoEncontrada.getMessage() + "\n");
		} catch (FormaPagamentoNaoEncontradaException formaPagamentoNaoEncontrada) {
			System.out.println(formaPagamentoNaoEncontrada.getMessage() + "\n");
		}
		System.out.println("----- ATUALIZAR UM PEDIDO NAO EXISTENTE -----" + "\n");
		try {
			Pedido pedido = new Pedido(codigo3, produto, cliente1, funcionario1, formaPagamento1);
			fachada.atualizar(pedido);
			System.out.println("O pedido " + pedido.getCodigo() + " foi atualizado." + "\n");
		} catch (PedidoNaoEncontradoException pedidoNaoEncontrado) {
			System.out.println(pedidoNaoEncontrado.getMessage() + "\n");
		} catch (PessoaNaoEncontradaException pessoaNaoEncontrada) {
			System.out.println(pessoaNaoEncontrada.getMessage() + "\n");
		} catch (FormaPagamentoNaoEncontradaException formaPagamentoNaoEncontrada) {
			System.out.println(formaPagamentoNaoEncontrada.getMessage() + "\n");
		}
		System.out.println("----- ATUALIZAR UM PEDIDO COM UM FUNCIONARIO NAO EXISTENTE -----" + "\n");
		try {
			Pedido pedido = new Pedido(codigo1, produto, cliente2, funcionario2, formaPagamento1);
			fachada.atualizar(pedido);
			System.out.println("O pedido " + pedido.getCodigo() + " foi atualizado." + "\n");
		} catch (PedidoNaoEncontradoException pedidoNaoEncontrado) {
			System.out.println(pedidoNaoEncontrado.getMessage() + "\n");
		} catch (PessoaNaoEncontradaException pessoaNaoEncontrada) {
			System.out.println(pessoaNaoEncontrada.getMessage() + "\n");
		} catch (FormaPagamentoNaoEncontradaException formaPagamentoNaoEncontrada) {
			System.out.println(formaPagamentoNaoEncontrada.getMessage() + "\n");
		}
		/*
		System.out.println("----- ATUALIZAR UM PEDIDO COM UMA FORMA DE PAGAMENTO NAO EXISTENTE -----" + "\n");
		try {
			Pedido pedido = new Pedido(codigo1, produto, cliente2, funcionario1, formaPagamento2);
			fachada.atualizar(pedido);
			System.out.println("O pedido " + pedido.getCodigo() + " foi atualizado." + "\n");
		} catch (PedidoNaoEncontradoException pedidoNaoEncontrado) {
			System.out.println(pedidoNaoEncontrado.getMessage() + "\n");
		} catch (PessoaNaoEncontradaException pessoaNaoEncontrada) {
			System.out.println(pessoaNaoEncontrada.getMessage() + "\n");
		} catch (FormaPagamentoNaoEncontradaException formaPagamentoNaoEncontrada) {
			System.out.println(formaPagamentoNaoEncontrada.getMessage() + "\n");
		}
		*/
		System.out.println("----- PROCURAR UM PEDIDO -----" + "\n");
		try {
			Pedido pedido = new Pedido(codigo1, produto, cliente1, funcionario1, formaPagamento1);
			fachada.procurarPedido(codigo1);
			System.out.println("O pedido " + pedido.getCodigo() + " foi encontrado." + "\n");
		} catch (PedidoNaoEncontradoException PedidoNaoEncontrado) {
			System.out.println(PedidoNaoEncontrado.getMessage() + "\n");
		}
		System.out.println("----- PROCURAR UM PEDIDO NAO EXISTENTE -----" + "\n");
		try {
			Pedido pedido = new Pedido(codigo3, produto, cliente1, funcionario1, formaPagamento1);
			fachada.procurarPedido(codigo3);
			System.out.println("O pedido " + pedido.getCodigo() + " foi encontrado." + "\n");
		} catch (PedidoNaoEncontradoException PedidoNaoEncontrado) {
			System.out.println(PedidoNaoEncontrado.getMessage() + "\n");
		}
		System.out.println("----- REMOVER UM PEDIDO -----" + "\n");
		try {
			Pedido pedido = new Pedido(codigo1, produto, cliente1, funcionario1, formaPagamento1);
			fachada.removerPedido(codigo1);
			System.out.println("O pedido " + pedido.getCodigo() + " foi removido." + "\n");
		} catch (PedidoNaoEncontradoException PedidoNaoEncontrado) {
			System.out.println(PedidoNaoEncontrado.getMessage() + "\n");
		}
		System.out.println("----- REMOVER UM PEDIDO NAO EXISTENTE -----" + "\n");
		try {
			Pedido pedido = new Pedido(codigo1, produto, cliente1, funcionario1, formaPagamento1);
			fachada.removerPedido(codigo1);
			System.out.println("O pedido " + pedido.getCodigo() + " foi removido." + "\n");
		} catch (PedidoNaoEncontradoException PedidoNaoEncontrado) {
			System.out.println(PedidoNaoEncontrado.getMessage() + "\n");
		}
		System.out.println("----- VER SE OS PEDIDOS EXISTEM -----" + "\n");
		boolean pedidoExiste = fachada.existePedido(codigo1);
		System.out.println("O pedido " + codigo1 + " existe: " + pedidoExiste);
		boolean pedido2Existe = fachada.existePedido(codigo2);
		System.out.println("O pedido " + codigo2 + " existe: " + pedido2Existe);
		boolean pedido3Existe = fachada.existePedido(codigo3);
		System.out.println("O pedido " + codigo3 + " existe: " + pedido3Existe);
	}
}