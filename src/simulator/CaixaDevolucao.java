package simulator;

import ProgramInterfaces.StackTAD;


/**
 * Classe responsável por entregar documentações para clientes. Esta classe
 * extende a classe Caixa, tendo com isso, todas os métodos da classe Caixa
 * disponíveis na classe. A novidade da classe "CaixaDevolucao" conta apenas 
 * com um novo método chamado "entrega".
 * 
 * @author Rodrigo Okido
 * @version 1.0
 */
public class CaixaDevolucao extends Caixa {

	/*
	 * Construtor para a instanciar um objeto "CaixaDevolucao".  
	 */
	public CaixaDevolucao() {
		super();
	}
    
	
	/*
	 * Método responsável por fazer a entrega de documentos ao cliente.
	 * 
	 * @param doc É usado uma pilha de documentos por parâmetro, onde a 
	 * cada chamada do método, um documento é desempilhado na determinada
	 * pilha em que ele estiver localizado
	 */
	public void entrega(StackTAD<Documento> doc) {
		doc.pop();
	}
}
