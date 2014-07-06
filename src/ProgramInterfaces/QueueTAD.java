package ProgramInterfaces;

/**
 * Interface de uma fila. Toda fila que implementar esta interface, deve 
 * possuir todos os respectivos métodos da interface implementados. A fila,
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
	 * ser de um tipo genérico, pode ser de qualquer tipo. Tendo liberdade para definir
	 * seu tipo. 
	 * 
	 * @param element Recebe um elemento de tipo qualquer por parâmetro para ser adicionado na fila
	 */
    void add(E element);
    
    
    /**
     * Método responsável por retirar um elemento da fila. O primeiro elemento que chega, 
     * é o primeiro elemento que será retirado.
     * 
     * @return retorna o elemento que foi retirado
     */
    E removeFromHead();
    
        
    /**
     * Método responsável por retirar um elemento da fila. Nesta ocasião, a fila funciona 
     * de maneira duplamente encadeada, podendo ser feita a remoção a partir do ultimo elemento
     * que entra. (Como se trata de uma fila, este método é apenas usado caso necessário).
     * 
     * @return retorna o elemento que foi retirado
     */
    E removeFromTail();
    
    
    /**
     * Método responsável por verificar o tamanho da fila.
     * 
     * @return retorna seu tamanho.
     */
    int size();
    
    
    /**
     * Método que verifica se uma fila está vazia ou não.
     * 
     * @return retorna "true" caso a fila estiver vazia ou "false" caso contrário
     */
    boolean isEmpty();
    
    
    /**
     * Método que esvazia uma fila.
     */
    void clear();
    
    
    /**
     * Método responsável por retornar o primeiro elemento da fila.
     * 
     * @return retorna o primeiro elemento da fila
     */
    E element();
}
