import java.util.*;

public class Test {
  public static void main(String[] args) {
    // Tests for LinkedList
    System.out.println("Running tests on LinkedList.");
    Integer[] arr = new Integer[] {1, 2, 3, 4, 5};
    // Creating linked list from a list
    LinkedList<Integer> N = new LinkedList<Integer>(Arrays.asList(arr));
    // Add another element in end
    N.add(6);
    // Add an element in front
    N.addFirst(0);
    try {
      if(N.size() != 7) throw new Exception("Error in size");
      if(N.getFirst() != 0) throw new Exception("Error in getFirst");
      if(N.getLast() != 6) throw new Exception("Error in getLast");
      if(N.indexOf(3) != 3) throw new Exception("Error in indexOf");
      N.set(3, 8);
      if(N.contains(3) != false) throw new Exception("Error in contains");
      N.set(3, 3);
      System.out.println("All tests passed.");
    }
    catch (Exception e) {
      System.out.println("TEST FAILED: " + e.getMessage());
    }

    // Tests for BinaryTree
    System.out.println("Running tests on BinaryTree.");
    BinaryTree<Integer> B = new BinaryTree<Integer>();
    // Inserting elements
    B.insert(2);
    B.insert(1);
    B.insert(4);
    B.insert(3);
    B.insert(5);
    /* Tree should now be
     *     2
     *    / \
     *   1   4
     *      / \
     *     3   5
     */
    try {
      if(B.height() != 3) throw new Exception("Error in height");
      if(B.search(4) != true) throw new Exception("Error in search");
      if(B.search(6) != false) throw new Exception("Error in search");
      ArrayList<Integer> A = B.inorderWalk();
      if(A.get(0) != 1 || A.get(1) != 2 || A.get(2) != 3 || A.get(3) != 4) throw new Exception("Error in inorderWalk");
      B.remove(4);
      /* Tree should now be
      *    2
      *   / \
      *  1   5
      *     /
      *    3
      */
      if(B.search(4) != false) throw new Exception("Error in remove");
      System.out.println("All tests passed.");
      System.out.println("Binary tree is:");
      System.out.println(B);
    }
    catch (Exception e) {
      System.out.println("TEST FAILED: " + e.getMessage());
    }
  }
}
