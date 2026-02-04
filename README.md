**COMP20280 Data Structures**<br>
Fork by <ins>Jamie Kildea</ins>

This README file features answers for the lab sheets which are not directly part of the code.

# Wk. 2
Coding questions have been answered by contributing to this repository. Below are a few sentences on each of the non-code questions:
## Question 5
> In my upstream repo, there are some unit test classes SinglyLinkedListTest, DoublyLinkedListTest, etc. These tests require Junit5. Run these tests and make sure they all pass. Are the tests complete?

All provided tests pass. However, many tests are dependent on several other untested methods - for instance, `SinglyLinkedListTest testAdd` is dependent on `addLast()` working correctly, `SinglyLinkedList testRemove` is dependent on `size()` and `addLast()` working. `SinglyLinkedListTest testAddLast` has a circular dependency on `SinglyLinkedListTest testAddFirst`. These dependencies can lead to cascading errors which require multiple methods to be fixed to be able to diagnose an error in one. In addition to that, many tests make use of multiple assertions, which further complicates the location and resolution of errors in the tests. Some similar issues can be spotted in `DoublyLinkedListTest` and `CircularlyLinkedListTest`.

## Question 6
> What is the difference between a singly linked list and a circularly linked list?

A Singly Linked List is one-way and the final node inside points to null as its succeeding node. If given any node within a Singly Linked List, there is no way to find out the size of the entire list, or the identities of members placed before that node within the list. To be able to access all elements in the list, you have to begin at the 'head' node of the list. While a Circularly Linked List is also one-way, the final node of the list instead of pointing to the first node in the list. This means that any given node within the Circularly Linked List is able to calculate the size of the entire list, and obtain all members within. Any node is an access point to the entire list.
