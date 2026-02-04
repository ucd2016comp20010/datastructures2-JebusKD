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

## Question 7
> In what situations would you prefer to use a linked list to an array?

In cases where the number of elements is unknown, a linked list can resize to fit the needs without taking up excess storage, as an array declares and holds onto a pre-determined block of memory. If an array needs to resize, the machine will have to create a brand new array and copy all prior members into it, which is an O(n) complexity action. For a linked list, appending to the end of the existing list is an O(1) complexity action. If a large continuous block of memory is inacessible in memory, an array may not be able to be declared. Meanwhile, linked lists can exist across many interlinked addresses in the memory heap.

## Question 8
> Describe 2 possible use-cases for a circularly linked list (2-3 sentences for each).

***(unanswered)***
