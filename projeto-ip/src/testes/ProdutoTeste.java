package testes;

import fachada.Matriz;
import filiais.RepositorioFiliais;
import filiais.RepositorioFiliaisLista;
import pedidos.RepositorioPedidos;
import pedidos.RepositorioPedidosLista;
import pessoas.*;
import produtos.*;
import utils.Endereco;

public class ProdutoTeste {

    public static void main (String[] args){
        RepositorioProdutos repositorioProdutos = new RepositorioProdutosLista();
        //RepositorioProdutos repositorioProdutos = new RepositorioProdutosArray(1);

        RepositorioPessoas repositorioPessoasClientes = new RepositorioPessoasArray();
        // RepositorioPessoas repositorioPessoasClientes = new RepositorioPessoasLista();

        RepositorioPessoas repositorioPessoasFuncionarios = new RepositorioPessoasLista();
        // RepositorioPessoas repositorioPessoasFuncionarios = new RepositorioPessoasArray(1);

        RepositorioFiliais repositorioFiliais = new RepositorioFiliaisLista();
        RepositorioPedidos repositorioPedidos = new RepositorioPedidosLista();

        Matriz fachada = new Matriz(repositorioPessoasClientes, repositorioPessoasFuncionarios, repositorioFiliais, repositorioProdutos, repositorioPedidos);


        System.out.println("---TESTE DE VALOR INVÁLIDO---");
        //Deve cadastrar um produto de valor negativo ou zero e falhar.
        try {
            Produto produtoValorInvalido = new Produto("Galeto", "Nada", -1.00);
            fachada.cadastrar(produtoValorInvalido);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("---TESTE DE CADASTRAR PRODUTO---");
        //Deve cadastrar um produto.
        try{
            Produto produtoTeste = new Produto("Tapiocao","Queijo", 2.00);
            fachada.cadastrar(produtoTeste);
            System.out.println(produtoTeste.getNome() + " cadastrado com sucesso.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        //Tenta cadastrar um produto já cadastrado anteriormente e falhar.
        try{
            Produto produtoTeste = new Produto("Tapiocao","Queijo", 2.00);
            fachada.cadastrar(produtoTeste);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("---TESTE DE PROCURAR PRODUTO---");
        //Deve procurar um produto que existe.
        try {
            Produto produto = fachada.procurarProduto("Tapiocao");
            System.out.println(produto.getNome() + " encontrado com sucesso.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        //Tenta procurar um produto que não existe e falha.
        try{
            Produto produto = fachada.procurarProduto("Galeto");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("---TESTE DE ATUALIZAR PRODUTO---");
        //Deve atualizar um pedidos já cadastrado.
        try {
            Produto novoProduto = new Produto("Tapiocao","Presunto",2.50);
            fachada.atualizar(novoProduto);
            System.out.println(novoProduto.getNome() + " atualizado com sucesso.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        //Tenta atualizar um produto que não existe e falha.
        try {
            Produto produtoInvalido = new Produto("Galeto","Nada",5.00);
            fachada.atualizar(produtoInvalido);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("---TESTE DE EXISTE PRODUTO---");
        boolean produtoExiste;
        //Procura se um produto já cadastrado existe.
        try{
            Produto produtoTeste = new Produto("Tapiocao","Queijo", 2.00);
            produtoExiste = fachada.existeProduto(produtoTeste.getNome());
            System.out.println("O produto existe no sistema: "+produtoExiste);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        //Procura se um produto não cadastrado existe.
        produtoExiste = fachada.existeProduto("Galeto");
        System.out.println("O produto existe no sistema: "+produtoExiste);

        System.out.println("---TESTE DE REMOVER PRODUTO---");
        //Deve remover um produto existe.
        try{
            Produto produtoTeste = new Produto("Tapiocao","Queijo", 2.00);
            fachada.removerProduto(produtoTeste.getNome());
            System.out.println(produtoTeste.getNome() + " foi removido com sucesso.");
        }catch(Exception e){
            System.out.print(e.getMessage());
        }

        //Tenta remover um produto que não existe e falha.
        try{
            fachada.removerProduto("Galeto");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }



    }
}
