package ProgramListTADs;

import ProgramExceptions.EmptyQueueException;
import ProgramInterfaces.QueueTAD;

public class QueueLinked<E> implements QueueTAD<E>
{
    private static final class Node<E> {
        public E element;
        public Node<E> next;
        public Node<E> prev;
        
        public Node(E e){
            element = e;
            prev = null;
            next = null;
            
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int count;
    
    public QueueLinked()
    {
        head = null;
        tail = null;
        count = 0;        
    }

    public int size()
    {
        return count;
    }

    public boolean isEmpty()
    {
        return (count == 0);
    }
    
    public void clear()
    {
        head = null;
        tail = null;
        count = 0;        
    }

    public E element()
    {
        if(isEmpty())
            throw new EmptyQueueException();
        return head.element;
    }
    
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
    
    /*
     * Remove da lista a partir da "Head", isto é, o primeiro elemento ao ser adicionado,
     * ele será o primeiro a ser removido. 
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
