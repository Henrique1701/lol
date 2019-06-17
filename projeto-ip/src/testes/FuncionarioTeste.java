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

public class FuncionarioTeste {

    public static void main(String[] args) {
        RepositorioPessoas repositorioPessoasFuncionarios = new RepositorioPessoasArray();
        // RepositorioPessoas repositorioPessoasFuncionarios = new RepositorioPessoasLista();

        RepositorioPessoas repositorioPessoasClientes = new RepositorioPessoasLista();
        // RepositorioPessoas repositorioPessoasClientes = new RepositorioPessoasArray(1);

        RepositorioFiliais repositorioFiliais = new RepositorioFiliaisLista();
        RepositorioProdutos repositorioProdutos = new RepositorioProdutosLista();

        RepositorioPedidos repositorioPedidos = new RepositorioPedidosLista();
		Matriz fachada = new Matriz(repositorioPessoasFuncionarios, repositorioPessoasClientes, repositorioFiliais, repositorioProdutos, repositorioPedidos);
        Endereco enderecoTeste = new Endereco("50740-445", "Rua dos Bobos", 0, null, "Recife", "PE", "Casinha");

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
