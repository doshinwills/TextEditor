package textgen;

import java.util.AbstractList;

/**
 * A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E>
 *            The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode(null);
		tail = new LLNode(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * 
	 * @param element
	 *            The element to add
	 */
	public boolean add(E element) {
		if (element != null) {
			LLNode<E> node = new LLNode(element);
			tail.prev.next = node;
			node.prev = tail.prev;
			node.next = tail;
			tail.prev = node;
			size++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get the element at position index
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E get(int index) {
		LLNode<E> node = null;
		if (0 <= index && index < size()) {
			int tempIndex = index;
			node = head.next;
			while (tempIndex != 0) {
				node = node.next;
				tempIndex--;
			}
		} else {
			throw new IndexOutOfBoundsException("Wrong Index to get!");
		}
		return node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * 
	 * @param The
	 *            index where the element should be added
	 * @param element
	 *            The element to add
	 */
	public void add(int index, E element) {
		LLNode<E> node = null;
		if (element == null) {
			throw new NullPointerException("Data cannot be null!");
		}
		if (0 <= index && (index <= size() || index == 0)) {
			int tempIndex = index;
			node = head.next;
			while (tempIndex != 0) {
				node = node.next;
				tempIndex--;
			}
			LLNode<E> newNode = new LLNode(element);
			newNode.prev = node.prev;
			newNode.next = node;

			node.prev.next = newNode;
			node.prev = newNode;
			size++;


		} else {
			throw new IndexOutOfBoundsException("Cannot add element at that position");
		}
	}

	/** Return the size of the list */
	public int size() {
		if (size == 0)
			return -1;
		else
			return size;
	}

	/**
	 * Remove a node at the specified index and return its data element.
	 * 
	 * @param index
	 *            The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException
	 *             If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
		LLNode<E> node = null;
		if (0 <= index && index < size()) {
			int tempIndex = index;
			node = head.next;
			while (tempIndex != 0) {
				node = node.next;
				tempIndex--;
			}
			node.prev.next = node.next;
			node.next.prev = node.prev;
			size--;
		} else {
			throw new IndexOutOfBoundsException("Wrong Index to get!");
		}
		return node.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * 
	 * @param index
	 *            The index of the element to change
	 * @param element
	 *            The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of bounds.
	 */
	public E set(int index, E element) {
		LLNode<E> node = null;
		if (element == null) {
			throw new NullPointerException("Data cannot be null!");
		}
		if (0 <= index && index < size()) {
			int tempIndex = index;
			node = head.next;
			while (tempIndex != 0) {
				node = node.next;
				tempIndex--;
			}
			node.data = element;

		} else {
			throw new IndexOutOfBoundsException("Wrong Index to get!");
		}
		return node.data;
	}

public String toString(){
	LLNode<E> node = head.next;
	String elements = "[";
		while(node.data != null) {
			elements = elements + ", " + node.data.toString();
			node = node.next;
		}
		elements = elements +"]";
		return elements;
	}
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
