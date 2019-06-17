package formasPagamento;

public class RepositorioFormasPagamentoArray implements RepositorioFormasPagamento {
    private FormaPagamento[] FormaPagamento;
    private int indice;

    public RepositorioFormasPagamentoArray(int tamanho) {
        this.FormaPagamento = new FormaPagamento[tamanho];
        this.indice = 0;
    }

    public void inserir(FormaPagamento FormaPagamento) {
    	FormaPagamento.setCodigo(indice);
        this.FormaPagamento[indice] = FormaPagamento;
        this.indice++;
    }

    public void atualizar(FormaPagamento FormaPagamento) throws FormaPagamentoNaoEncontradaException {
        int indiceFormaPagamento = this.getIndice(FormaPagamento.getCodigo());
        this.FormaPagamento[indice] = FormaPagamento;
    }

    public void remover(int codigo) throws FormaPagamentoNaoEncontradaException {
        int indiceFormaPagamento = this.getIndice(codigo);
        this.indice--;
        this.FormaPagamento[indiceFormaPagamento] = this.FormaPagamento[this.indice];
        this.FormaPagamento[this.indice] = null;
    }

    public FormaPagamento procurar(int codigo) throws FormaPagamentoNaoEncontradaException {
        for (int i = 0; i < this.indice; i++) {
            if (FormaPagamento[i].getCodigo() == codigo) {
                return FormaPagamento[i];
            }
        }
        throw new FormaPagamentoNaoEncontradaException(codigo);
    }

    public boolean existe(int codigo) {
        for (int i = 0; i < this.indice; i++) {
            if (FormaPagamento[i].getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    private int getIndice(int codigo) throws FormaPagamentoNaoEncontradaException {
        for (int i = 0; i < this.indice; i++) {
            if (FormaPagamento[i].getCodigo()==codigo) {
                return i;
            }
        }
        throw new FormaPagamentoNaoEncontradaException(codigo);
    }
}