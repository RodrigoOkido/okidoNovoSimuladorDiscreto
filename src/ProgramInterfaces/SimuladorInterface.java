package ProgramInterfaces;

/**
 * Interface responsável pela criação de um simulador. Todo simulador criado 
 * que implementar esta Interface, deve apresentar esses três métodos 
 * implementados. 
 * 
 * @author Rodrigo Okido
 * @version 1.0   
 */
public interface SimuladorInterface 
{
	
	/**
	 * Método responsável pela execução da simulação. O método chave e fundamental 
	 * onde o programa vai trabalhar.
	 * 
	 *  @param min Tempo minimo de atendimento de um cliente
	 *  @param max Tempo máximo de atendimento de um cliente
	 *  @param espF Tempo de espera na fila definido
	 *  @param dur Tempo de duração em que a simulação será realizada
	 *  
	 *  @return retorna toda a ocorrência da simulação
	 */
	public String simular(int min, int max, int espF, int dur);
	
	/*
	/**
	 * Método responsável pela execução passo a passo da simulação. Outra alternativa
	 * do método chave e fundamental onde o programa vai trabalhar.
	 * 
	 *  @param min Tempo minimo de atendimento de um cliente
	 *  @param max Tempo máximo de atendimento de um cliente
	 *  @param espF Tempo de espera na fila definido
	 *  @param dur Tempo de duração em que a simulação será realizada
	 *  
	 *  @return retorna toda a ocorrência da simulação passo a passo
	 *
	public String simularStepbyStep(int min, int max, int espF, int dur);
     */
	
	/**
	 * Método responsável por limpar e zerar toda a simulação corrente. 
	 */
	public void limpar();
	
	
	/**
	 * Método responsável por exibir o resultado da simulação corrente gerada.
	 * 
	 * @return retorna estatística básico da simulação
	 */
	public String imprimirResultados();
	
	/**
	 * Método responsável por exibir o resultados estatísticos avançados da simulação corrente gerada. 
	 * 
	 * @return retorna estatística avançada da simulação 
	 */
	public String imprimirEstatisticasAvancadas();

}
