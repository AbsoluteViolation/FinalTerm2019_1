package midtermskuska2022;

import sk.upjs.jpaz2.ObjectInspector;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Midtemarka rasto = new Midtemarka();
		
		System.out.println(rasto.countOccurencesOfMaxDigit(999879453));
		System.out.println(rasto.odCislovac("a5h", "gci"));
		System.out.println(rasto.odCislovac("1mn4op78", "ABCDEFGH"));
		
		
//		MidtermPane sandbox = new MidtermPane();
//		
//		ObjectInspector oi = new ObjectInspector();
//		
//		oi.inspect(sandbox);
		
		System.out.println(Math.random() * 3);
	}

}
