import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    private Node stackRoot;
    private int stackSize;

    public StackImpl() {
        this.stackRoot = null;
        this.stackSize = 0;
    }

    private static class Node {
        private Object stackValue;
        private Node stackNext;

        public Node (Object stackValue) {
            this.stackValue = stackValue;
        }

        public void setValue(Object stackValue) {
            this.stackValue = stackValue;
        }

        public Object getValue() {
            return stackValue;
        }

        public void setNext(Node stackNext) {
            this.stackNext = stackNext;
        }

        public Node getNext() {
            return stackNext;
        }
    }

    @Override
    public void clear() {
        this.stackRoot = null;
        this.stackSize = 0;
    }

    @Override
    public int size() {
        return this.stackSize;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl(this.stackRoot);
    }

    private class IteratorImpl implements Iterator<Object> {

        Node stackRoot;

        public IteratorImpl(Node root) {
            this.stackRoot = root;
        }

        @Override
        public boolean hasNext() {
            return this.stackRoot!=null;
        }

        @Override
        public Object next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            Object element = this.stackRoot.getValue();
            this.stackRoot = this.stackRoot.getNext();
            return element;
        }

    }

    @Override
    public void push(Object element) {
        Node temp = this.stackRoot;
        Node newNode = new Node(element);
        newNode.setNext(temp);
        this.stackRoot = newNode;
        this.stackSize++;
    }

    @Override
    public Object pop() {
        if (this.stackRoot!=null){
            Node head = this.stackRoot;
            this.stackRoot = stackRoot.getNext();
            this.stackSize--;
            return head.getValue();
        }
        return null;
    }

    @Override
    public Object top() {
        if (this.stackRoot != null){
            return this.stackRoot.getValue();
        }
        return null;
    }

    @Override
    public String toString() {
        Node root = new Node(this.stackRoot.getValue());
        root.setNext(this.stackRoot.getNext());
        Node prev = null;
        Node current = root;
        Node next = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        root = prev;

        StringBuilder stackString = new StringBuilder();
        stackString.append("[");

        for (Node x = root; x != null ; x = x.getNext()) {
            stackString.append(x.getValue());
            if (x.getNext()!=null){
                stackString.append(", ");
            }
        }

        stackString.append("]");
        return stackString.toString();
    }

    public static void main(String[] args) {
        StackImpl stack = new StackImpl();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push(null);
        System.out.println(stack);
        Iterator<Object> iterator = stack.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next());
        }
    }

}
