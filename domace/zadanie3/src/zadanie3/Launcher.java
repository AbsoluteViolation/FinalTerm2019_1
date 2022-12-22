package zadanie3;

import sk.upjs.jpaz2.*;

public class Launcher {

	public static void main(String[] args) {
		WinPane sandbox = new WinPane();
		HomeTurtle rasto = new HomeTurtle();

		rasto.setPosition(150, 150);

		//sandbox.add(rasto);

		// rasto.filledSquare(100);
		// rasto.squardot(100);
//		System.out.println(rasto.isUniDigitNumber(-555));
//
//		System.out.println(rasto.countHammingDistance(0, 17709499));
		System.out.println(rasto.divergence(1));
	}

}
