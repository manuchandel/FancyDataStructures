## Binary Index Tree

### Initialization

* create object of class BinaryIndexTree of size N.
* Recomended keep N <= 10 ^ 6.
* Initially all nodes of tree are zero.


`BinaryIndexTree tree=new BinaryIndexTree(N);`


### Functions

* To add value at a particular index.
  
  `tree.update(index,value);`

* To add value in a range from startIndex to endIndex to all elements.

  `tree.updateRange(startIndex,endIndex,value)`
  
* To query value at a particular index.

  `tree.query(index,value);`
  
* To query sum of all values from startIndex to endIndex.

  `tree.queryRange(startIndex,endIndex);`

* To reset all nodes of tree i.e set all values to zero.

  `tree.reset();`
  
### Analysis

  * Each update whether on range or at an index takes `O(logN)` time.
  * Each query whether on range or at an index takes `O(logN)` time.
  * Space complexity is `O(N)`
