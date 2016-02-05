/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	MyLinkedList<Integer> addList;
	MyLinkedList<Integer> sizeList;
	MyLinkedList<Integer> addAtIndex;
	MyLinkedList<Integer> setValueList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
//		shortList.add("C");
//		shortList.add("D");
//		shortList.add(shortList.size()/2, "F");
//		shortList.remove(shortList.size() - 1);

		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
		addList = new MyLinkedList<Integer>();
		sizeList = new MyLinkedList<Integer>();
		addAtIndex = new MyLinkedList<Integer>();
		setValueList = new MyLinkedList<Integer>();
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		//Check for Head and get(0) element pointers
		assertEquals("Remove: check Next of sentonal node ", 
				list1.head.next, list1.tail.prev.prev);
		assertEquals("Remove: check previous first element ", 
				list1.head, list1.tail.prev.prev.prev);
		
		//Check for get(0) and get(1) element pointers
		assertEquals("Remove: check next of first element ", 
				list1.head.next.next, list1.tail.prev);
		assertEquals("Remove: check prev of second element ", 
				list1.head.next, list1.tail.prev.prev);
		
		try {
			list1.remove(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

		
		
		
		// TODO: Add more tests here
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		Integer element = new Integer(45);
		addList.add(element);	
		assertEquals("Add: check head data is correct", 
				addList.head.next.data, element);
		assertEquals("Add: check tail data is correct", 
				addList.tail.prev.data, element);
		assertEquals("Add: check head and tail points to same node", 
				addList.head.next, addList.tail.prev);
		assertEquals("Add: check the size for the node", 
				addList.size(), 1);
		assertEquals("Add: check the get of 0", 
				addList.get(0), element);
		
		element = new Integer(67);
		addList.add(element);
		assertEquals("Add: check head data is correct", 
				addList.head.next.next.data, element);
		assertEquals("Add: check tail data is correct", 
				addList.tail.prev.data, element);
		assertEquals("Add: check head and tail points to same node", 
				addList.head.next.next, addList.tail.prev);
		assertEquals("Add: check the size for the node", 
				addList.size(), 2);
		assertEquals("Add: check the get of 0", 
				addList.get(1), element);
		
		assertEquals("Add: Fail to add ", 
				addList.add(null), false);
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Size: check the get of -1", 
				sizeList.size(), -1);
		sizeList.add(new Integer(15));
		assertEquals("Size: check the get of 1", 
				sizeList.size(), 1);
		sizeList.add(new Integer(20));
		assertEquals("Size: check the get of 2", 
				sizeList.size(), 2);
		sizeList.remove(1);
		assertEquals("Size: check the get of 1", 
				sizeList.size(), 1);
		sizeList.remove(0);
		assertEquals("Size: check the get of 0", 
				sizeList.size(), -1);
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		try {
			addAtIndex.add(0,null);
			fail("AddAtIndex : Check for null when adding");
		}
		catch (NullPointerException e) {
			
		}
		
		try {
			addAtIndex.add(-1,new Integer(34));
			fail("AddAtIndex : Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		try {
			addAtIndex.add(0,new Integer(45));
			
		}
		catch (Exception e) {
			fail("AddAtIndex : Check for exceptions");
		}
		
		addAtIndex.add(new Integer(40));
		try {
			addAtIndex.add(0,new Integer(78));
		}
		catch (IndexOutOfBoundsException e) {
			fail("AddAtIndex : Check out of bounds");
		}
		
		try {
			addAtIndex.add(1,new Integer(89));
			fail("AddAtIndex : Check for exceptions");
		}
		catch (Exception e) {
		}
		
		addAtIndex.add(new Integer(78));
		try {
			addAtIndex.add(1,new Integer(46));
		}
		catch (Exception e) {
			fail("AddAtIndex : Check for exceptions");
		}

		addAtIndex.remove(1);
		try {
			addAtIndex.add(1,new Integer(29));
			fail("AddAtIndex : Check for exceptions");
		}
		catch (Exception e) {
		}

		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		try {
			setValueList.set(0, new Integer(62));
			fail("Set : No value to set");
		}
		catch (IndexOutOfBoundsException e) {
		}
		try {
			setValueList.set(-1, new Integer(62));
			fail("Set : No value to set");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		setValueList.add(new Integer(5));
		try {
			setValueList.set(0, null);
			fail("Set : Check for null");
		}
		catch (NullPointerException e) {
		}
		
		try {
			setValueList.set(2, new Integer(55));
			fail("Set : Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		setValueList.add(new Integer(58));
		assertNotNull("Set: set is working fine",  setValueList.set(1, new Integer(55)));
		assertEquals("Set: Set value ", setValueList.get(1), new Integer(55));

		assertEquals("Set: Size of array", setValueList.size(), 2);
		
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
