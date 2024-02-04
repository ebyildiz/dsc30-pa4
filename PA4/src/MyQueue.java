/*
    Name: Elif Yildiz
    PID:  A16962992
 */

/**
 * The class implements a Queue by using a Doubly Linked List
 *
 * @author Elif Yildiz
 * @since 2/3/2024
 */
public class MyQueue<T> implements MyQueueInterface<T> {
    
    /* instance variables, feel free to add if you need */
    private DoublyLinkedList queue;

    /* ===separation=== */

    /**
     * The constructor creates the queue using a DLL
     */
    public MyQueue() {
        queue = new DoublyLinkedList<T>();

    }

    /**
     * Method checks if the queue is empty
     *
     * @return (boolean) true if it is empty, flase if not
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * The method retrieves the size of the queue
     *
     * @return (int) size
     */
    public int size() {
        return queue.size();
    }

    /**
     * Method adds an element to the queue
     *
     * @param data to be added
     * @throws NullPointerException if the data is null
     */
    public void enqueue(T data){
        if(data==null){
            throw new IllegalArgumentException();
        }
        queue.add(data);
    }

    /**
     * The method removes the element at the front from the queue
     *
     * @return (T) the removed element or null if the queue is empty
     */
    public T dequeue() {
        if(queue.isEmpty()){
            return null;
        }
        T toReturn = (T) queue.remove(0);
        return toReturn;
    }

    /**
     * The method retrieves the element at the front of the queue
     * @return (T) the element at the front
     */
    public T peek() {
        if(queue.isEmpty()){
            return null;
        }
        return (T) queue.get(0);
    }

}
