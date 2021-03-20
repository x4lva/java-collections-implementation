import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    Object[] array;
    int addIndex = 0;

    public ArrayImpl(){
        this.array = new Object[0];
    }

    public ArrayImpl(int array){
        this.array = new Object[array];
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] = null;
        }
    }

	@Override
    public void clear() {
        this.array = new Object[0];
    }

	@Override
    public int size() {
        return this.array.length;
    }
	
	@Override
    public Iterator<Object> iterator() {
	    return new IteratorImpl(this.array);
    }
	
	private class IteratorImpl implements Iterator<Object> {

        Object[] array;
        int currentIndex = -1;
        int lastIndex;

        public IteratorImpl(Object[] array){
            this.lastIndex = array.length-1;
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return this.currentIndex < this.lastIndex;
        }

        @Override
        public Object next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            this.currentIndex++;
            return this.array[this.currentIndex];
        }

    }
	
	@Override
    public void add(Object element) {
        if (this.addIndex < this.array.length){
            this.array[addIndex] = element;
        }else {
            Object[] resultArray = new Object[this.array.length+1];
            System.arraycopy(this.array, 0, resultArray, 0, this.array.length);
            resultArray[this.array.length] = element;
            this.array = resultArray;
        }
        this.addIndex++;
    }

	@Override
    public void set(int index, Object element) {
        if (this.array.length >= index+1){
            this.array[index] = element;
        }
    }

	@Override
    public Object get(int index) {
        if (this.array.length >= index+1){
            return this.array[index];
        }
        return null;
    }

	@Override
    public int indexOf(Object element) {
        for (int i = 0; i < this.array.length ; i++) {
            if((this.array[i] != null && this.array[i].equals(element)) || (this.array[i] == null && element == null)){
                return i;
            }
        }
        return -1;
    }

	@Override
    public void remove(int index) {
        if (this.array.length >= index+1) {
            Object[] copyArray = new Object[this.array.length-1];
            int j = 0;
            for (int i = 0; i < array.length; i++) {
                if (i != index){
                    copyArray[j] = this.array[i];
                    j++;
                }
            }
            this.array = copyArray;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < this.array.length; i++) {
            res.append(this.array[i]);
            if (i != this.array.length-1){
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayImpl arr = new ArrayImpl(4);
        arr.add("A");
        arr.add("B");
        arr.add("C");

        System.out.println(arr);
    }

}
