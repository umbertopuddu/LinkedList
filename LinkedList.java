import java.util.Iterator;
import java.util.NoSuchElementException;
@SuppressWarnings("unchecked")
public class LinkedList<T> implements Iterable<T> {
    Node head;
    Node cur;
    
    class Node {
        T data;
        Node next;
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
        public Node getNext() {
            return this.next;
        }
        public String toString(){
            return this.data.toString();
        }
        
    }
    
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    public LinkedList() {
        head = null;
        cur = head;
    }

    public LinkedList(T... data) {
        this();
        add(data);
    }

    public void add(T... data) {
        for (T add : data) {
            Node newNode = new Node(add);
            if (head == null) {
                head = newNode;
                cur = head;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }
    }

    public T getCur() {
        if (cur != null) {
            return cur.data;
        }
        return null;
    }

    public Node next() {
        if (cur != null) {
            T data = cur.data;
            cur = cur.next;
            return cur;
        }
        return null;
    }

    public void resetNext() {
        cur = head;
    }

    public boolean hasNext() {
        return cur.next != null;
    }
    

    
    public int length() {
        int count = 0;
        Node iter = head;
        while (iter != null) {
            count++;
            iter = iter.getNext();
        }
        return count;
    }
    
    public T[] toArray(){
        T[] arr = (T[]) new Object[this.length()];
        int iter = 0;
        for(T a : this){
            arr[iter++] = a;
        }
        return arr;
    }

    public boolean insert(int index, T... data) {
        if (index < 0 || index > this.length()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.length());
        }
        if (data == null || data.length == 0) {
            return false; // No data to insert
        }
    
        if (index == 0) { // Insert at the beginning
            for (int i = data.length - 1; i >= 0; i--) {
                Node newNode = new Node(data[i]);
                newNode.next = head;
                head = newNode;
            }
        } else {
            Node previous = get(index - 1);
            Node nextNode = previous.next;
            for (T datum : data) {
                Node newNode = new Node(datum);
                previous.next = newNode;
                previous = previous.next;
            }
            previous.next = nextNode;
        }
        return true;
    }
    
    public Node get(int index) {
        if (index < 0 || index >= this.length()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.length());
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
    
    public int find(T target){
        int i = 0;
        for(T loc : this){
            if(loc.equals(target)){
                return i;
            }
            i++;
        }
        return -1;
    }
    
    public void modify(int index, T value){
        this.get(index).data = value;
    }
    
    public void modify(T previous, T value){
        int index = find(previous);
        this.modify(index, value);
    }
    
    public void remove(int index) {
        if (index == 0) {
            head = head.next;
        } else {
            Node previous = get(index - 1);
            if (previous.next != null) {
                previous.next = previous.next.next;
            }
        }
    }
    
    public void remove(T value){
        int index = find(value);
        if(index != -1){
            remove(index);
        }
    }
    
    public void remove(T... values){
        for(T value : values){
            int index = find(value);
            if(index != -1){
                remove(index);
            } 
        }
    }

}