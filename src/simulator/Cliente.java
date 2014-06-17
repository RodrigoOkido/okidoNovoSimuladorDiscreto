package simulator;

import java.util.Random;

/**
 * A classe cliente define como funcionar� o cliente. Todo cliente possui um n�mero espec�fico
 * para cada um, o instante de chegada ao estabelecimento, uma senha e o principal fator, um 
 * tempo de atendimento, o qual cada cliente possui um tempo m�nimo e m�ximo em cada estabelecimento.
 *  
 * @author Rodrigo Okido
 * @version 1.0
 */
public class Cliente
{
	
	/**
	 * Atributo respons�vel por guardar o n�mero de um cliente. 
	 */
	private int numero; //numero do cliente
	
	
	/**
	 * Atributo respons�vel por guardar o instante de chegada de um cliente.
	 */
	private int instanteChegada;
	
	
	/**
	 * Atributo respons�vel por guardar a senha de um cliente. Esta senha deve
	 * ser �nica para cada cliente.
	 */
	private int senhaCliente = 0;
	
	
	/**
	 * Atributo respons�vel por guardar o tempo de atendimento de cada cliente.
	 */
	private int tempoAtendimento; //quantidade de tempo que resta para o cliente no caixa
	
	
	/**
	 * Gerador de n�meros aleat�rios utilizando a classe Random.
	 */
	private static final Random gerador = new Random();
	

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
	 * Primeiro construtor da classe Cliente. O cliente recebe por par�metro duas vari�veis.
	 * 
	 * @param n Recebe por par�metro um inteiro n onde ser� atribuido ao atributo "numero"
	 * ao novo cliente instanciado
	 * @param c Recebe por par�metro um inteiro c onde ser� atribuido ao atributo "instanteChegada"
	 * ao novo cliente instanciado
	 */
	public Cliente(int n, int c)
	{
	    numero = n;
	    instanteChegada = c;
	    tempoAtendimento = gerador.nextInt(tempoMaxAtendimento-tempoMinAtendimento+1)+tempoMinAtendimento; //gera valores entre 5 e 20
	}
	
	
	/**
	 * Segundo construtor da classe Cliente. O cliente recebe por par�metro tr�s vari�veis.
	 * 
	 * @param n Recebe por par�metro um inteiro n onde ser� atribuido ao atributo "numero"
	 * ao novo cliente instanciado
	 * @param c Recebe por par�metro um inteiro c onde ser� atribuido ao atributo "instanteChegada"
	 * ao novo cliente instanciado
	 * @param s Recebe por par�metro um inteiro s onde ser� atribuido ao atributo "senhaCliente"
	 * ao novo cliente instanciado
	 */
	public Cliente(int n, int c, int s)
	{
	    numero = n;
	    instanteChegada = c;
	    senhaCliente = s;
	    tempoAtendimento = gerador.nextInt(tempoMaxAtendimento-tempoMinAtendimento+1)+tempoMinAtendimento; //gera valores entre 5 e 20
	}
	
	
	/**
	 * M�todo respons�vel por definir o tempo de atendimento m�nimo e m�ximo de um Cliente.
	 * 
	 * @param x Atribui o tempo m�nimo de atendimento do Cliente
	 * @param y Atribui o tempo m�ximo de atendimento do Cliente
	 */
	public static void setMinMaxTimeFila1 (int x, int y){
		tempoMinAtendimento = x;
		tempoMaxAtendimento = y;
	}
	
	
	/**
	 * M�todo respons�vel por retornar o n�mero do cliente.
	 * 
	 * @return retorna o n�mero do cliente
	 */
	public int getNumero()
	{
	    return numero;
	}
	
	
	/**
	 * M�todo respons�vel por retornar o instante de chegada do cliente.
	 * 
	 * @return retorna o instante de chegada do cliente
	 */
	public int getInstanteChegada()
	{
	    return instanteChegada;
	}
	
	
	/**
	 * M�todo respons�vel por decrementar o tempo de atendimento do cliente. 
	 */
	public void decrementarTempoAtendimento()
	{
	    tempoAtendimento--;
	}
	
	
	/**
	 * M�todo respons�vel por retornar o tempo de atendimento do cliente.
	 * 
	 * @return retorna o tempo de atendimento do cliente
	 */
	public int getTempoAtendimento()
	{
	    return tempoAtendimento;
	}
	
	
	/**
	 * M�todo respons�vel por retornar a senha do cliente.
	 * 
	 * @param c recebe por par�metro um Cliente c, e retorna o a senha do cliente
	 * definido no par�metro
	 * @return retorna a senha do cliente definido no par�metro
	 */
	public int getSenhaCliente (Cliente c){
		return senhaCliente;
	}
}
