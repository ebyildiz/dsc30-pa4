/*
 * NAME: Elif Yildiz
 * PID: A16962992
 */

import java.util.AbstractList;
import java.util.LinkedList;

/**
 * Implements a Doubly Linked List with its inner class Node
 * @author Elif Yildiz
 * @since 2/2/2024
 */
public class DoublyLinkedList<T> extends AbstractList<T> {

    /* DLL instance variables */
    private int nelems;
    private Node head;
    private Node tail;
    private T toAdd;
    private void outOfBounds(int index, int start, int end)
    throws IndexOutOfBoundsException{
        if(index>end||index<start){
            throw new IndexOutOfBoundsException();
        }
    }
    private void throwNullEx(T element)
    throws NullPointerException{
        if(element==null){
            throw new NullPointerException();
        }
    }

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {

        /* Node instance variables */
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            data = element;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            data = element;
            next = nextNode;
            prev = prevNode;
        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            return data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            return next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            return prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            this.prev.next = this.next;
            this.next.prev = this.prev;
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        nelems = 0;
        Node dummyHead = new Node(null);
        Node dummyTail = new Node(null);
        head = dummyHead;
        tail = dummyTail;
        head.setNext(tail);
        tail.setPrev(head);
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return true if the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        throwNullEx(element);

        Node toBeAdded = new Node(element);

        tail.getPrev().setNext(toBeAdded);
        toBeAdded.setNext(tail);
        toBeAdded.setPrev(tail.prev);
        tail.setPrev(toBeAdded);

        nelems++;

        return true;

    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * @param element the element to be added to the list
     * @param index the index we want the element to eb added
     * @throws IndexOutOfBoundsException if index is not in range [0,size]
     * @throws NullPointerException if the element is null
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {

        outOfBounds(index, 0, size());
        throwNullEx(element);

        Node curNode = head;
        Node tobeAdded = new Node(element);

        for(int i=0; i<index+1; i++){
            curNode = curNode.getNext();
        }

        Node before = curNode.getPrev();
        Node after = curNode;

        before.setNext(tobeAdded);
        after.setPrev(tobeAdded);

        tobeAdded.setNext(after);
        tobeAdded.setPrev(before);

        nelems++;

    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        nelems = 0;
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * @param element the element we are searching for
     * @return (boolean) true if the list contains the element, false if not
     */
    @Override
    public boolean contains(Object element) {
        T data = (T) element;
        Node curNode = head.getNext();
        while(curNode!=tail){
            if(curNode.getElement().equals(data)){
                return true;
            }
            curNode=curNode.getNext();
        }
        return false;

    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index the index of the Node which has the element we need
     * @return (T) the element at the given index in the list
     * @throws IndexOutOfBoundsException if index is not in range [0, size-1]
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        outOfBounds(index, 0, size()-1);
        T atIndex = getNth(index).getElement();
        return atIndex;
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * @param index the index of the Node we want
     * @return (Node) the Node at the given index
     */
    private Node getNth(int index) {
        Node atIndex = head;
        for(int i=0; i<index+1; i++){
            atIndex = atIndex.getNext();
        }
        return atIndex;
    }

    /**
     * Determine if the list empty
     *
     * @return (boolean) true if the list is empty/otherwise false
     */
    @Override
    public boolean isEmpty() {
        if (nelems==0){
            return true;
        }
        return false;
    }

    /**
     * Remove the element from position index in the list
     *
     * @param index the index of the Node we want to remove
     * @return the removed Node's data
     * @throws IndexOutOfBoundsException if the index is out of range [0, size-1]
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        outOfBounds(index, 0, size()-1);

        Node atIndex = getNth(index);
        T removed = atIndex.getElement();

        Node before = atIndex.getPrev() ;
        Node after = atIndex.getNext();
        before.setNext(after);
        after.setPrev(before);

        nelems--;

        return removed;
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * @param index the index of the Node we want the element in
     * @param element the element we want to set for the Node at index
     * @return the element previously stored in Node
     * @throws IndexOutOfBoundsException if  index is outside the range [0, size - 1]
     * @throws NullPointerException if the element is null
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        throwNullEx(element);
        outOfBounds(index, 0, size()-1);

        Node atIndex = getNth(index);
        T prevData = atIndex.getElement();
        atIndex.setElement(element);

        return prevData;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return the size of the list
     */
    @Override
    public int size() {
        return nelems;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     *
     * @return (String) the String representation of the list
     *          with elements
     */
    @Override
    public String toString() {
        String allEls = "";
        Node curNode = head.getNext();
        while (curNode!=tail){
            allEls = allEls + " -> " + curNode.getElement().toString();
            curNode = curNode.getNext();
        }

        return "[(head)"+ allEls + " -> (tail)]";
    }

    /* ==================== EXTRA CREDIT ==================== */

    /**
     * Remove nodes whose index is a multiple of base
     *
     * @param base is a base for the indexes we are going to remove
     *             which will be multiples of the base
     * @throws IllegalArgumentException if base is less than 1
     */
    public void removeMultipleOf(int base) {
        if(base<1){
            throw new IllegalArgumentException();
        }

        else if(base!=1) {
            for (int i = 0; i < size(); i++) {
                if (i % (base - 1) == 0) {
                    remove(i);
                }
            }
        }
        else{
            clear();
        }
    }

    /**
     * Swap the nodes between index [0, splitIndex] of two lists
     *
     * @param other the other Node we need to swap elements with
     * @param splitIndex the end of the range we want the swap to happen
     */
    public void swapSegment(DoublyLinkedList<T> other, int splitIndex) {
            Node first = this.head.getNext();
            this.head.setNext(other.head.getNext());
            other.head.setNext(first);
            this.head.getNext().setPrev(this.head);
            other.head.getNext().setPrev(other.head);

            Node atIndexThis = this.getNth(splitIndex);
            Node atIndexOther = other.getNth(splitIndex);

            Node rebond = atIndexThis.getNext();
            atIndexThis.setNext(atIndexOther.getNext());
            atIndexOther.setNext(rebond);

            atIndexThis.getNext().setPrev(atIndexThis);
            atIndexOther.getNext().setPrev(atIndexOther);


    }

}
