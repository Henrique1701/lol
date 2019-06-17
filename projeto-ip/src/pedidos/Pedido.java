package pedidos;

import formasPagamento.FormaPagamento;
import pessoas.Cliente;
import pessoas.Funcionario;
import produtos.RepositorioProdutos;

public class Pedido {
	private String codigo;
	private RepositorioProdutos repositorioProduto;
	private Cliente cliente;
	private Funcionario funcionario;
	private FormaPagamento formaPagamento;

	public Pedido(String codigo, RepositorioProdutos produtos, Cliente cliente, Funcionario funcionario, FormaPagamento formaPagamento) {
		this.codigo = codigo;
		this.repositorioProduto = produtos;
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.formaPagamento = formaPagamento;
	}

	public String getCodigo() {
		return codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public RepositorioProdutos getProdutos() {
		return repositorioProduto;
	}
}