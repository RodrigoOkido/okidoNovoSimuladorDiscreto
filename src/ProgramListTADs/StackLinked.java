package ProgramListTADs;

import ProgramExceptions.EmptyStackException;
import ProgramInterfaces.StackTAD;

public class StackLinked <E> implements StackTAD<E>{
	
	 private static final class Node<E> {
	        public E element;
	        public Node<E> next;
	        
	        public Node(E e){
	            element = e;
	            next = null;
	        }
	    }

	    private Node<E> head;
	    private Node<E> tail;
	    private int count;
	    
	    public StackLinked()
	    {
	        head = null;
	        tail = null;
	        count = 0;        
	    }


	@Override
	public void push(E element) {
		Node<E> newNode = new Node<E>(element);
		if (count == 0){
			head = newNode;
			tail = newNode;
			count++;
		}
		else{
			tail.next = newNode;
			tail = newNode;
			count++;
		}
		
		
	}

	@Override
	public E pop() throws EmptyStackException {
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
		 Node <E> aux2 = head;	
		for (int i = 0; i < count-1; i ++){
			aux2 = aux2.next;
		}
		
		Node<E> guard = aux2.next;
		aux2.next = null;
		tail = aux2;
		count --;
		return guard.element;
		
		}
		// TODO Auto-generated method stub retorna e remove o item no topo da pilha

	    //Deve lançar uma exceção java.util.EmptyStackException se a pilha estiver vazia
	}

	@Override
	public E top() throws EmptyStackException {
		if (count == 0){
			throw new EmptyStackException ();
		}
		else {
			Node<E> aux = tail;
			return aux.element;
		}
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public boolean isEmpty() {
      	return count == 0;
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		count = 0;
		
	}

}
