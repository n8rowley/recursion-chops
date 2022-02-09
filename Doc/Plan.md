# Software Development Plan

## - - - Requirement Specifications - - - - - - - - - - 

Create several binary tree-manipulating methods to practice recursive thinking


## - - - System Analysis - - - - - - - - - -

### Input

**Class:**
The tree class takes either an array of arguments and a tree label and creates a 
tree with each value in a node *or* A label and creates a new, empty tree

**Functions:** Many functions take no arguments. Some will take an int to know where or what to 
look for.

###Output

No methods in the Tree class print anything. Everything is a string or a TreeNode and
the driver takes care of printing. TreeNodes will have an overridden toString that 
will print when the object is printed


## - - - Functions - - - - - - - - - - -

Create a generic binary tree class. Must include methods to perform several actions

   1) Override toString method to generate a string with the tree name and each 
      value with its parent node in brackets beside it. Each value is indented based
      on its node depth in the tree. Must have "no parent" for root node and be
      able to handle and empty tree 
      
            public String ToString()

   2) a method that generates a string of the tree values in order. Must include 
      tree name, and every value
        
            public String inOrderToString()

   3) A method that balances a binary tree. Adds nodes evenly to either side of
      each node
      - copies the values of existing tree into an arraylist
      - creates a new tree
      - give values to insert in a binary search fashion
      - assign existing tree reference to new tree
    
            
            public void balanceTree()
      
   4) A method that flips position of every node's children

            public void flip()

   5) A method that searches a tree for a value and returns the node. Assumes a BST
      and that the value is present
      
            public BinaryTreeNode<E> getByKey(E key)

   6) A method that takes a depth level of a tree as an int and reports the number 
      of nodes at that level. Cannot store this info in TreeNode.
      
            public int nodesInLevel(int level)

   7) A method that prints the path to every leaf node
       - Traverse to a leaf node
          - Add the value of each node along to way to a string
       - return the string after it's been passed down
    

            public void printAllPaths()
    

   8) A method that accepts a node of a tree and returns the next largest number
      found in the tree. Cannot traverse the whole tree.
      
            public BinaryTreeNode<E> successor(BinaryTreeNode<E> node)

   9) Counts the number of binary search trees that make up a given binary tree. 
      A BST is one that has all values to the left less than the value and all 
      values to the right greater than the value

            public int countBST()

## - - - System Design - - - - - - - - - - - - -

### Class:  Tree


      private String toString(BinaryTreeNode node, depth, String s)

Kicked off with `public String ToString()`
 - create a string with the name of the tree
 - if root has no element, return s + `: Empty Tree`  
 - calls the recursive version with the `root` node, depth of `0`, and string
   `s` and returns the result
   
   
**Algorithm**

 - Base case: If node == null: return
 - append to s a recursive call using the `right` node, `depth + 1`, and `s`
 - append value
    - append *depth* number of tabs
    - append the value of this node
    - append the value of the parent node in `[]`
      - If root, print `[No parent]`
 - append to s a recursive call using the `left` node, `depth + 1`, and `s`


      private String inOrderToString(BinaryTreeNode node, String s)

Kicked off with `public String inOrderToString()`
 - create an empty string `s`
 - append the name of the tree
 - if tree is empty, append `: Empty tree`  
 - calls recursive method with `root` and string `s` and returns result

**Algorithm**

 - Base case: if node == null; return
 - append to s a recursive call using the `left` child and `s`
 - append to s the value of the current node
 - append to s a recursive call using the `right` child and `s`
 - return `s`


    public void balanceTree()


Uses `private int grabValue(BinaryTreeNode node, valueList)`

- Base case: if node == null return
- append to `valueList` a recursive call with `node.left` and `valueList`
- append to `valueList` the value of node
- append to `valueList` a recursive call with `node.right` and `valueList`
- return `valueList`

Uses `private void binaryInsert(ArrayList valueList, int low, int high)`

- Base case: if low > high; return
- mid = (low + high) / 2
- insert the value at mid into the tree
- call binaryInsert with `valueList`, `low`, `mid - 1`
- call binaryInsert with `valueList`, `mid + 1`, and `high`

**Algorithm** 
 - Creates a variable to hold the tree values
 - assigns variable by calling `grabValue()` with the `root` node and a `new arrarylist`
 - set the root to null
 - set the root children to null  
 - calls binaryInsert with `valueList`, `0`, and `valueList.size - 1`
 - 






    private void flip(BinaryTreeNode node)

Kicked off by `void flip()` 
 - calls recursive method with `root`

**Algorithm**
 - Base case: if node == null; return
 - flip current node's children  
   - create a temporary variable
   - set `temp` to `node.left`
   - set `node.left` to `node.right`
   - set `node.right` to `temp`
 - flip left children's children with a recursive call using `node.left`     
 - flip right children's children with a recursive call using `node.right`



      private BinaryTreeNode getByKey(E key, node)


Kicked off by `public BinaryTreeNode<E> getByKey(E key)`

 - starts searching by calling recursive method with `E` and the `root` node
 - return search result


**Algorithm**

 - Base case: if node == null; return
 - if `node.value` == key; return node
 - else if key < node.value; return the left node search
 - else return the right node search



    private int nodesInLevel(int level, int total)

Kicked off by `public int nodesInLevel(int Level)`

 - creates count for the total
 - if level >= 0
   - calls the recursive method with `level` and `total`
   - returns the result 

**Algorithm**

 - Base case: if node == null; `return total`
 - Base case: if level = 0; 
   - return total + 1
 - else
    - set total to a recursive call with `level - 1` and `total`



    private void printAllPaths(BinaryTreeNode node)

Kicked off with `public void printAllPaths()`

 - calls recursive method with `root`

**Algorithm**

 - Base case: if both node children are null;
    - create a printNode variable and set it to root
    - While printNode.value != to node.value
      - print printNode.value
      - if printNode.value < node.value
        - printNode = printNode.left
      - else
        - printNode = printNode.right
    - print node.value

 - else
    - call recursive printAllPaths() with the left child
    - call recursive printAllPaths() with the right child
    


    private BinaryTreeNode<E> inOrderSuccessor(BinaryTreeNode node, int value)


kicked off with `public BinaryTreeNode<E> inOrderSuccessor(BinaryTreeNode<E> node)`
    
 - calls recursive method with `node` and `node.value`

**Algorithm**

 - Base case: if node == null; return null
 - if node.value = value
     - if node.right != null
        - return a recursive call with `node.right` and `value`
     - if parent = null; return null    
     - else if node.parent > node.value: return parent
     - else (node.parent.value < node.value)
       - return a recursive call with `parent` and `value`
 - else if node.value > value
    - if node.left == null; return node
    - ~~else if node.left.value < value; return node~~  
    - else return recursive call with `node.left` and `value`
 - else 
    - if node.parent = null; return null
    - if node.parent > value; return parent
    - else (node.parent < value)
      - return a recursive call with `node.parent` and `value`



    private int countBST(BinaryTreeNode node, int total)

Kicked off with `public int countBST()`
 - creates an int for `total` BST's 
 - returns a recursive method call with `root` and `total`

Uses `private boolean isBST(BinaryTreeNode node)`
 - Base case: if node == null; return true
 - if ((node.left != null *and* node.left > node.value) *or* (node.right != null *and* node.right < node.value))
    - return false
 - else
    - return isBST(node.left) & isBST(node.right)
    

**Algorithm**
 - Base case: if node == null; return `total`
 - if isBST(node)
    - total += 1
 - total = a recursive call with the left child and total
 - total = a recursive call with the left child and total
 - return total