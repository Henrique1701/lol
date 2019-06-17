package formasPagamento;

public class FormaPagamentoJaCadastradaException extends Exception {
    public FormaPagamentoJaCadastradaException(String forma) {
        super("A forma de pagamento " + forma + " nao foi encontrada");
    }
}