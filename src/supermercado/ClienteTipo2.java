package supermercado;

import java.util.Random;

public class ClienteTipo2 extends Cliente{
	
	private int tempoAtendimento;
	private Documento documentoCliente;
	public static final int tempoMinAtendimento = 2;
	public static final int tempoMaxAtendimento = 15;
	private static final Random timeGenerator = new Random();

	
	public ClienteTipo2 (int n, int c, int s, Documento doc)
	{
		super(n,c,s);
	    documentoCliente = doc;
	    tempoAtendimento = timeGenerator.nextInt(tempoMaxAtendimento - tempoMinAtendimento +1) + tempoMinAtendimento;
	     
	}
	
	public Documento getDocumento(){
		return documentoCliente;
	}
	
	
	public int getTempoAtendimento(){
		return tempoAtendimento;
	}
	
	
	public void decrementarTempoAtendimento()
	{
	    tempoAtendimento--;
	}
}
