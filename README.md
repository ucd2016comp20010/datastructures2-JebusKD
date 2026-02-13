**COMP20280 Data Structures**<br>
Fork by <ins>Jamie Kildea</ins>

This README file features answers for the lab sheets which are not directly part of the code.

# Wk. 2, Linked Lists
Coding questions have been answered by contributing to this repository. Below are a few sentences on each of the non-code questions:
## Question 5
> In my upstream repo, there are some unit test classes SinglyLinkedListTest, DoublyLinkedListTest, etc. These tests require Junit5. Run these tests and make sure they all pass. Are the tests complete?

All provided tests pass. However, many tests are dependent on several other untested methods - for instance, `SinglyLinkedListTest testAdd` is dependent on `addLast()` working correctly, `SinglyLinkedList testRemove` is dependent on `size()` and `addLast()` working. `SinglyLinkedListTest testAddLast` has a circular dependency on `SinglyLinkedListTest testAddFirst`. These dependencies can lead to cascading errors which require multiple methods to be fixed to be able to diagnose an error in one. In addition to that, many tests make use of multiple assertions, which further complicates the location and resolution of errors in the tests. Some similar issues can be spotted in `DoublyLinkedListTest` and `CircularlyLinkedListTest`.

## Question 6
> What is the difference between a singly linked list and a circularly linked list?

A Singly Linked List is one-way and the final node inside points to `null` as its succeeding node. If given any node within a Singly Linked List, there is no way to find out the size of the entire list, or the identities of members placed before that node within the list. To be able to access all elements in the list, you have to begin at the 'head' node of the list. While a Circularly Linked List is also one-way, the final node of the list instead of pointing to the first node in the list. This means that any given node within the Circularly Linked List is able to calculate the size of the entire list, and obtain all members within. Any node is an access point to the entire list.

## Question 7
> In what situations would you prefer to use a linked list to an array?

In cases where the number of elements is unknown, a linked list can resize to fit the needs without taking up excess storage, as an array declares and holds onto a pre-determined block of memory. If an array needs to resize, the machine will have to create a brand new array and copy all prior members into it, which is an O(n) complexity action. For a linked list, appending to the end of the existing list is an O(1) complexity action. If a large continuous block of memory is inacessible in memory, an array may not be able to be declared. Meanwhile, linked lists can exist across many interlinked addresses in the memory heap.

## Question 8
> Describe 2 possible use-cases for a circularly linked list (2-3 sentences for each).

A Circularly Linked List is excellent at implementing a Stack. If the top element of the stack is the node directly after the reference point of the list (the first/bottom element of the stack) it's possible to add or remove this first element with an O(1) time complexity.

A Circularly Linked List could also be used to produce an infinitely-repeating pattern of elements. This can be done with an iterator and a `for` loop.

# Wk. 3, Stacks, Queues, Deques
Coding questions have been answered by contributing to this repository. Below are a few sentences on each of the non-code questions:
## Question 2
> Write the pseudocode for an algorithm which implements a Queue using two stacks. Provide implementations for the enqueue() and dequeue() methods.

```
let 'mainStack' and 'helperStack' be empty stacks

define enqueue( new element 'i' ) :
  while there is an element in 'mainStack' do
    pop the top element from 'mainStack' and push it onto 'helperStack'

  push 'i' onto 'helperStack'

  while there is an element in 'helperStack' do
    pop the top element from 'helperStack' and push it onto 'mainStack'

define dequeue() :
  pop the top element from 'mainStack' and return it
```

## Question 3
> Write the pseudocode algorithm which reverses the elements on a Stack using two additional Stack's (no other data structures are allowed).

```
algorithm reverse-stack is
  input : stack 'target' which needs to be reversed

  let 's1' and 's2' be empty stacks

  while there is an element in 'target' do
    pop the top element from 'target' and push it onto 's1'

  while there is an element in 's1' do
    pop the top element from 'target' and push it onto 's2'

  while there is an element in 's2' do
    pop the top element from 's2' and push it onto 'target'
```

## Question 4
> How would you extend \[the method made for question 4\] to handle different bases? and bases greater than 9?

This is the operation performed for the binary implementation on every iteration:
```java
while (dec_num > 0) {
  remainders.push((int) (dec_num % 2));
  dec_num /= 2;
}
```
Note the modulo `%` and division `/=` performed on the number `2`, where binary is a base-2 number system. We can change this `2` to anything, replacing `2` with `10` for instance will cause our method to simply convert the incoming long number to a string (albiet, in an extraordinarily unoptimised manner), and any base between 2 and 10 (inclusive) will be converted in these conditions.
For bases above this, we will have to first define single characters to represent a number outside of our standard digits 0 through 9. Take base-16, also known as Hexadecimal, for instance. Under hexadecimal numbers 0 through 9 are represented the same as they are in decimal (base-10), but beyond that, the letters of the alphabet A through F are used to denote a number in a single character. We would have to parse an integer into the corresponding character before concatenating it to our running string. We can replace the standard `.toString()` method with a helper function to attain this.
```java
while (!remainders.isEmpty()) {
  binaryString = binaryString.concat(remainders.pop().intToHex());
}
```
# Wk. 4, Binary Trees

## Question 1

### Subquestion (g)

> What happens if you change positions() to call preorder()?

`positions()` by default obtains its values from `inorder()`. Inorder traversal begins with the 'left-most' side of the tree and traverses rightwards until the end of the end of the tree is reached. It can be described recursively for any beginning node as follows;
```
inorder(node) := [ (inorder(node's left child), node, inorder(node's right child) ]
```
As this will be the iterable collection passed to `toString()`, `toString()` will return a string following this order. Meanwhile, changing `inorder()` for `postorder()` yields a very diffent organisation of members. `postorder()` starts at the root node, and recursively executes `postorder()`
