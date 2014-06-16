package ProgramInterfaces;

/**
 * Interface de uma Pilha. Toda classe que implementar esta interface, deve
 * possuir todos os respectivos m�todos devidamente implementados. O principal cuidado
 * de uma pilha, � o fato que todo elemento adicionado dentro dela, ele deve ser o �ltimo 
 * a ser retirado. Isto significa que se um elemento for adicionado, e logo em seguida 
 * um pr�ximo elemento entrar, o �ltimo elemento deve ser desempilhado primeiro, para em 
 * seguida, o primeiro adicionado ser retirado.
 * 
 * 
 * @author Rodrigo Okido
 * @version 1.0   
 */
public interface StackTAD <E> {
	
	       /*
	        * M�todo respons�vel por adicionar um elemento na pilha. 
	        * 
	        * @param element Adiciona um elemento de um tipo qualquer dentro da pilha
	        */
	       public void push(E element);
	       
	       
	       /*
	        * M�todo respons�vel por remover o elemento do topo da pilha. 
	        */
	       public E pop();
	       
	       
	       /*
	        * M�todo respons�vel por verificar o elemento que se encontra no topo da
	        * pilha.
	        * 
	        *  @return retorna o elemento encontrado, podendo ser de qualquer tipo
	        */
	       public E top();
	       
	       
	       /*
	        *  M�todo respons�vel por verificar o tamanho da pilha.
	        *  
	        *  @return retorna o tamanho da pilha
	        */
	       public int size();   
	       
	       
	       /*
	        * M�todo respons�vel por verificar se uma pilha est� vazia.
	        * 
	        * @return retorna "true" caso a pilha estiver com nenhum elemento dentro nela
	        */
	       public boolean isEmpty();    
	       
	       
	       /*
	        * M�todo respons�vel por limpar todo o conte�do dentro da pilha.
	        */
	       public void clear();

}


