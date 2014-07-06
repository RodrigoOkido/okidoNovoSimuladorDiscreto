package ProgramInterfaces;

/**
 * Interface de uma fila. Toda fila que implementar esta interface, deve 
 * possuir todos os respectivos m�todos da interface implementados. A fila,
 * nesta interface, trabalha de forma duplamente encadeada, ou seja, ela pode
 * trabalhar com a retirada de elementos em ambos os lados. Cabendo ao desenvolvedor,
 * decidir a melhor forma de trabalhar em cima deles. 
 * 
 * 
 * @author Rodrigo Okido
 * @version 1.0   
 */
public interface QueueTAD<E>
{
	
	/**
	 * Adiciona um elemento dentro de uma fila. O elemento a ser adicionado, por 
	 * ser de um tipo gen�rico, pode ser de qualquer tipo. Tendo liberdade para definir
	 * seu tipo. 
	 * 
	 * @param element Recebe um elemento de tipo qualquer por par�metro para ser adicionado na fila
	 */
    void add(E element);
    
    
    /**
     * M�todo respons�vel por retirar um elemento da fila. O primeiro elemento que chega, 
     * � o primeiro elemento que ser� retirado.
     * 
     * @return retorna o elemento que foi retirado
     */
    E removeFromHead();
    
        
    /**
     * M�todo respons�vel por retirar um elemento da fila. Nesta ocasi�o, a fila funciona 
     * de maneira duplamente encadeada, podendo ser feita a remo��o a partir do ultimo elemento
     * que entra. (Como se trata de uma fila, este m�todo � apenas usado caso necess�rio).
     * 
     * @return retorna o elemento que foi retirado
     */
    E removeFromTail();
    
    
    /**
     * M�todo respons�vel por verificar o tamanho da fila.
     * 
     * @return retorna seu tamanho.
     */
    int size();
    
    
    /**
     * M�todo que verifica se uma fila est� vazia ou n�o.
     * 
     * @return retorna "true" caso a fila estiver vazia ou "false" caso contr�rio
     */
    boolean isEmpty();
    
    
    /**
     * M�todo que esvazia uma fila.
     */
    void clear();
    
    
    /**
     * M�todo respons�vel por retornar o primeiro elemento da fila.
     * 
     * @return retorna o primeiro elemento da fila
     */
    E element();
}
