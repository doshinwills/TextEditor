package spelling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
		boolean addWord = false;
		if(word == null && word.trim().length() !=0) 
			return addWord;
	    String lowerCaseWord = word.toLowerCase();
	    TrieNode current = root;
	    char[] wordChar = lowerCaseWord.toCharArray();
	    int index = 0;
	    while(index < wordChar.length) {
	    	if(current.getChild(wordChar[index]) == null) {
	    		if(index == wordChar.length - 1) {
	    			current = current.insert(wordChar[index]);
	    			current.setEndsWord(true);
	    		} else {
		    		current = current.insert(wordChar[index]);
	    		}
	    	} else {
	    		current = current.getChild(wordChar[index]);
	    		if(index == wordChar.length - 1) {
	    			current.setEndsWord(true);
	    		}
	    	}
	    	index++;
	    }

	    return addWord;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
		printNode(root);
		return itrateTriSize(root);
	}
	
	private int itrateTriSize(TrieNode curr)
 	{
		int size = 0;
 		if (curr == null) 
 			return 0;
 		
 		TrieNode next = null;
 		if(curr.endsWord()) 
 			size++;
 		for (Character c : curr.getValidNextCharacters()) {
 				next = curr.getChild(c);
 				size = size + itrateTriSize(next);
 		}
 		return size;
 	}
	
	private boolean itrateTriWord(TrieNode curr, String word)
 	{
		boolean isWord = false;
 		if (curr == null) 
 			return isWord;
 		
 		TrieNode next = null;
 		if(curr.endsWord() && word.equalsIgnoreCase(curr.getText())) 
 			isWord = isWord || true;
 		for (Character c : curr.getValidNextCharacters()) {
 				next = curr.getChild(c);
 				isWord = isWord || itrateTriWord(next, word);
 		}
 		return isWord;
 	}
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		printNode(root);
		return itrateTriWord(root, s);
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 List<String> completionsList = new ArrayList<String>();
  		
  		TrieNode curr = root;
  		char[] spellings =  prefix.toLowerCase().toCharArray();
  		for (Character c : spellings) {
  			if(curr != null)
  				curr = curr.getChild(c);
  				
  		}
  		if(curr == null) {
  			return completionsList;
  		} 
  		
  		return brethFirstList(curr, numCompletions);
    	 
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
     }
     
     private List<String> brethFirstList(TrieNode curr, int numCompletions) {
    	 Queue<TrieNode> trieNodes = new LinkedList<TrieNode>();
    	 List<String> completionsList = new ArrayList<String>();
    	 if(curr == null || numCompletions < 1)
    	        return completionsList;
    	    else
    	    {
    	    	trieNodes.add(curr);
    	        while(trieNodes.peek() != null)
    	        {
    	        	TrieNode temp = trieNodes.remove();
    	        	if(temp.endsWord())
    	        		completionsList.add(temp.getText());
    	        	if(completionsList.size() == numCompletions)
    	        		break;
    	            for(Character character : temp.getValidNextCharacters()) {
    	            	TrieNode child = temp.getChild(character);
    	            	if(child != null) 
    	            		trieNodes.add(child);
    	            }
    	        }
    	    }
    	 return completionsList;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}