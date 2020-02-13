# COLLECTION_API

## Milan Bhat (mb554), Robert Chen (rec43), Ryan Weeratunga (), Jaidha Rosenblatt (jrr59), 


* In our experience, collections are easy to use because they provide a standarized way of interacting with groups of objects.

* In theory, the collection API is difficult to misuse. However, in practice, we have not thoughourly read the documentation and might use the wrong methods for a certain data structure. The methods in the API are also well-written and account for all the edge cases such as .size() being 0. The methods throw errors when inappropriate parameters are passed and this prevents people from misusing the API. 

* Concrete collection often implement multiple of these interfaces, such as a LinkedList implementing List, Deque, and Queue interfaces. Each of the interfaces provides different functionality that could be useful for the concrete classes. The List interface implementation makes the LinkedList class be a sequenced collection of objects while the Queue interface implementation provides additional insertion, extraction, and inspection operations.

* Set has several implementations (HashSet, TreeSet, LinkedHashSet, AbstractSet, etc.). The number justifies it being an interface because any more than one implementation would reduce duplicate code by having the interface.

* An example of a concrete class is the LinkedList class. This class has several heirarchichal interface levels. It implements Queue, Deque, and List, but each of these are subclasses of Collections. Each level of subclass provides a more specific level of functionality. At the most basic level, a LinkedList is just a group of Objects, or a Collection. As we implement more subclasses, more traits are given to the LinkedList such as being ordered from List. 

* Having the utility class of Collections is better than putting the methods in each of the subclasses because it prevents duplicate code. Many of the methods in the Collections class are applicable to several of the interfaces, so the methods are housed there instead of duplicated into each interface. In the event that the subclass has the same method as the Collections class, the subclass's method should be used because it is likely more specific to that class's implementation.