public interface Array extends Container {
 
    // Add the specified element to the end.
    void add(Object element);
 
    // Sets the element at the specified position.
    void set(int index, Object element);
 
    // Returns the element at the specified position.
    Object get(int index);
 
    // Returns the index of the first occurrence of the specified element,
    // or -1 if this array does not contain the element.
    int indexOf(Object element);
 
    // Removes the element at the specified position.
    void remove(int index);
 
}