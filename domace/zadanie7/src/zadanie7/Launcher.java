package zadanie7;

import java.util.Arrays;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] numbers = new int[]{4, 6, 2, 1, 3, 8};
	    // Vypise: [4, 6, 2, 1, 3, 8]
	    System.out.println(Arrays.toString(numbers));

	    NumberList z1 = new NumberList(numbers);
	    z1.set(1, 8);
	    // Vypise: [4, 8, 2, 1, 3, 8]
	    System.out.println(z1.toString());

	    // Vypise: [4, 6, 2, 1, 3, 8], t.j. pole sa nezmenilo
	    System.out.println(Arrays.toString(numbers));

	    NumberList z2 = new NumberList(z1);
	    // Vypise: [4, 8, 2, 1, 3, 8]
	    System.out.println(z1.toString());
	    // Vypise: [4, 8, 2, 1, 3, 8]
	    System.out.println(z2.toString());

	    z2.set(1, 10);

	    // Vypise: [4, 8, 2, 1, 3, 8]
	    System.out.println(z1.toString());
	    // Vypise: [4, 10, 2, 1, 3, 8]
	    System.out.println(z2.toString());
	    
	    
	    Word uloha = new Word();
	    
	}
	
		
	}




