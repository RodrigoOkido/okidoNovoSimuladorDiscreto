package simulator;

/**
 * A classe Caixa é responsável pelo funcionamento no atendimento de algum 
 * tipo de estabelecimento. Possui métodos para atender, dispensar, verificar
 * se está vazio ou não, ver a quantidade de atendimentos feitos e verificar 
 * o cliente que está no atendimento atualmente por aquele caixa.
 * 
 * @author Rodrigo Okido
 * @version 1.0
 */
public class Caixa 
{
	
	/*
	 * Atributo do tipo Cliente para verificar o cliente que está
	 * atualmente no caixa.
	 */
	private Cliente clienteAtual; // cliente sendo atendido no caixa
	
	
	/*
	 * Atributo para contabilizar o número de atendimentos realizados
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
	 * Método que realiza a função de um caixa atender um novo cliente.
	 * 
	 * @param c Recebe por parâmetro uma variável do tipo Cliente
	 */
	public void atenderNovoCliente(Cliente c) {
		clienteAtual = c;

	}

	
	/*
	 * Método que realiza a função de dispensar o cliente que estava em
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
	 * Verica se um caixa está vazio ou não. 
	 * 
	 * @return retorna "true" se o caixa está vazio, e "false" caso o caixa
	 * esteja atendendo algum cliente.
	 */
	public boolean estaVazio() {
		return (clienteAtual == null);
	}

	
	/*
	 * Retorna o cliente que está em atendimento no caixa.
	 * 
	 * @return retorna o cliente que está sendo atendido
	 */
	public Cliente getClienteAtual() {
		return clienteAtual;
	}

	
	/*
	 * Retorna o número de atendimentos realizados em um caixa.
	 * 
	 * @return retorna o número de atendimentos realizados
	 */
	public int getNumeroAtendidos() {
		return numeroAtendidos;
	}
}
