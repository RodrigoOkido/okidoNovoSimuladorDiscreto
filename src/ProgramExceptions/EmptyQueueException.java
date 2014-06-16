package ProgramExceptions;

public class EmptyQueueException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da Exceção de uma Fila
	 *
	 */
	public EmptyQueueException()
	{
	    super();
	}
}
