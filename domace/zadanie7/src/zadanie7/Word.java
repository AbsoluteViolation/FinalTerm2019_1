package zadanie7;

public class Word {
	
	String[] podM = {"Jano", "Jozef"};
	String[] slovesa = {"pije" , "spi "};
	
	public Word() {
		
		for (int i = 0; i < podM.length; i++) {
			
			for (int j = 0; j < slovesa.length; j++) {
				System.out.println(podM[i] + " " + slovesa[j]);
			}
		}
		
	}
	
	
}
