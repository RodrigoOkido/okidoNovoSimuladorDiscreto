package simulator;

import java.util.Random;

/**
 * A classe ClienteTipo2 define como funcionar� um outro tipo de cliente. Esta classe extende a classe
 * Cliente, e com isso, possui tudo o que um cliente mais "gen�rico" possui mais algumas novidades. Esta
 * classe tem a possibilidade de trabalhar com dois caixas, ou seja, o cliente precisa passar por mais de 
 * um caixa para obter o que deseja.
 * 
 * @author Rodrigo Okido
 * @version 1.0
 */
public class ClienteTipo2 extends Cliente
{
	
	/**
	 * Atributo respons�vel por guardar o tempo de atendimento de cada cliente no 
	 * primeiro caixa.
	 */
	private int tempoAtendimento;
	
	
	/**
	 * Atributo respons�vel por guardar o tempo de atendimento de cada cliente no 
	 * segundo caixa.
	 */
	private int tempoAtendimento2;
	
	
	/**
	 * Atributo respons�vel o documento de um cliente. Neste documento est�o composto
	 * o CPF e o RG do cliente. 
	 */
	private Documento documentoCliente;
	
	
	/**
	 * Atributo para definir e guardar o tempo de atendimento m�nimo de um cliente. 
	 * O atributo apesar de ser inicializado com zero, pode ser alterado atrav�s do
	 * m�todo setMinMaxTimeFila1().
	 */
	public static int tempoMinAtendimento = 0;
	
	
	/**
	 * Atributo para definir e guardar o tempo de atendimento m�ximo de um cliente. 
	 * O atributo apesar de ser inicializado com zero, pode ser alterado atrav�s do
	 * m�todo setMinMaxTimeFila1().
	 */
	public static int tempoMaxAtendimento = 0;
	
	
	/**
	 * Atributo para definir e guardar o tempo de atendimento m�nimo de um cliente. 
	 * O atributo apesar de ser inicializado com zero, pode ser alterado atrav�s do
	 * m�todo setMinMaxTimeFila2().
	 */
	public static int tempoMinAtendimento2 = 0;
	
	
	/**
	 * Atributo para definir e guardar o tempo de atendimento m�ximo de um cliente no caixa
	 * de devolu��o. 
	 * O atributo apesar de ser inicializado com zero, pode ser alterado atrav�s do
	 * m�todo setMinMaxTimeFila2().
	 */
	public static int tempoMaxAtendimento2 = 0;
	
	private static final Random timeGenerator = new Random();

	
	/**
	 * Construtor do ClienteTipo2. Inicializa os atributos definindo as
	 * quatro vari�veis por par�metro. 
	 * 
	 * 
	 * @param n Recebe por par�metro um inteiro n onde ser� atribuido ao atributo "numero"
	 * ao novo cliente instanciado
	 * @param c Recebe por par�metro um inteiro c onde ser� atribuido ao atributo "instanteChegada"
	 * ao novo cliente instanciado
	 * @param s Recebe por par�metro um inteiro s onde ser� atribuido ao atributo "senhaCliente"
	 * ao novo cliente instanciado
	 * @param doc Recebe por par�metro um objeto do tipo Documento que ser� atribuido ao
	 * atributo "documentoCliente" ao novo cliente instanciado
	 */
	public ClienteTipo2 (int n, int c, int s, Documento doc)
	{
		super(n,c,s);
	    documentoCliente = doc;
	    tempoAtendimento = timeGenerator.nextInt(tempoMaxAtendimento - tempoMinAtendimento +1) + tempoMinAtendimento;
	    tempoAtendimento2 = timeGenerator.nextInt(tempoMaxAtendimento2 - tempoMinAtendimento2 +1) + tempoMinAtendimento2;
	     
	}
	
	
	/**
	 * M�todo respons�vel por devolver o documento de um cliente.
	 * 
	 * @return retorna o documento do cliente
	 */
	public Documento getDocumento(){
		return documentoCliente;
	}
	
	
	/**
	 * M�todo respons�vel por definir o tempo de atendimento m�nimo e m�ximo de um Cliente
	 * no primeiro caixa.
	 * 
	 * @param x Atribui o tempo m�nimo de atendimento do Cliente no primeiro caixa
	 * @param y Atribui o tempo m�ximo de atendimento do Cliente no primeiro caixa
	 */
	public static void setMinMaxTimeFila1 (int x, int y){
		tempoMinAtendimento = x;
		tempoMaxAtendimento = y;
	}
	
	
	/**
	 * M�todo respons�vel por definir o tempo de atendimento m�nimo e m�ximo de um Cliente
	 * no segundo caixa.
	 * 
	 * @param w Atribui o tempo m�nimo de atendimento do Cliente no caixa
	 * de devolu��o. 
	 * @param z Atribui o tempo m�ximo de atendimento do Cliente no caixa
	 * de devolu��o. 
	 */
	public static void setMinMaxTimeFila2 (int w, int z){
		tempoMinAtendimento2 = w;
		tempoMaxAtendimento2 = z;
	}
	
	
	/**
	 * M�todo respons�vel por retornar o tempo de atendimento do cliente.
	 * Considerando o fato de possuir duas filas, este m�todo retorna o 
	 * tempo de atendimento do cliente no primeiro caixa.
	 * 
	 * @return retorna o tempo de atendimento do cliente no primeiro caixa
	 */
	public int getTempoAtendimento(){
		return tempoAtendimento;
	}
	
	
	/**
	 * M�todo respons�vel por retornar o tempo de atendimento do cliente.
	 * Considerando o fato de possuir duas filas, este m�todo retorna o 
	 * tempo de atendimento do cliente no caixa de devolu��o.
	 * 
	 * @return retorna o tempo de atendimento do cliente no segundo caixa
	 */
	public int getTempoAtendimento2(){
		return tempoAtendimento2;
	}
	
	
	/**
	 * M�todo respons�vel por decrementar o tempo de atendimento do cliente no 
	 * primeiro caixa. 
	 */
	public void decrementarTempoAtendimento()
	{
	    tempoAtendimento--;
	}
	
	
	/**
	 * M�todo respons�vel por decrementar o tempo de atendimento do cliente no caixa
	 * de devolu��o.  
	 */
	public void decrementarTempoAtendimento2()
	{
	    tempoAtendimento2--;
	}
}
