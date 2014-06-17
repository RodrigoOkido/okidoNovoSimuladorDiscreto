package simulator;

import java.util.Random;

/**
 * A classe cliente define como funcionará o cliente. Todo cliente possui um número específico
 * para cada um, o instante de chegada ao estabelecimento, uma senha e o principal fator, um 
 * tempo de atendimento, o qual cada cliente possui um tempo mínimo e máximo em cada estabelecimento.
 *  
 * @author Rodrigo Okido
 * @version 1.0
 */
public class Cliente
{
	
	/**
	 * Atributo responsável por guardar o número de um cliente. 
	 */
	private int numero; //numero do cliente
	
	
	/**
	 * Atributo responsável por guardar o instante de chegada de um cliente.
	 */
	private int instanteChegada;
	
	
	/**
	 * Atributo responsável por guardar a senha de um cliente. Esta senha deve
	 * ser única para cada cliente.
	 */
	private int senhaCliente = 0;
	
	
	/**
	 * Atributo responsável por guardar o tempo de atendimento de cada cliente.
	 */
	private int tempoAtendimento; //quantidade de tempo que resta para o cliente no caixa
	
	
	/**
	 * Gerador de números aleatórios utilizando a classe Random.
	 */
	private static final Random gerador = new Random();
	

	/**
	 * Atributo para definir e guardar o tempo de atendimento mínimo de um cliente. 
	 * O atributo apesar de ser inicializado com zero, pode ser alterado através do
	 * método setMinMaxTimeFila1().
	 */
	public static int tempoMinAtendimento = 0;
	
	
	/**
	 * Atributo para definir e guardar o tempo de atendimento máximo de um cliente. 
	 * O atributo apesar de ser inicializado com zero, pode ser alterado através do
	 * método setMinMaxTimeFila1().
	 */
	public static int tempoMaxAtendimento = 0;

	
	/**
	 * Primeiro construtor da classe Cliente. O cliente recebe por parâmetro duas variáveis.
	 * 
	 * @param n Recebe por parâmetro um inteiro n onde será atribuido ao atributo "numero"
	 * ao novo cliente instanciado
	 * @param c Recebe por parâmetro um inteiro c onde será atribuido ao atributo "instanteChegada"
	 * ao novo cliente instanciado
	 */
	public Cliente(int n, int c)
	{
	    numero = n;
	    instanteChegada = c;
	    tempoAtendimento = gerador.nextInt(tempoMaxAtendimento-tempoMinAtendimento+1)+tempoMinAtendimento; //gera valores entre 5 e 20
	}
	
	
	/**
	 * Segundo construtor da classe Cliente. O cliente recebe por parâmetro três variáveis.
	 * 
	 * @param n Recebe por parâmetro um inteiro n onde será atribuido ao atributo "numero"
	 * ao novo cliente instanciado
	 * @param c Recebe por parâmetro um inteiro c onde será atribuido ao atributo "instanteChegada"
	 * ao novo cliente instanciado
	 * @param s Recebe por parâmetro um inteiro s onde será atribuido ao atributo "senhaCliente"
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
	 * Método responsável por definir o tempo de atendimento mínimo e máximo de um Cliente.
	 * 
	 * @param x Atribui o tempo mínimo de atendimento do Cliente
	 * @param y Atribui o tempo máximo de atendimento do Cliente
	 */
	public static void setMinMaxTimeFila1 (int x, int y){
		tempoMinAtendimento = x;
		tempoMaxAtendimento = y;
	}
	
	
	/**
	 * Método responsável por retornar o número do cliente.
	 * 
	 * @return retorna o número do cliente
	 */
	public int getNumero()
	{
	    return numero;
	}
	
	
	/**
	 * Método responsável por retornar o instante de chegada do cliente.
	 * 
	 * @return retorna o instante de chegada do cliente
	 */
	public int getInstanteChegada()
	{
	    return instanteChegada;
	}
	
	
	/**
	 * Método responsável por decrementar o tempo de atendimento do cliente. 
	 */
	public void decrementarTempoAtendimento()
	{
	    tempoAtendimento--;
	}
	
	
	/**
	 * Método responsável por retornar o tempo de atendimento do cliente.
	 * 
	 * @return retorna o tempo de atendimento do cliente
	 */
	public int getTempoAtendimento()
	{
	    return tempoAtendimento;
	}
	
	
	/**
	 * Método responsável por retornar a senha do cliente.
	 * 
	 * @param c recebe por parâmetro um Cliente c, e retorna o a senha do cliente
	 * definido no parâmetro
	 * @return retorna a senha do cliente definido no parâmetro
	 */
	public int getSenhaCliente (Cliente c){
		return senhaCliente;
	}
}
