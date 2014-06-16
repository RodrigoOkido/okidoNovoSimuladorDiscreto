package ProgramInterfaces;

/**
 * Interface de uma Pilha. Toda classe que implementar esta interface, deve
 * possuir todos os respectivos métodos devidamente implementados. O principal cuidado
 * de uma pilha, é o fato que todo elemento adicionado dentro dela, ele deve ser o último 
 * a ser retirado. Isto significa que se um elemento for adicionado, e logo em seguida 
 * um próximo elemento entrar, o último elemento deve ser desempilhado primeiro, para em 
 * seguida, o primeiro adicionado ser retirado.
 * 
 * 
 * @author Rodrigo Okido
 * @version 1.0   
 */
public interface StackTAD <E> {
	
	       /*
	        * Método responsável por adicionar um elemento na pilha. 
	        * 
	        * @param element Adiciona um elemento de um tipo qualquer dentro da pilha
	        */
	       public void push(E element);
	       
	       
	       /*
	        * Método responsável por remover o elemento do topo da pilha. 
	        */
	       public E pop();
	       
	       
	       /*
	        * Método responsável por verificar o elemento que se encontra no topo da
	        * pilha.
	        * 
	        *  @return retorna o elemento encontrado, podendo ser de qualquer tipo
	        */
	       public E top();
	       
	       
	       /*
	        *  Método responsável por verificar o tamanho da pilha.
	        *  
	        *  @return retorna o tamanho da pilha
	        */
	       public int size();   
	       
	       
	       /*
	        * Método responsável por verificar se uma pilha está vazia.
	        * 
	        * @return retorna "true" caso a pilha estiver com nenhum elemento dentro nela
	        */
	       public boolean isEmpty();    
	       
	       
	       /*
	        * Método responsável por limpar todo o conteúdo dentro da pilha.
	        */
	       public void clear();

}


