package supermercado;

import java.util.Random;

public class ClienteTipo2 extends Cliente{
	
	private int tempoAtendimento;
	private Documento documentoCliente;
	private static int min;
	private static int max;
	public static final int tempoMinAtendimento = min;
	public static final int tempoMaxAtendimento = max;
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
	
	
	public static void setMaxTime (int x){
		max = x;
	}
	
	public static void setMinTime (int x){
		min = x;
	}
	
	
	public int getTempoAtendimento(){
		return tempoAtendimento;
	}
	
	
	public void decrementarTempoAtendimento()
	{
	    tempoAtendimento--;
	}
}
