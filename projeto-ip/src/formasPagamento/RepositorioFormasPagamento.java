package formasPagamento;

public interface RepositorioFormasPagamento {
	public void inserir(FormaPagamento forma);
    public void atualizar(FormaPagamento forma) throws FormaPagamentoNaoEncontradaException;
    public void remover(int codigo) throws FormaPagamentoNaoEncontradaException;
    public FormaPagamento procurar(int codigo) throws FormaPagamentoNaoEncontradaException;
    public boolean existe(int codigo);
}
