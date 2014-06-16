package simulator;

import ProgramInterfaces.StackTAD;


/**
 * Classe respons�vel por entregar documenta��es para clientes. Esta classe
 * extende a classe Caixa, tendo com isso, todas os m�todos da classe Caixa
 * dispon�veis na classe. A novidade da classe "CaixaDevolucao" conta apenas 
 * com um novo m�todo chamado "entrega".
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
	 * M�todo respons�vel por fazer a entrega de documentos ao cliente.
	 * 
	 * @param doc � usado uma pilha de documentos por par�metro, onde a 
	 * cada chamada do m�todo, um documento � desempilhado na determinada
	 * pilha em que ele estiver localizado
	 */
	public void entrega(StackTAD<Documento> doc) {
		doc.pop();
	}
}
