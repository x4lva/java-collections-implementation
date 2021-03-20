import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

    private Node root;
    private int size;

    public QueueImpl() {
        this.root = null;
        this.size = 0;
    }

    static class Node {
        private Object value;
        private Node next;

        public Node (Object value) {
            this.value = value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl(this.root);
    }

    private class IteratorImpl implements Iterator<Object>  {
        Node root;
        public IteratorImpl(Node root){
            this.root = root;
        }
        @Override
        public boolean hasNext() {
            return this.root != null;
        }
        @Override
        public Object next(){
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            Object element = this.root.getValue();
            this.root = this.root.getNext();
            return element;
        }
    }

    @Override
    public void enqueue(Object element) {
       Node newNode = new Node(element);
       newNode.setNext(null);
        if (this.root == null){
            this.root = newNode;
        }else{
            Node temp = this.root;
            while(temp.getNext()!=null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
        }
        this.size++;
    }

    @Override
    public Object dequeue() {
        if (this.root!=null){
            Node head = this.root;
            this.root = root.getNext();
            this.size--;
            return head.getValue();
        }
        return null;
    }

    @Override
    public Object top() {
        if (this.root != null){
            return this.root.getValue();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");

        for (Node x = this.root; x != null ; x = x.getNext()) {
            res.append(x.getValue());
            if (x.getNext()!=null){
                res.append(", ");
            }
        }

        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        QueueImpl queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue(null);
        System.out.println(queue);
        Iterator<Object> iterator = queue.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
