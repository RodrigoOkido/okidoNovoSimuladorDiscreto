package simulator;

/**
 * A classe Caixa � respons�vel pelo funcionamento no atendimento de algum 
 * tipo de estabelecimento. Possui m�todos para atender, dispensar, verificar
 * se est� vazio ou n�o, ver a quantidade de atendimentos feitos e verificar 
 * o cliente que est� no atendimento atualmente por aquele caixa.
 * 
 * @author Rodrigo Okido
 * @version 1.0
 */
public class Caixa 
{
	
	/*
	 * Atributo do tipo Cliente para verificar o cliente que est�
	 * atualmente no caixa.
	 */
	private Cliente clienteAtual; // cliente sendo atendido no caixa
	
	
	/*
	 * Atributo para contabilizar o n�mero de atendimentos realizados
	 * em um caixa.
	 */
	private int numeroAtendidos;

	
	/*
	 * Construtor para instanciar um Caixa. 
	 */
	public Caixa() {
		clienteAtual = null;
		numeroAtendidos = 0;
	}

	
	/*
	 * M�todo que realiza a fun��o de um caixa atender um novo cliente.
	 * 
	 * @param c Recebe por par�metro uma vari�vel do tipo Cliente
	 */
	public void atenderNovoCliente(Cliente c) {
		clienteAtual = c;

	}

	
	/*
	 * M�todo que realiza a fun��o de dispensar o cliente que estava em
	 * atendimento no caixa.
	 * 
	 * @return c retorna o cliente que estava em atendimento no momento antes
	 * de ser dispensado 
	 */
	public Cliente dispensarClienteAtual() {
		Cliente c = clienteAtual;
		clienteAtual = null;
		numeroAtendidos++;
		return c;
	}

	
	/*
	 * Verica se um caixa est� vazio ou n�o. 
	 * 
	 * @return retorna "true" se o caixa est� vazio, e "false" caso o caixa
	 * esteja atendendo algum cliente.
	 */
	public boolean estaVazio() {
		return (clienteAtual == null);
	}

	
	/*
	 * Retorna o cliente que est� em atendimento no caixa.
	 * 
	 * @return retorna o cliente que est� sendo atendido
	 */
	public Cliente getClienteAtual() {
		return clienteAtual;
	}

	
	/*
	 * Retorna o n�mero de atendimentos realizados em um caixa.
	 * 
	 * @return retorna o n�mero de atendimentos realizados
	 */
	public int getNumeroAtendidos() {
		return numeroAtendidos;
	}
}
