package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
	public DictionaryLL() {
		super();
		dict = new LinkedList<String>();
	}

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	boolean added = false;
    	if(word != null && word.trim().length() > 0 && !dict.contains(word.toLowerCase())) {
    		added = dict.add(word.toLowerCase());
    	}
        return added;
    }


    /** Return the number of words in the dictionary */
    public int size() {
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	boolean isWord = false;
    	if(s != null && s.trim().length() > 0) {
    		isWord = dict.contains(s.toLowerCase());
    	}
        return isWord;
    }
    
    public static void main(String[] args) {
    	DictionaryLL dictionaryLL = new DictionaryLL();
    	dictionaryLL.addWord("Doshin");
    	dictionaryLL.addWord("MAry Ann");
    	System.out.println(dictionaryLL.isWord("Mary Ann"));
    	
	}

    
}
