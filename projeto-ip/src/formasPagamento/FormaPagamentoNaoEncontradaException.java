package formasPagamento;

public class FormaPagamentoNaoEncontradaException extends Exception {
    public FormaPagamentoNaoEncontradaException(int codigo) {
        super("A forma de pagamento " + codigo + " não foi encontrada");
    }
}