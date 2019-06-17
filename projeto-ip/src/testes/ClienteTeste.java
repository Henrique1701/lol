package testes;

import fachada.Matriz;
import filiais.RepositorioFiliais;
import filiais.RepositorioFiliaisLista;
import pedidos.RepositorioPedidos;
import pedidos.RepositorioPedidosLista;
import pessoas.*;
import produtos.RepositorioProdutos;
import produtos.RepositorioProdutosLista;
import utils.Endereco;

public class ClienteTeste {

    public static void main(String[] args) {
        RepositorioPessoas repositorioPessoasClientes = new RepositorioPessoasArray();
        // RepositorioPessoas repositorioPessoasClientes = new RepositorioPessoasLista();

        RepositorioPessoas repositorioPessoasFuncionarios = new RepositorioPessoasLista();
        // RepositorioPessoas repositorioPessoasFuncionarios = new RepositorioPessoasArray();

        RepositorioFiliais repositorioFiliais = new RepositorioFiliaisLista();
        RepositorioProdutos repositorioProdutos = new RepositorioProdutosLista();
        RepositorioPedidos repositorioPedidos = new RepositorioPedidosLista();

        Matriz fachada = new Matriz(repositorioPessoasClientes, repositorioPessoasFuncionarios, repositorioFiliais, repositorioProdutos, repositorioPedidos);
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
    }

}
