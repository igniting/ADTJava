import java.util.*;

/** Implementation of binary search tree.
 * Note that this is not a height balancing tree so in worst case,
 * height would be of O(n).
 */
public class BinaryTree<T extends Comparable<T>> {
  // Helper class to represent one node of a binary tree.
  private class Node {
    T key;
    Node leftChild;
    Node rightChild;
    Node parent;
    
    // Default constructor
    public Node() {
      key = null;
      leftChild = rightChild = parent = null;
    }

    /** Paramaterized constructor.
     * @param key Value to be set to key of the node
     */
    public Node(T key) {
      this.key = key;
      leftChild = rightChild = parent = null;
    }

    /** Helper function which recursively searches for key in a node and it's children
     * @param key Value to be searched for
     * @return true if value is found, false otherwise
     */
    private boolean search(T key) {
      if(this.key == key) {
        return true;
      }
      else if(this.key.compareTo(key) < 0) {
        if(rightChild == null) {
          return false;
        }
        else {
          return rightChild.search(key);
        }
      }
      else {
        if(leftChild == null) {
          return false;
        }
        else {
          return leftChild.search(key);
        }
      }
    }

    private ArrayList<T> inorderWalk() {
      ArrayList<T> L = new ArrayList<T>();
      if(leftChild != null) {
        L.addAll(leftChild.inorderWalk());
      }
      L.add(key);
      if(rightChild != null) {
        L.addAll(rightChild.inorderWalk());
      }
      return L;
    }

    private ArrayList<T> preorderWalk() {
      ArrayList<T> L = new ArrayList<T>();
      L.add(key);
      if(leftChild != null) {
        L.addAll(leftChild.preorderWalk());
      }
      if(rightChild != null) {
        L.addAll(rightChild.preorderWalk());
      }
      return L;
    }

    private ArrayList<T> postorderWalk() {
      ArrayList<T> L = new ArrayList<T>();
      if(leftChild != null) {
        L.addAll(leftChild.postorderWalk());
      }
      if(rightChild != null) {
        L.addAll(rightChild.postorderWalk());
      }
      L.add(key);
      return L;
    }

    private int height() {
      int leftH = 0, rightH = 0;
      if(leftChild != null) {
        leftH = leftChild.height();
      }
      if(rightChild != null) {
        rightH = rightChild.height();
      }
      return Math.max(leftH, rightH) + 1;
    }

    private Node getMinimum() {
      Node n = this;
      if(n != null) {
        while(n.leftChild != null) {
          n = n.leftChild;
        }
      }
      return n;
    }

    private void replaceInParent(Node newValue) {
      if(parent != null) {
        if(this == parent.leftChild) {
          parent.leftChild = newValue;
        }
        else {
          parent.rightChild = newValue;
        }
      }
      if(newValue != null) {
        newValue.parent = parent;
      }
    }

    private void remove(T e) {
      if(key.compareTo(e) < 0) {
        if(rightChild != null) {
          rightChild.remove(e);
        }
        else {
          throw new NoSuchElementException();
        }
      }
      else if(key.compareTo(e) > 0) {
        if(leftChild != null) {
          leftChild.remove(e);
        }
        else {
          throw new NoSuchElementException();
        }
      }
      else {
        if(leftChild != null && rightChild != null) {
          Node successor = rightChild.getMinimum();
          key = successor.key;
          successor.remove(successor.key);
        }
        else if(leftChild != null) {
          replaceInParent(leftChild);
        }
        else if(rightChild != null) {
          replaceInParent(rightChild);
        }
        else {
          replaceInParent(null);
        }
      }
    }

    private String prettyPrint(int level) {
      String s = "";
      if(rightChild != null) {
        s = s + rightChild.prettyPrint(level + 1);
      }
      if(level != 0) {
        for(int i = 0; i < level; i++) {
          s = s + "\t";
        }
        s = s + key + "\n";
      }
      else {
        s = s + key + "\n";
      }
      if(leftChild != null) {
        s = s + leftChild.prettyPrint(level + 1);
      }
      return s;
    }
  }

  // Represents the root node of the binary tree.
  Node root;

  /** Creates a binary tree with root as null
   */
  public BinaryTree() {
    root = null;
  }

  /** Function to insert a key into the binary tree at appropriate place
   * @param e Value to be inserted in tree
   */
  public void insert(T e) {
    Node x = root, y = null, z = new Node(e);
    while(x != null) {
      y = x;
      if(z.key.compareTo(x.key) < 0) {
        x = x.leftChild;
      }
      else {
        x = x.rightChild;
      }
    }
    z.parent = y;
    if(y == null) {
      // tree is empty
      root = z;
    }
    else if(z.key.compareTo(y.key) < 0) {
      y.leftChild = z;
    }
    else {
      y.rightChild = z;
    }
  }

  /** Search for a given key in the binary tree
   * @param e Value to be searched for
   * @return true if value is in the tree, false otherwise
   */
  public boolean search(T e) {
    if(root == null) {
      return false;
    }
    else {
      return root.search(e);
    }
  }


  /** Deletes a given key from the binary tree
   * @param e key to be deleted from the tree
   * @throws NoSuchElementException if the given key is not present in the tree
   */
  public void remove(T e) {
    try {
      root.remove(e);
    }
    catch (Exception E) {
      throw E;
    }
  }

  /** Perform an inorder walk on the tree
   * This also gives the key in the sorted order
   * @return an arraylist of elements in the order visited
   */
  public ArrayList<T> inorderWalk() {
    if(root != null) {
      return root.inorderWalk();
    }
    else {
      return new ArrayList<T>();
    }
  }

  /** Perform a preorder walk on the tree
   * @return an arraylist of elements in the order visited
   */
  public ArrayList<T> preorderWalk() {
    if(root != null) {
      return root.preorderWalk();
    }
    else {
      return new ArrayList<T>();
    }
  }

  /** Perform a postorder walk on the tree
   * @return an arraylist of elements in the order visited
   */
  public ArrayList<T> postorderWalk() {
    if(root != null) {
      return root.postorderWalk();
    }
    else {
      return new ArrayList<T>();
    }
  }

  /** Determine height of the tree
   * @return height of tree, 0 if empty
   */
  public int height() {
    if(root != null) {
      return root.height();
    }
    else {
      return 0;
    }
  }

  /** Pretty print the tree
   * Tree printed is rotated by 90 degrees anti-clockwise
   */
  public String toString() {
    if(root == null) {
      return "Empty";
    }
    else {
      return root.prettyPrint(0);
    }
  }
}
