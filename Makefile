compile: BinaryTree.class LinkedList.class Test.class

BinaryTree.class: BinaryTree.java
	javac -Xlint BinaryTree.java

LinkedList.class: LinkedList.java
	javac -Xlint LinkedList.java

Test.class: Test.java
	javac -Xlint Test.java

run: BinaryTree.class LinkedList.class Test.class
	java Test

doc: BinaryTree.java LinkedList.java
	javadoc BinaryTree.java LinkedList.java -d doc/

clean:
	rm -rf *.class
