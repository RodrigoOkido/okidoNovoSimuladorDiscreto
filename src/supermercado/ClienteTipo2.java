package supermercado;

public class ClienteTipo2 extends Cliente{
	
	private static int senhaCliente;
	private Documento documentoCliente;
	public static final int tempoMinAtendimento = 2;
	public static final int tempoMaxAtendimento = 15;
	
	public ClienteTipo2 (int n, int c, Documento doc)
	{
		super(n,c);
	    documentoCliente = doc;

	}
	
	public int getSenhaCliente ()
	{
		return senhaCliente;
	}
	
	public Documento getDocumento(){
		return documentoCliente;
	}

}
