package ProgramListTADs;

import ProgramExceptions.EmptyStackException;
import ProgramInterfaces.StackTAD;

/**
 * Classe respons�vel pela cria��o de uma pilha. A classe implementa uma interface gen�rica 
 * chamada "StackTAD" e possui todos os m�todos necess�rios para o funcionamento de uma pilha
 * de qualquer tipo (gen�ricos). 
 * 
 * @author Rodrigo Okido
 * @version 1.0   
 */
public class StackLinked <E> implements StackTAD<E>
{
	
	/**
	 * Classe interna que corresponde a cria��o dos nodos da fila. Cada nodo pode guardar
	 * um elemento de algum tipo gen�rico dentro dele, e referenciar o pr�ximo elemento dele e 
	 * o anterior. 
	 */
	 private static final class Node<E> {
		 
		    /**  
			 * Atributo que guarda um elemento de um tipo qualquer dentro do nodo.
	    	 */
	        public E element;
	        
	        
	        /**
	         * Atributo que referencia o pr�ximo nodo de algum elemento.
	         */
	        public Node<E> next;
	        
	        
	        /**
	         * Atributo que referencia o nodo anterior de algum elemento. 
	         */
	        public Node<E> prev;
	        
	        
	        /**
	         * Construtor para a cria��o do Nodo. Recebe um elemento por par�metro tendo 
	         * o pr�ximo e o anterior ao nodo criado inicializados com null.
	         * 
	         * @param e Recebe por par�metro um elemento de tipo gen�rico para ser colocado
	         * dentro do nodo.
	         */
	        public Node(E e){
	            element = e;
	            next = null;
	            prev = null;
	        }
	    }

	    
	    /**
	     * Atributo que corresponde ao Nodo inicial de uma lista qualquer. Por ser uma pilha, 
	     * o primeiro elemento que entrar na pilha, deve ser o �ltimo elemento a sair.  
	     */
	    private Node<E> head;
	    
	    
	    /**
	     * Atributo que corresponde ao Nodo Final de uma lista qualquer. Por ser uma pilha, 
	     * o �ltimo elemento que entrar na pilha, deve ser o primeiro elemento a sair.
	     */
	    private Node<E> tail;
	    
	    
	    /**
	     * Atributo para controlar o tamanho atual da pilha.
	     */
	    private int count;
	    
	    
	    /**
	     * Construtor para a cria��o de uma pilha. Iniciada com todos os seus atributos vazios, 
	     * ou seja, "head" e "tail" com null, e "count" com zero elementos.
	     */
	    public StackLinked()
	    {
	        head = null;
	        tail = null;
	        count = 0;        
	    }


	/**
	 * Adiciona um elemento dentro da pilha.
	 * 
	 * @param element Adiciona por par�metro um elemento de tipo gen�rico dentro da pilha
	 */
	public void push(E element) {
		Node<E> newNode = new Node<E>(element);
		if (count == 0){
			head = newNode;
			tail = newNode;
			count++;
		}
		else{
			tail.next = newNode;
			tail.next.prev = tail;
			tail = newNode;
			count++;
		}
		
		
	}

	
	/**
	 * M�todo que remove o elemento do topo da pilha. 
	 * 
	 * @throws joga uma EmptyStackException se a pilha estiver vazia
	 * @return retorna o elemento removido
	 */
	public E pop() {
		if (count == 0){
			throw new EmptyStackException ();
		}
		
		if (count == 1){
			Node <E> aux = tail;
			head = null;
			tail = null;
			count--;
			return aux.element;
		}
		
		else{
		Node <E> aux2 = tail;			
		E guard = aux2.element;
		tail.prev.next = null;
		tail = aux2.prev;
		count --;
		return guard;
		
		}
	}

	
	/**
	 * Verifica o elemento que se encontra no topo da pilha.
	 * 
	 * @throws joga uma EmptyStackException se a pilha estiver vazia
	 * @return retorna o elemento que se encontra no topo da pilha
	 */
	public E top() {
		if (count == 0){
			throw new EmptyStackException ();
		}
		else {
			Node<E> aux = tail;
			return aux.element;
		}
	}

	
    /**
     * Verifica o tamanho atual da fila.
     * 
     * @return retorna o tamanho atual da fila
     */
	public int size() {
		return count;
	}

	
    /**
     * Verifica se a fila est� vazia ou n�o.
     * 
     * @return retorna "true" se a lista se encontra vazia, e "false" caso contr�rio
     */
	public boolean isEmpty() {
      	return count == 0;
	}

	
    /**
     * M�todo respons�vel por limpar toda a pilha. Seus elementos ser�o todos exclu�dos 
     * e count zerado.
     */
	public void clear() {
		head = null;
		tail = null;
		count = 0;
		
	}

}
