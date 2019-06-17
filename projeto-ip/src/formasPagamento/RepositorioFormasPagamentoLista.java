package formasPagamento;

public class RepositorioFormasPagamentoLista implements RepositorioFormasPagamento {
    private FormaPagamento FormaPagamento;
    private RepositorioFormasPagamentoLista proximo;

    public RepositorioFormasPagamentoLista() {
        this.FormaPagamento = null;
        this.proximo = new RepositorioFormasPagamentoLista();
    }

    public void inserir(FormaPagamento FormaPagamento) {
        if(this.FormaPagamento == null) {
            this.FormaPagamento = FormaPagamento;
            this.proximo = new RepositorioFormasPagamentoLista();
        } else {
            this.proximo.inserir(FormaPagamento);
        }
    }

    public void atualizar(FormaPagamento FormaPagamento) throws FormaPagamentoNaoEncontradaException {
        if(this.FormaPagamento == null) {
            throw new FormaPagamentoNaoEncontradaException(FormaPagamento.getCodigo());
        } else if (this.FormaPagamento.getCodigo()== FormaPagamento.getCodigo()) {
            this.FormaPagamento = FormaPagamento;
        } else {
            this.proximo.atualizar(FormaPagamento);
        }
    }

    public void remover(int codigo) throws FormaPagamentoNaoEncontradaException {
        if(this.FormaPagamento == null) {
            throw new FormaPagamentoNaoEncontradaException(codigo);
        } else if (this.FormaPagamento.getCodigo()== FormaPagamento.getCodigo()) {
            this.FormaPagamento = this.proximo.FormaPagamento;
            this.proximo = this.proximo.proximo;
        } else {
            this.proximo.remover(codigo);
        }
    }

    public FormaPagamento procurar(int codigo) throws FormaPagamentoNaoEncontradaException {
        if(this.FormaPagamento == null) {
            throw new FormaPagamentoNaoEncontradaException(codigo);
        } else if (this.FormaPagamento.getCodigo()==codigo) {
            return this.FormaPagamento;
        } else {
            return this.proximo.procurar(codigo);
        }
    }

    public boolean existe(int codigo) {
        if(this.FormaPagamento == null) {
            return false;
        } else if (this.FormaPagamento.getCodigo()==codigo) {
            return true;
        } else {
            return this.proximo.existe(codigo);
        }
    }
}