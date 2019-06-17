package filiais;

public class RepositorioFiliaisArray implements RepositorioFiliais {
	private Filial[] filiais;
	private int indice;
	
	public RepositorioFiliaisArray(int tamanho) 
	{
		this.filiais = new Filial[tamanho];
		this.indice = 0;
	}
	
	public void inserir (Filial filial)
	{
		if(this.indice==this.filiais.length)
		{
			Filial[] aux = new Filial[this.indice];
			aux = this.filiais;
			indice++;
			this.filiais = new Filial[this.indice];
			for (int i = 0; i<this.indice-1; i++)
			{
				this.filiais[i] = aux[i];
			}
			this.filiais[this.indice-1] = filial;
		}
		else
		{
			this.filiais[this.indice] = filial;
			this.indice++;
		}
	}
	
	public void atualizar (Filial filial) throws FilialNaoEncontradaException
	{
		this.filiais[this.getIndice(filial.getCodigo())] = filial;
	}
	
	private int getIndice (int codigo) throws FilialNaoEncontradaException
	{
		for (int i = 0; i<this.indice+1; i++)
		{
			if(this.filiais[i].getCodigo()==(codigo))
			{
				return i;
			}
		}
		throw new FilialNaoEncontradaException(codigo);
	}
	
	public void remover (int codigo) throws FilialNaoEncontradaException
	{
		this.indice--;
		this.filiais[this.getIndice(codigo)] = this.filiais[this.indice];
		this.filiais[this.indice] = null;
	}
	
	public Filial procurar (int codigo) throws FilialNaoEncontradaException
	{
		for (int i = 0; i<this.indice+1; i++)
		{
			if(this.filiais[i].getCodigo()==codigo)
			{
				return filiais[i];
			}
		}
		throw new FilialNaoEncontradaException(codigo);
	}
	
	public boolean existe (int codigo)
	{
		for (int i = 0; i<this.indice; i++)
		{
			if(this.filiais[i].getCodigo()==codigo)
			{
				return true;
			}
		}
		return false;
	}
}
