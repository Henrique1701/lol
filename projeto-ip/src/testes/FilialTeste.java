package testes;

import fachada.Matriz;
import filiais.*;
import pedidos.RepositorioPedidos;
import pedidos.RepositorioPedidosArray;
import pessoas.RepositorioPessoas;
import pessoas.RepositorioPessoasArray;
import produtos.RepositorioProdutos;
import produtos.RepositorioProdutosArray;
import utils.Endereco;

public class FilialTeste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RepositorioPessoas funcionarios = new RepositorioPessoasArray();
		RepositorioPessoas clientes = new RepositorioPessoasArray();
		RepositorioFiliais filiais = new RepositorioFiliaisArray(1);
		//RepositorioFiliais filiais = new RepositorioFiliaisLista();
		RepositorioProdutos produtos = new RepositorioProdutosArray(1);
		RepositorioPedidos pedidos = new RepositorioPedidosArray(1);
		Endereco endereco1 = new Endereco ("55820-000","Rua Jose Cavalcanti", 99, "Zona quase rural", "Atui", "Estado Livre de Direito", "Ao lado do Amorintel");
		Endereco endereco2 = new Endereco ("92201-20", "Rua João de Lira", 456, "Centro", "Afogados", "Amazonas", "Ao lado da oficina de seu Zé");
		Matriz moden = new Matriz (funcionarios, clientes, filiais, produtos, pedidos);
		Filial filial1 = new Filial(4558, "Tairone da mandioca", endereco1, funcionarios, pedidos);
		Filial filial2 = new Filial(488, "Tapiocaria da Steffany", endereco2, funcionarios, pedidos);
		Filial filial3 = new Filial(488, "Tapiocaria da Steffany", endereco1, funcionarios, pedidos);
				
		//CADASTRAR
		
		//Cadastro Filial
		try {
			System.out.println("*********************\n* Teste de cadastro *\n*********************\n");
			moden.cadastrar(filial1);
			System.out.println("A filial " + filial1.getNome() + " foi cadastrada com sucesso.");
		} catch (FilialJaCadastradaException e) {
			System.out.println(e.getMessage());
		} 
		
		//Cadastro Repetido
		try {
			System.out.println("\n******************************\n* Teste de cadastro repetido *\n******************************\n");
			moden.cadastrar(filial2);
			System.out.println("A filial " + filial2.getNome() + " foi cadastrada com sucesso.");
			moden.cadastrar(filial1);
			System.out.println("A filial " + filial1.getNome() + " foi cadastrada com sucesso.");
		} catch (FilialJaCadastradaException e) {
			System.out.println(e.getMessage());
		} 
		
		//REMOVER
		
		// Remocao filial
		try {
			System.out.println("\n********************\n* Teste de remocao *\n********************\n");
			moden.removerFilial(4558);
			System.out.println("A filial foi removida com sucesso.");
		} catch (FilialNaoEncontradaException e) {
			System.out.println(e.getMessage());
		}
		
		// Remocao filial inexistente
		
		try {
			System.out.println("\n******************************************\n* Teste de remocao de filial inexistente *\n******************************************\n");
			moden.removerFilial(4558);
			System.out.println("A filial foi removida com sucesso.");
		} catch (FilialNaoEncontradaException e) {
			System.out.println(e.getMessage());
		}
		
		// Atualizar
		
		// Atualizacao filial
		try {
			System.out.println("\n************************\n* Teste de atualizacao *\n************************\n");
			moden.atualizar(filial3);
			System.out.println("A filial " + filial3.getNome() + " foi atualizada com sucesso.");
		} catch (FilialNaoEncontradaException e) {
			System.out.println(e.getMessage());
		}
		
		// Atualizacao filial inexistente
		try {
			System.out.println("\n**********************************************\n* Teste de atualizacao de filial inexistente *\n**********************************************\n");
			moden.atualizar(filial1);
			System.out.println("A filial " + filial3.getNome() + " foi atualizada com sucesso.");
		} catch (FilialNaoEncontradaException e) {
			System.out.println(e.getMessage());
		}
		
		// PROCURAR
		
		// Procura filial
		try {
			System.out.println("\n********************\n* Teste de procura *\n********************\n");
			Filial filial4 = moden.procurarFilial(488);
			System.out.println("A filial com o código " + 488 + " é " + filial4.getNome() + ".");
		} catch (FilialNaoEncontradaException e) {
			System.out.println(e.getMessage());
		}
		
		// Procura filial inexistente
		try {
			System.out.println("\n******************************************\n* Teste de procura de filial inexistente *\n******************************************\n");
			moden.procurarFilial(4558);
			System.out.println("A filial com o código " + 4558 + " é " + filial1.getNome() + ".");
		} catch (FilialNaoEncontradaException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
