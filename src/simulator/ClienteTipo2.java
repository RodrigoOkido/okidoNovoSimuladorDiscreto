package simulator;

import java.util.Random;

public class ClienteTipo2 extends Cliente{
	
	private int tempoAtendimento;
	private int tempoAtendimento2;
	private Documento documentoCliente;
	public static int tempoMinAtendimento = 0;
	public static int tempoMaxAtendimento = 0;
	public static int tempoMinAtendimento2 = 0;
	public static int tempoMaxAtendimento2 = 0;
	private static final Random timeGenerator = new Random();

	
	public ClienteTipo2 (int n, int c, int s, Documento doc)
	{
		super(n,c,s);
	    documentoCliente = doc;
	    tempoAtendimento = timeGenerator.nextInt(tempoMaxAtendimento - tempoMinAtendimento +1) + tempoMinAtendimento;
	    tempoAtendimento2 = timeGenerator.nextInt(tempoMaxAtendimento2 - tempoMinAtendimento2 +1) + tempoMinAtendimento2;
	     
	}
	
	public Documento getDocumento(){
		return documentoCliente;
	}
	
	
	public static void setMinMaxTimeFila1 (int x, int y){
		tempoMinAtendimento = x;
		tempoMaxAtendimento = y;
	}
	
	public static void setMinMaxTimeFila2 (int w, int z){
		tempoMinAtendimento2 = w;
		tempoMaxAtendimento2 = z;
	}
	
	
	public int getTempoAtendimento(){
		return tempoAtendimento;
	}
	
	public int getTempoAtendimento2(){
		return tempoAtendimento2;
	}
	
	
	public void decrementarTempoAtendimento()
	{
	    tempoAtendimento--;
	}
	
	public void decrementarTempoAtendimento2()
	{
	    tempoAtendimento2--;
	}
}
