package spelling;

public class Amain {
	
	public static void main(String[] args) {
		String s = "a";
		for(int index = 1; index <= s.length(); index++) {
			System.out.println(s.substring(0, index-1) + s.substring(index));			
		}
	}

}
