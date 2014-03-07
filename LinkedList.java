import java.util.*;

/** Implementation of a generic linked list.
 * Can be used to implement stack/queue.
 */
public class LinkedList<T> {
  // Helper class for node representation
  private class Node {
    private T data;
    private Node next;

    public Node() {
      data = null;
      next = null;
    }

    public Node(T data) {
      this.data = data;
      this.next = null;
    }
  }

  // Data fields in linked list
  private Node head;
  private int size;

  /** Creates an empty linked list */
  public LinkedList() {
    head = null;
    size = 0;
  }

  /** Creates a linked list using a collection
   * @param c Collection from which linked list would be created
   */
  public LinkedList(Collection<T> c) {
    this();
    for(Iterator<T> it = c.iterator(); it.hasNext();) {
      T e = it.next();
      this.add(e);
    }
  }

  /** Returns the first element of the linked list
   * @return First element of the linked list
   * @throws NoSuchElementException when linked list is empty
   */
  public T getFirst() {
    try {
      return get(0);
    }
    catch (IndexOutOfBoundsException e) {
      throw new NoSuchElementException();
    }
  }

  /** Returns the last element of the linked list
   * @return Last element of the linked list
   * @throws NoSuchElementException when linked list is empty
   */
  public T getLast() {
    try {
      return get(size - 1);
    }
    catch (IndexOutOfBoundsException e) {
      throw new NoSuchElementException();
    }
  }

  /** Removes and returns the first element of the linked list
   * @return First element of the linked list
   * @throws NoSuchElementException when linked list is empty
   */
  public T removeFirst() {
    try {
      return remove(0);
    }
    catch (IndexOutOfBoundsException e) {
      throw new NoSuchElementException();
    }
  }

  /** Removes and returns last element of the linked list
   * @return Last element of the linked list
   * @throws NoSuchElementException when linked list is empty
   */
  public T removeLast() {
    try {
      return remove(size - 1);
    }
    catch (IndexOutOfBoundsException e) {
      throw new NoSuchElementException();
    }
  }

  /** Inserts an element at beginning of the linked list
   * @param e Element to be inserted
   */
  public void addFirst(T e) {
    add(0, e);
  }
  
  /** Inserts an element at end of the linked list, equivalent to add(T)
   * @param e Element to be inserted
   */
  public void addLast(T e) {
    add(e);
  }

  /** Inserts an element at end of the linked list
   * @param e Element to be inserted
   */
  public void add(T e) {
    add(size, e);
  }

  /** Add an element at a specified index
   *  @param index Index at which element is to be inserted
   *  @param e Element to be inserted
   *  @throws IndexOutOfBoundsException if the index is less than 0 or greater than size()
   */
  public void add(int index, T e) {
    if(index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }
    else {
      Node t = head;
      Node p = null;
      for(int i = 0; i < index; i++) {
        p = t;
        t = t.next;
      }
      Node n = new Node(e);
      // If list is empty
      if(head == null) {
        head = n;
      }
      else if(t == null) {
        // Element is to be inserted at end
        p.next = n;
      }
      else if(p == null) {
        // Element is to be inserted in front
        n.next = head;
        head = n;
      }
      else {
        p.next = n;
        n.next = t;
      }
      size++;
    }
  }

  /** Removes an element from a specified index
   * @param index Index from which element is to be removed
   * @return element previously at the specified index
   * @throws IndexOutOfBoundsException if index is less than 0 or greater than equal to size
   */
  public T remove(int index) {
    if(index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    else {
      Node t = head;
      Node p = null;
      T d = null;
      for(int i = 0; i < index; i++) {
        p = t;
        t = t.next;
      }
      // Element to be deleted is t
      if(p == null) {
        // First element is to be deleted
        d = head.data;
        head = null;
      }
      else {
        p.next = t.next;
        d = t.data;
        t = null;
      }
      return d;
    }
  }

  /** Returns an element at the specified position
   * @param index position from which element would be returned
   * @return element at the specified position
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  public T get(int index) {
    if(index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    else {
      Node t = head;
      for(int i = 0; i < index; i++) {
        t = t.next;
      }
      return t.data;
    }
  }

  /** Replaces an element at the specified location with the given element
   * @param index position at which element is to be replaced
   * @param e new element
   * @return the element previously at the location
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  public T set(int index, T e) {
    if(index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    else {
      Node t = head;
      T d = null;
      for(int i = 0; i < index; i++) {
        t = t.next;
      }
      d = t.data;
      t.data = e;
      return d;
    }
  }

  /** Returns the index of the first occurence of the specified element
   * @param e Element to be searched for
   * @return index of first occurence of the element, -1 if not found
   */
  public int indexOf(T e) {
    Node t = head;
    for(int i = 0; i < size; i++) {
      if(t.data.equals(e)) {
        return i;
      }
      t = t.next;
    }
    return -1;
  }

  /** Checks if an element is present in the linked list or not
   * @param e Element to be searched for
   * @return true if element is present, false otherwise
   */
  public boolean contains(T e) {
    if(indexOf(e) != -1) {
      return true;
    }
    return false;
  }

  /** Returns number of elements in the linked list
   * @return Number of elements, 0 if empty
   */
  public int size() {
    return size;
  }
  
  /** Prints the linked list in a pretty way
   * @return formatted string to be printed
   */
  public String toString() {
    String s = "";
    for(Node x = head; x != null; x = x.next) {
      s = s + x.data + "-->";
    }
    s = s + "End";
    return s;
  }
}

