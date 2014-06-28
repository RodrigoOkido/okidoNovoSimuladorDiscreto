package ProgramInterfaces;

/**
 * Interface respons�vel pela cria��o de um simulador. Todo simulador criado 
 * que implementar esta Interface, deve apresentar esses tr�s m�todos 
 * implementados. 
 * 
 * @author Rodrigo Okido
 * @version 1.0   
 */
public interface SimuladorInterface 
{
	
	/**
	 * M�todo respons�vel pela execu��o da simula��o. O m�todo chave e fundamental 
	 * onde o programa vai trabalhar.
	 * 
	 *  @param min Tempo minimo de atendimento de um cliente
	 *  @param max Tempo m�ximo de atendimento de um cliente
	 *  @param espF Tempo de espera na fila definido
	 *  @param dur Tempo de dura��o em que a simula��o ser� realizada
	 *  
	 *  @return retorna toda a ocorr�ncia da simula��o
	 */
	public String simular(int min, int max, int espF, int dur);
	
	/*
	/**
	 * M�todo respons�vel pela execu��o passo a passo da simula��o. Outra alternativa
	 * do m�todo chave e fundamental onde o programa vai trabalhar.
	 * 
	 *  @param min Tempo minimo de atendimento de um cliente
	 *  @param max Tempo m�ximo de atendimento de um cliente
	 *  @param espF Tempo de espera na fila definido
	 *  @param dur Tempo de dura��o em que a simula��o ser� realizada
	 *  
	 *  @return retorna toda a ocorr�ncia da simula��o passo a passo
	 *
	public String simularStepbyStep(int min, int max, int espF, int dur);
     */
	
	/**
	 * M�todo respons�vel por limpar e zerar toda a simula��o corrente. 
	 */
	public void limpar();
	
	
	/**
	 * M�todo respons�vel por exibir o resultado da simula��o corrente gerada.
	 * 
	 * @return retorna estat�stica b�sico da simula��o
	 */
	public String imprimirResultados();
	
	/**
	 * M�todo respons�vel por exibir o resultados estat�sticos avan�ados da simula��o corrente gerada. 
	 * 
	 * @return retorna estat�stica avan�ada da simula��o 
	 */
	public String imprimirEstatisticasAvancadas();

}
