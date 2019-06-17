package ui;

import fachada.Matriz;
import filiais.RepositorioFiliais;
import filiais.RepositorioFiliaisArray;
import formasPagamento.FormaPagamento;
import formasPagamento.FormaPagamentoNaoEncontradaException;
import pedidos.*;
import pessoas.*;
import produtos.Produto;
import produtos.RepositorioProdutos;
import produtos.RepositorioProdutosArray;
import produtos.RepositorioProdutosLista;
import utils.Endereco;

public class Programa {

    public static void main(String[] args) {
        RepositorioPessoas repositorioPessoasClientes = new RepositorioPessoasArray();
        // RepositorioPessoas repositorioPessoasClientes = new RepositorioPessoasLista();

        RepositorioPessoas repositorioPessoasFuncionarios = new RepositorioPessoasLista();
        // RepositorioPessoas repositorioPessoasFuncionarios = new RepositorioPessoasArray();

        //RepositorioPedidos repositorioPedidos = new RepositorioPedidosLista();
        RepositorioPedidos repositorioPedidos = new RepositorioPedidosArray(1);

        RepositorioProdutos repositorioProdutos = new RepositorioProdutosLista();
        //RepositorioProdutos repositorioProdutos = new RepositorioProdutosArray(1);

        RepositorioFiliais repositorioFiliais = new RepositorioFiliaisArray(1);
        //RepositorioFiliais repositorioFiliais = new RepositorioFiliaisLista();

        Matriz fachada = new Matriz(repositorioPessoasClientes, repositorioPessoasFuncionarios, repositorioFiliais, repositorioProdutos, repositorioPedidos);


        System.out.println("---TESTE DE VALOR INVALIDO---");
        //Deve cadastrar um produto de valor negativo ou zero e falhar.
        try {
            Produto produtoValorInvalido = new Produto("Galeto", "Nada", -1.00);
            fachada.cadastrar(produtoValorInvalido);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---TESTE DE CADASTRAR PRODUTO---");
        //Deve cadastrar um produto.
        try {
            Produto produtoTeste = new Produto("Tapiocao", "Queijo", 2.00);
            fachada.cadastrar(produtoTeste);
            System.out.println(produtoTeste.getNome() + " cadastrado com sucesso.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Tenta cadastrar um produto j� cadastrado anteriormente e falhar.
        try {
            Produto produtoTeste = new Produto("Tapiocao", "Queijo", 2.00);
            fachada.cadastrar(produtoTeste);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---TESTE DE PROCURAR PRODUTO---");
        //Deve procurar um produto que existe.
        try {
            Produto produto = fachada.procurarProduto("Tapiocao");
            System.out.println(produto.getNome() + " encontrado com sucesso.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Tenta procurar um produto que n�o existe e falha.
        try {
            Produto produto = fachada.procurarProduto("Galeto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---TESTE DE ATUALIZAR PRODUTO---");
        //Deve atualizar um pedidos j� cadastrado.
        try {
            Produto novoProduto = new Produto("Tapiocao", "Presunto", 2.50);
            fachada.atualizar(novoProduto);
            System.out.println(novoProduto.getNome() + " atualizado com sucesso.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Tenta atualizar um produto que n�o existe e falha.
        try {
            Produto produtoInvalido = new Produto("Galeto", "Nada", 5.00);
            fachada.atualizar(produtoInvalido);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---TESTE DE EXISTE PRODUTO---");
        boolean produtoExiste;
        //Procura se um produto j� cadastrado existe.
        try {
            Produto produtoTeste = new Produto("Tapiocao", "Queijo", 2.00);
            produtoExiste = fachada.existeProduto(produtoTeste.getNome());
            System.out.println("O produto existe no sistema: " + produtoExiste);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Procura se um produto n�o cadastrado existe.
        produtoExiste = fachada.existeProduto("Galeto");
        System.out.println("O produto existe no sistema: " + produtoExiste);

        System.out.println("---TESTE DE REMOVER PRODUTO---");
        //Deve remover um produto existe.
        try {
            Produto produtoTeste = new Produto("Tapiocao", "Queijo", 2.00);
            fachada.removerProduto(produtoTeste.getNome());
            System.out.println(produtoTeste.getNome() + " foi removido com sucesso.");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        //Tenta remover um produto que n�o existe e falha.
        try {
            fachada.removerProduto("Galeto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // PEDIDOS


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
        } catch (Exception erro) {
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
        System.out.println("O pedido " + codigo3 + " existe: " + pedido3Existe + "\n");


        // CLIENTES
        Endereco enderecoTeste = new Endereco("50740-445", "Rua dos Bobos", 0, null, "Recife", "PE", "Casinha");

        String CPF_CLIENTE_TESTE = "85000205049";
        Cliente clienteTeste = new Cliente("Joao Pedro", enderecoTeste, CPF_CLIENTE_TESTE);

        /* TESTES DE CADASTRAR CLIENTE */
        System.out.println("----- TESTES DE CADASTRAR CLIENTE -----");
        // Deve cadastrar um cliente no Cadastro de Clientes
        try {
            fachada.cadastrar(clienteTeste);
            System.out.println(clienteTeste.getNome() + " cadastrado com sucesso");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Deve tentar cadastrar o mesmo Cliente no Cadastro novamente e falhar
        try {
            fachada.cadastrar(clienteTeste);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /* TESTES DE PROCURAR CLIENTE */
        System.out.println("----- TESTES DE PROCURAR CLIENTE -----");
        // Busca um cliente que existe
        try {
            Cliente cliente = fachada.procurarCliente(CPF_CLIENTE_TESTE);
            System.out.println(cliente.getNome() + " encontrado com sucesso");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Busca um cliente que nao existe
        try {
            Cliente cliente = fachada.procurarCliente("123");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /* TESTES DE ATUALIZAR CLIENTE */
        System.out.println("----- TESTES DE ATUALIZAR CLIENTE -----");
        // Atualiza um cliente que existe
        try {
            Cliente novoCliente = new Cliente("Matheus", enderecoTeste, CPF_CLIENTE_TESTE);
            fachada.atualizar(novoCliente);
            System.out.println(novoCliente.getNome() + " atualizado com sucesso");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Atualiza um cliente que nao existe
        try {
            Cliente novoClienteNaoExiste = new Cliente("Troll", enderecoTeste, "123");
            fachada.atualizar(novoClienteNaoExiste);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /* TESTES DE EXISTE CLIENTE */
        System.out.println("----- TESTES DE EXISTE CLIENTE -----");
        // Busca um cliente que existe
        boolean clienteExiste = fachada.existeCliente(clienteTeste.getIdentificador());
        System.out.println("O cliente buscado existe: " + clienteExiste);

        // Busca um cliente que nao existe
        clienteExiste = fachada.existeCliente("0");
        System.out.println("O cliente buscado existe: " + clienteExiste);

        /* TESTES DE REMOVER CLIENTE */
        System.out.println("----- TESTES DE REMOVER CLIENTE -----");
        // Remove um cliente que existe
        try {
            fachada.removerCliente(clienteTeste.getIdentificador());
            System.out.println(clienteTeste.getNome() + " removido com sucesso");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Remove um cliente que nao existe
        try {
            fachada.removerCliente("123");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // FUNCIONARIOS

        String CPF_FUNCIONARIO_TESTE = "85000205049";
        String MATRICULA_FUNCIONARIO = "1234";
        Funcionario FuncionarioTeste = new Funcionario("Joao Pedro", enderecoTeste, CPF_FUNCIONARIO_TESTE, MATRICULA_FUNCIONARIO);

        /* TESTES DE CADASTRAR Funcionario */
        System.out.println("----- TESTES DE CADASTRAR Funcionario -----");
        // Deve cadastrar um Funcionario no Cadastro de Funcionarios
        try {
            fachada.cadastrar(FuncionarioTeste);
            System.out.println(FuncionarioTeste.getNome() + " cadastrado com sucesso");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Deve tentar cadastrar o mesmo Funcionario no Cadastro novamente e falhar
        try {
            fachada.cadastrar(FuncionarioTeste);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /* TESTES DE PROCURAR Funcionario */
        System.out.println("----- TESTES DE PROCURAR Funcionario -----");
        // Busca um Funcionario que existe
        try {
            Funcionario Funcionario = fachada.procurarFuncionario(CPF_FUNCIONARIO_TESTE);
            System.out.println(Funcionario.getNome() + " encontrado com sucesso");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Busca um Funcionario que nao existe
        try {
            Funcionario Funcionario = fachada.procurarFuncionario("123");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /* TESTES DE ATUALIZAR Funcionario */
        System.out.println("----- TESTES DE ATUALIZAR Funcionario -----");
        // Atualiza um Funcionario que existe
        try {
            Funcionario novoFuncionario = new Funcionario("Matheus", enderecoTeste, CPF_FUNCIONARIO_TESTE, MATRICULA_FUNCIONARIO);
            fachada.atualizar(novoFuncionario);
            System.out.println(novoFuncionario.getNome() + " atualizado com sucesso");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Atualiza um Funcionario que nao existe
        try {
            Funcionario novoFuncionarioNaoExiste = new Funcionario("Troll", enderecoTeste, "123", MATRICULA_FUNCIONARIO);
            fachada.atualizar(novoFuncionarioNaoExiste);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        /* TESTES DE EXISTE Funcionario */
        System.out.println("----- TESTES DE EXISTE Funcionario -----");
        // Busca um Funcionario que existe
        boolean FuncionarioExiste = fachada.existeFuncionario(FuncionarioTeste.getIdentificador());
        System.out.println("O Funcionario buscado existe: " + FuncionarioExiste);

        // Busca um Funcionario que nao existe
        FuncionarioExiste = fachada.existeFuncionario("0");
        System.out.println("O Funcionario buscado existe: " + FuncionarioExiste);

        /* TESTES DE REMOVER Funcionario */
        System.out.println("----- TESTES DE REMOVER Funcionario -----");
        // Remove um Funcionario que existe
        try {
            fachada.removerFuncionario(FuncionarioTeste.getIdentificador());
            System.out.println(FuncionarioTeste.getNome() + " removido com sucesso");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Remove um Funcionario que nao existe
        try {
            fachada.removerFuncionario("123");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

