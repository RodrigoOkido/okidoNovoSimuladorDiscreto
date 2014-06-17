package ProgramExceptions;

/**
 * Classe responsável para criação de exceções em uma pilha. 
 * 
 * @author Rodrigo Okido
 * @version 1.0   
 */
public class EmptyStackException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	
	/**
	 * Construtor para criação de uma exceção de uma pilha. 
	 */
	public EmptyStackException ()
	{
		super();
	}
}
