package ProgramListTADs;

import ProgramExceptions.EmptyQueueException;
import ProgramInterfaces.QueueTAD;

/**
 * Classe respons�vel pela cria��o de uma fila. A classe implementa uma interface gen�rica 
 * chamada "QueueTAD" e possui todos os m�todos necess�rios para o funcionamento de uma fila
 * de qualquer tipo (gen�ricos). A classe trabalha de forma duplamente encadeada, podendo trabalhar
 * nela pelos dois lados. 
 * 
 * @author Rodrigo Okido
 * @version 1.0   
 */
public class QueueLinked<E> implements QueueTAD<E>
{
	
	/**
	 * Classe interna que corresponde a cria��o dos nodos da fila. Cada nodo pode guardar
	 * um elemento de algum tipo gen�rico dentro dele, e referenciar o pr�ximo elemento dele e 
	 * o anterior.
	 */
    private static final class Node<E> 
    {
    	
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
            prev = null;
            next = null;
            
        }
    }

    
    /**
     * Atributo que corresponde ao Nodo inicial de uma lista qualquer. Por ser uma fila, 
     * o primeiro elemento que entrar na fila, deve ser o primeiro elemento a sair.  
     */
    private Node<E> head;
    
    
    /**
     * Atributo que corresponde ao Nodo Final de uma lista qualquer. Por ser uma fila, 
     * o �ltimo elemento que entrar na fila, deve ser o �ltimo elemento a sair.
     */
    private Node<E> tail;
    
    
    /**
     * Atributo para controlar o tamanho atual da fila.
     */
    private int count;
    
    
    /**
     * Construtor de uma fila. Iniciada com todos os seus atributos vazios, ou seja, "head" 
     * e "tail" com null, e count com zero elementos.
     */
    public QueueLinked()
    {
        head = null;
        tail = null;
        count = 0;        
    }
    
    
    /**
     * Verifica o tamanho atual da fila.
     * 
     * @return retorna o tamanho atual da fila
     */
    public int size()
    {
        return count;
    }

    
    /**
     * Verifica se a fila est� vazia ou n�o.
     * 
     * @return retorna "true" se a lista se encontra vazia, e "false" caso contr�rio
     */
    public boolean isEmpty()
    {
        return (count == 0);
    }
    
    
    /**
     * M�todo respons�vel por limpar toda a fila. Seus elementos ser�o todos exclu�dos 
     * e count zerado.
     */
    public void clear()
    {
        head = null;
        tail = null;
        count = 0;        
    }

    
    /**
     * Retorna o elemento que se encontra em primeiro na fila.
     * 
     * @throws joga uma EmptyQueueException caso a fila estiver vazia
     * @return retorna o primeiro elemento da fila
     */
    public E element()
    {
        if(isEmpty())
            throw new EmptyQueueException();
        return head.element;
    }
  
    
  /**
   * Adiciona um elemento de um tipo qualquer dentro de uma fila.
   * 
   * @param element Adiciona na fila por par�metro um elemento de um tipo gen�rico,
   * podendo ser de qualquer tipo.
   */
    public void add(E element)
    {
        Node<E> n = new Node<E>(element);
        if (head == null){
           head = n;
           head.prev = null;
        }else{
           tail.next = n;
           tail.next.prev = tail;
        }
        tail = n;
        count++;        
    }
    
    
    /**
     * Remove da lista a partir da "Head", isto �, o primeiro elemento ao ser adicionado,
     * ele ser� o primeiro a ser removido. 
     * 
     * @return retorna o elemento que foi retirado
     */
    public E removeFromHead()
    {
        if(isEmpty())
            throw new EmptyQueueException();
        Node<E> target = head;
        E item = target.element;
        head = target.next;
        target.element = null;
        target.next = null;
        if (head == null)
           tail = null;
        count--;
        return item;            
    }

    
    /**
     * Remove da lista a partir da "Tail", isto �, o �ltimo elemento ao ser adicionado,
     * ele ser� o primeiro a ser removido. Tem um funcionamento similar ao uma pilha neste caso,
     * por isso, deve ser usado apenas caso necess�rio.  
     * 
     * @return retorna o elemento que foi retirado
     */
	public E removeFromTail() {
		if(isEmpty())
            throw new EmptyQueueException();	
		Node<E> target = tail;
		E item = target.element;
		tail = target.prev;
		target.element = null;
		target.prev = null;
		if (tail == null)
			head = null;
		count --;
		return item;	
}    
	
}
