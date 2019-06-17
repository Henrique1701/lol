package formasPagamento;

import utils.RepositorioException;

public class CadastroFormasPagamento {
	private RepositorioFormasPagamento repositorioFormasPagamento;
	
	public CadastroFormasPagamento(RepositorioFormasPagamento r) {
		this.repositorioFormasPagamento = r;
	}
	
	public void cadastrar (FormaPagamento formapagamento) throws FormaPagamentoJaCadastradaException, RepositorioException{
		if(this.repositorioFormasPagamento.existe(formapagamento.getCodigo()))
		{
			throw new FormaPagamentoJaCadastradaException(formapagamento.getForma());
		}
		this.repositorioFormasPagamento.inserir(formapagamento);
	}
	
	public FormaPagamento procurar (int codigo) throws FormaPagamentoNaoEncontradaException
	{
		return this.repositorioFormasPagamento.procurar(codigo);
	}
	
	public void remover (int codigo) throws FormaPagamentoNaoEncontradaException{
		this.repositorioFormasPagamento.remover(codigo);
	}
	
	public void atualizar (FormaPagamento formapagamento) throws FormaPagamentoNaoEncontradaException
	{
		this.repositorioFormasPagamento.atualizar(formapagamento);
	}
	
	public boolean existe (int codigo)
	{
		return this.repositorioFormasPagamento.existe(codigo);
	}
}