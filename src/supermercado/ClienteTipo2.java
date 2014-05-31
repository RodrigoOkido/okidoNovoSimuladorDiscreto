package supermercado;

import java.util.Random;

public class ClienteTipo2 extends Cliente{
	
	private static int senhaCliente;
	private int tempoAtendimentoAE;
	private Documento documentoCliente;
	public static final int tempoMinAtendimento = 2;
	public static final int tempoMaxAtendimento = 15;
	private static final Random timeGenerator = new Random();

	
	public ClienteTipo2 (int n, int c, int s, Documento doc)
	{
		super(n,c);
		senhaCliente = s;
	    documentoCliente = doc;
	    tempoAtendimentoAE = timeGenerator.nextInt(tempoMaxAtendimento - tempoMinAtendimento +1) + tempoMinAtendimento;
	     
	}
	
	public int getSenhaCliente ()
	{
		return senhaCliente;
	}
	
	public Documento getDocumento(){
		return documentoCliente;
	}
	
	
	public int getTempoAtendimentoAE(){
		return tempoAtendimentoAE;
	}
	
	
	public void decrementarTempoAtendimentoAE()
	{
	    tempoAtendimentoAE--;
	}
}
