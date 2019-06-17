package formasPagamento;

public class FormaPagamento{
	private int codigo;
    private String forma;
    private double desconto;
    public FormaPagamento(String forma, double desconto) {
		this.forma = forma;
		this.desconto = desconto;
	}
	public FormaPagamento(int codigo, String forma, double desconto) {
		this.codigo = codigo;
		this.forma = forma;
		this.desconto = desconto;
	}
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getForma() {
		return forma;
	}
	public void setForma(String forma) {
		this.forma = forma;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
}