package simulator;

import java.util.Random;

/**
 * A classe ClienteTipo2 define como funcionará um outro tipo de cliente. Esta classe extende a classe
 * Cliente, e com isso, possui tudo o que um cliente mais "genérico" possui mais algumas novidades. Esta
 * classe tem a possibilidade de trabalhar com dois caixas, ou seja, o cliente precisa passar por mais de 
 * um caixa para obter o que deseja.
 * 
 * @author Rodrigo Okido
 * @version 1.0
 */
public class ClienteTipo2 extends Cliente
{
	
	/**
	 * Atributo responsável por guardar o tempo de atendimento de cada cliente no 
	 * primeiro caixa.
	 */
	private int tempoAtendimento;
	
	
	/**
	 * Atributo responsável por guardar o tempo de atendimento de cada cliente no 
	 * segundo caixa.
	 */
	private int tempoAtendimento2;
	
	
	/**
	 * Atributo responsável o documento de um cliente. Neste documento estão composto
	 * o CPF e o RG do cliente. 
	 */
	private Documento documentoCliente;
	
	
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
	 * Atributo para definir e guardar o tempo de atendimento mínimo de um cliente. 
	 * O atributo apesar de ser inicializado com zero, pode ser alterado através do
	 * método setMinMaxTimeFila2().
	 */
	public static int tempoMinAtendimento2 = 0;
	
	
	/**
	 * Atributo para definir e guardar o tempo de atendimento máximo de um cliente no caixa
	 * de devolução. 
	 * O atributo apesar de ser inicializado com zero, pode ser alterado através do
	 * método setMinMaxTimeFila2().
	 */
	public static int tempoMaxAtendimento2 = 0;
	
	private static final Random timeGenerator = new Random();

	
	/**
	 * Construtor do ClienteTipo2. Inicializa os atributos definindo as
	 * quatro variáveis por parâmetro. 
	 * 
	 * 
	 * @param n Recebe por parâmetro um inteiro n onde será atribuido ao atributo "numero"
	 * ao novo cliente instanciado
	 * @param c Recebe por parâmetro um inteiro c onde será atribuido ao atributo "instanteChegada"
	 * ao novo cliente instanciado
	 * @param s Recebe por parâmetro um inteiro s onde será atribuido ao atributo "senhaCliente"
	 * ao novo cliente instanciado
	 * @param doc Recebe por parâmetro um objeto do tipo Documento que será atribuido ao
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
	 * Método responsável por devolver o documento de um cliente.
	 * 
	 * @return retorna o documento do cliente
	 */
	public Documento getDocumento(){
		return documentoCliente;
	}
	
	
	/**
	 * Método responsável por definir o tempo de atendimento mínimo e máximo de um Cliente
	 * no primeiro caixa.
	 * 
	 * @param x Atribui o tempo mínimo de atendimento do Cliente no primeiro caixa
	 * @param y Atribui o tempo máximo de atendimento do Cliente no primeiro caixa
	 */
	public static void setMinMaxTimeFila1 (int x, int y){
		tempoMinAtendimento = x;
		tempoMaxAtendimento = y;
	}
	
	
	/**
	 * Método responsável por definir o tempo de atendimento mínimo e máximo de um Cliente
	 * no segundo caixa.
	 * 
	 * @param w Atribui o tempo mínimo de atendimento do Cliente no caixa
	 * de devolução. 
	 * @param z Atribui o tempo máximo de atendimento do Cliente no caixa
	 * de devolução. 
	 */
	public static void setMinMaxTimeFila2 (int w, int z){
		tempoMinAtendimento2 = w;
		tempoMaxAtendimento2 = z;
	}
	
	
	/**
	 * Método responsável por retornar o tempo de atendimento do cliente.
	 * Considerando o fato de possuir duas filas, este método retorna o 
	 * tempo de atendimento do cliente no primeiro caixa.
	 * 
	 * @return retorna o tempo de atendimento do cliente no primeiro caixa
	 */
	public int getTempoAtendimento(){
		return tempoAtendimento;
	}
	
	
	/**
	 * Método responsável por retornar o tempo de atendimento do cliente.
	 * Considerando o fato de possuir duas filas, este método retorna o 
	 * tempo de atendimento do cliente no caixa de devolução.
	 * 
	 * @return retorna o tempo de atendimento do cliente no segundo caixa
	 */
	public int getTempoAtendimento2(){
		return tempoAtendimento2;
	}
	
	
	/**
	 * Método responsável por decrementar o tempo de atendimento do cliente no 
	 * primeiro caixa. 
	 */
	public void decrementarTempoAtendimento()
	{
	    tempoAtendimento--;
	}
	
	
	/**
	 * Método responsável por decrementar o tempo de atendimento do cliente no caixa
	 * de devolução.  
	 */
	public void decrementarTempoAtendimento2()
	{
	    tempoAtendimento2--;
	}
}
