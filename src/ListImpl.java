import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
    Node first;
    int size;

    private static class Node{
        Object element;
        Node next;

        public Node(Object element, Node next){
            this.element = element;
            this.next = next;
        }
    }

    public ListImpl(){
        this.first = null;
        this.size = 0;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.first = null;
    }

    @Override
    public int size() {
        return this.size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl(this.first);
    }

    private class IteratorImpl implements Iterator<Object> {

        Node head;

        IteratorImpl(Node head){
            this.head = head;
        }

        @Override
        public boolean hasNext() {
            return this.head != null;
        }

        @Override
        public Object next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            Object element = this.head.element;
            this.head = this.head.next;
            return element;
        }

    }

    @Override
    public void addFirst(Object element) {
        Node temp = this.first;
        Node newNode = new Node(element, temp);
        this.first = newNode;
        this.size++;

    }

    @Override
    public void addLast(Object element) {
        Node newNode = new Node(element, null);
        if (this.first == null){
            this.first = newNode;
        }else{
            Node temp = this.first;
            while(temp.next!=null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        this.size++;
    }

    @Override
    public void removeFirst() {
        if (this.first != null){
            this.first = this.first.next;
            this.size--;
        }
    }

    @Override
    public void removeLast() {
        if (this.first != null){
            Node temp = this.first;
            while (temp.next.next != null){
                temp = temp.next;
            }
            temp.next = null;
            this.size--;
        }
    }

    @Override
    public Object getFirst() {
        if (this.first!=null){
            return this.first.element;
        }
        return null;
    }

    @Override
    public Object getLast() {
        if (this.first != null){
            for (Node x = this.first; x!=null; x=x.next){
                if (x.next==null){
                    return x.element;
                }
            }
        }
        return null;
    }

    @Override
    public Object search(Object element) {
        Node temp = this.first;
        while(temp != null){
            if(temp.element!=null && temp.element.equals(element)){
                return temp.element;
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        Node previous = this.first;
        Node current = this.first.next;
        if (previous.element!=null && previous.element.equals(element)){
            removeFirst();
            return true;
        }
        while (current != null) {
            Object dataOld = current.element;
            if ((dataOld == null && element == null) || (dataOld != null && dataOld.equals(element))) {
                Node afterRemoved = current.next;
                previous.next = afterRemoved;
                this.size--;
                return true;
            } else {
                previous = current;
                current = current.next;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        st.append("[");
        for (Node x = this.first; x!=null; x = x.next){
            st.append(x.element);
            if (x.next!=null){
                st.append(", ");
            }
        }
        st.append("]");
        return st.toString();
    }

    public static void main(String[] args) {
        ListImpl list = new ListImpl();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        list.addLast("D");
        list.addLast(null);
        list.addLast(null);
        System.out.println(list.remove("G"));
        System.out.println(list);
    }
}
