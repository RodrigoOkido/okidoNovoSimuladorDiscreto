package ProgramExceptions;

/**
 * Classe respons�vel para cria��o de exce��es em uma fila.
 * 
 * @author Rodrigo Okido
 * @version 1.0   
 */
public class EmptyQueueException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	
	/**
	 * Construtor para cria��o de uma exce��o de uma Fila
	 */
	public EmptyQueueException()
	{
	    super();
	}
}
