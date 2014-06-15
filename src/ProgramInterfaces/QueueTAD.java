package ProgramInterfaces;

public interface QueueTAD<E>
{
    void add(E element);
    E removeFromHead();
    E removeFromTail();
    int size();
    boolean isEmpty();
    void clear();
    E element();
}
