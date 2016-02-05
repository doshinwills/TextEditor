package spelling;

import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class DictionaryBST implements Dictionary 
{
   private TreeSet<String> dict;
	
   public DictionaryBST() {
	   super();
	   dict = new TreeSet<String>();
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
    public int size()
    {
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

}
