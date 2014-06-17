package ProgramExceptions;

/**
 * Classe responsável para criação de exceções em uma fila.
 * 
 * @author Rodrigo Okido
 * @version 1.0   
 */
public class EmptyQueueException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	
	/**
	 * Construtor para criação de uma exceção de uma Fila
	 */
	public EmptyQueueException()
	{
	    super();
	}
}
