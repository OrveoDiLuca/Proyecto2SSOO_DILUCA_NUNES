/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

/**
 *
 * @author orveodiluca
 */
public class Queue {
    private StudioCharacter head;
    private StudioCharacter tail;
    private int size;

    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public StudioCharacter getHead() {
        return head;
    }

    public void setHead(StudioCharacter head) {
        this.head = head;
    }

    public StudioCharacter getTail() {
        return tail;
    }

    public void setTail(StudioCharacter tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void enqueue(StudioCharacter element) {

        if (isEmpty()) {
            setTail(element);
            setHead(element);
        } else {
            StudioCharacter pointer = getTail();
            pointer.setNext(element);
            setTail(element);
        }
        size++;
    }
    public StudioCharacter dequeue() {
        if (isEmpty()) {
            System.out.println("The queue is empty");
        } else {
            StudioCharacter pointer = getHead();
            setHead((StudioCharacter) pointer.getNext());
            pointer.setNext(null);
            if (getHead() == null) {
                setTail(null);
            }
            size--;
            return pointer;
        }
        return null;
    }

    public boolean isEmpty() {
        return getHead() == null && getTail() == null;
    }

    public String printQueue() {
        String queue = "";
        StudioCharacter pointer = getHead();
        while (pointer != null) {

            queue = queue + pointer.getId() + " | ";
            
            pointer = (StudioCharacter) pointer.getNext();
        }
        //System.out.println(queue);
        return "| "+queue;
    }

    public void deleteFromQueue(StudioCharacter elementToRemove) {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return;
        }

        // Si el elemento a eliminar está al frente de la cola
        if (head.getId() == elementToRemove.getId()) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
            System.out.println("Element " + elementToRemove + " removed from the front of the queue.");
            size--;
            return;
        }

        // Buscar el elemento a eliminar en el resto de la cola
        StudioCharacter current = head;
        StudioCharacter previous = null;

        while (current != null && current.getId() != elementToRemove.getId()) {
            previous = current;
            current = current.getNext();
        }

        // Si se encuentra el elemento, eliminarlo
        if (current != null) {
            previous.setNext(current.getNext());
            if (current == tail) {
                tail = previous;
            }
            System.out.println("Element " + elementToRemove.getId() + " removed from the queue.");
            size--;
        } else {
            System.out.println("Element " + elementToRemove.getId() + " not found in the queue.");
        }
    }
}
