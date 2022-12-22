package sk.paz1a.practicals.zadanie1;

import sk.upjs.jpaz2.*;

public class Launcher {

	public static void main(String[] args) {
		// create new "sandbox" - a place where turtles can live
		AnimatedWinPane sandbox = new AnimatedWinPane();
		
		HomeTurtle rasto = new HomeTurtle();

		sandbox.add(rasto);

		// rasto.flower(50);
		// rasto.arrow(100);
//		rasto.navigationArrow(25);
//		rasto.hexagon(50);
//		rasto.beehive(50);
		// rasto.smartWatch(80, 3, 48);
		//rasto.flagOfSouthKorea(100000, 50);
//		rasto.sipka(50, 75);
		rasto.sipkovnica(5, 15, 30, 2);
	}
}